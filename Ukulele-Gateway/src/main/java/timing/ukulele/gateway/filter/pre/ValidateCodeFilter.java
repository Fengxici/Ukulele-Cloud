package timing.ukulele.gateway.filter.pre;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.redisson.cache.CacheManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 验证码验证
 * <p>
 * security.validate.code 默认 为false，开启需要设置为true
 */
@Slf4j
@RefreshScope
@Component("validateCodeFilter")
@ConditionalOnProperty(value = "security.validate.code", havingValue = "true")
public class ValidateCodeFilter extends ZuulFilter {
    private static final String EXPIRED_CAPTCHA_ERROR = "验证码已过期，请重新获取";
    /**
     * oauth token
     */
    String OAUTH_TOKEN_URL = "/oauth/token";
    /**
     * 默认保存code的前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY";

    @Autowired
    CacheManager cacheManager;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER + 1;
    }

    /**
     * 是否校验验证码
     * 1. 判断验证码开关是否开启
     * 2. 判断请求是否登录请求
     * 3. 判断终端是否支持
     *
     * @return true/false
     */
    @Override
    public boolean shouldFilter() {
        final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        if (RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        // 对指定的请求方法 进行验证码的校验
        return StringUtils.containsIgnoreCase(request.getRequestURI(), OAUTH_TOKEN_URL);
    }

    @Override
    public Object run() {
        try {
            checkCode(RequestContext.getCurrentContext().getRequest());
        } catch (Exception e) {
            final RequestContext ctx = RequestContext.getCurrentContext();
            final ResponseVO result = ResponseVO.failure(e.getMessage());
            final HttpServletResponse response = ctx.getResponse();
            response.setCharacterEncoding(Charset.defaultCharset().name());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(478);
            try {
                response.getWriter().print(JSONObject.toJSONString(result));
            } catch (IOException e1) {
                log.error("response io异常");
                e1.printStackTrace();
            }
            ctx.setSendZuulResponse(false);
            ctx.setResponse(response);
        }
        return null;
    }

    /**
     * 检查code
     *
     * @param httpServletRequest request
     * @throws Exception 验证码校验异常
     */
    private void checkCode(HttpServletRequest httpServletRequest) throws Exception {
        final String code = httpServletRequest.getParameter("code");
        if (StringUtils.isBlank(code)) {
            throw new Exception("请输入验证码");
        }

        String randomStr = httpServletRequest.getParameter("randomStr");
        if (StringUtils.isBlank(randomStr)) {
            randomStr = httpServletRequest.getParameter("mobile");
        }

        final String key = DEFAULT_CODE_KEY + randomStr;
        if (!cacheManager.exists(key)) {
            throw new Exception(EXPIRED_CAPTCHA_ERROR);
        }

        final Object codeObj = cacheManager.get(key);

        if (codeObj == null) {
            throw new Exception(EXPIRED_CAPTCHA_ERROR);
        }

        final String saveCode = codeObj.toString();
        if (StringUtils.isBlank(saveCode)) {
            cacheManager.del(key);
            throw new Exception(EXPIRED_CAPTCHA_ERROR);
        }

        if (!StringUtils.equals(saveCode, code)) {
            cacheManager.del(key);
            throw new Exception("验证码错误，请重新输入");
        }

        cacheManager.del(key);
    }
}
