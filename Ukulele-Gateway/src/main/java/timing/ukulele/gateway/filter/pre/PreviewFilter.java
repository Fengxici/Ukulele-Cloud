package timing.ukulele.gateway.filter.pre;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.common.exception.DefaultError;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 演示环境控制
 */
@Slf4j
@RefreshScope
@Component
@ConditionalOnProperty(value = "security.validate.preview", havingValue = "true")
public class PreviewFilter extends ZuulFilter {
    private static final String TOKEN = "token";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        if (StringUtils.equalsIgnoreCase(request.getMethod(), HttpMethod.GET.name()) ||
                StringUtils.containsIgnoreCase(request.getRequestURI(), TOKEN)) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final ResponseVO result = ResponseVO.failure(DefaultError.SHOW_AUTH_CONTROL);
        final HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding(Charset.defaultCharset().name());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(479);
        try {
            response.getWriter().print(JSONObject.toJSONString(result));
        } catch (IOException e1) {
            log.error("response io异常");
            e1.printStackTrace();
        }
        ctx.setSendZuulResponse(false);
        ctx.setResponse(response);
        return null;
    }
}
