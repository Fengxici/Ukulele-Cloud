package timing.ukulele.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import timing.ukulele.data.syslog.LogExchange;
import timing.ukulele.data.syslog.LogType;
import timing.ukulele.data.syslog.view.LogVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;

import static timing.ukulele.gateway.filter.GatewayFilterConstants.PRE_REQUEST_LOG_ORDER;
import static timing.ukulele.gateway.filter.GatewayFilterConstants.PRE_TYPE;
import static timing.ukulele.gateway.filter.GatewayHeaderConstants.USER_HEADER;

/**
 * 请求日志记录
 */
@Component
@Slf4j
public class PreRequestLogFilter extends ZuulFilter {
    private final AmqpTemplate rabbitTemplate;
    /**
     * oauth token
     */
    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    @Autowired
    public PreRequestLogFilter(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_REQUEST_LOG_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        log.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        addLog(request, ctx);
        return null;
    }

    /**
     * 添加系统日志
     *
     * @param request 请求对象
     * @param ctx     RequestContext
     */
    private void addLog(HttpServletRequest request, RequestContext ctx) {
        final LogVO log = new LogVO();
        log.setCreateTime(new Date());
        log.setRemoteAddr(request.getRemoteAddr());
        log.setRequestUri(request.getRequestURI());
        log.setMethod(request.getMethod());

        if (StringUtils.containsIgnoreCase(request.getRequestURI(),
                OAUTH_TOKEN_URL)) {
            // 记录登录日志
            log.setType(LogType.Login.name());
            log.setTitle(LogType.Login.name());
            log.setParams(queryParam(request));
            log.setCreateBy(request.getParameter("username"));
            rabbitTemplate.convertAndSend(LogExchange.SYS_LOG_EXCHANGE, LogExchange.SYS_LOG_ROUTING_KEY, log);
        } else {
            // 记录操作日志
            log.setType(LogType.Operation.name());
            log.setTitle(LogType.Operation.name());
            if (HttpMethod.GET.matches(request.getMethod()))
                log.setParams(queryParam(request));
            log.setCreateBy(ctx.getZuulRequestHeaders().get(USER_HEADER));
            rabbitTemplate.convertAndSend(LogExchange.SYS_LOG_EXCHANGE, LogExchange.SYS_LOG_ROUTING_KEY, log);
        }
    }

    /**
     * 获取get 参数
     */
    private String queryParam(HttpServletRequest request) {
        StringBuilder params = new StringBuilder();
        final Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            params.append(name);
            params.append("=");
            if ("password".equals(name)) {
                params.append("******");
            } else {
                params.append(request.getParameter(name));
            }
            params.append("&");
        }
        if (params.length() > 0) {
            params.delete(params.length() - 1, params.length());
            return params.toString();
        }
        return null;
    }
}
