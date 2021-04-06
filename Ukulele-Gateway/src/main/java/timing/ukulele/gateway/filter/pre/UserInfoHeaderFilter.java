package timing.ukulele.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER;
import static timing.ukulele.gateway.filter.GatewayHeaderConstants.*;

/**
 * 将认证用户的相关信息 放入header中, 后端服务可以直接读取使用
 * 在RateLimitPreFilter 之前执行，不然有空指针问题
 */
@Component
public class UserInfoHeaderFilter extends ZuulFilter {


    private final TokenStore tokenStore;

    @Autowired
    public UserInfoHeaderFilter(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FORM_BODY_WRAPPER_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set("startTime", System.currentTimeMillis());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.addZuulRequestHeader(USER_HEADER, authentication.getName());
            String authorities = Arrays.toString(authentication.getAuthorities().toArray());
            authorities = authorities.replace("[", "");
            authorities = authorities.replace("]", "");
            requestContext.addZuulRequestHeader(ROLE_HEADER, authorities);
        }
        return null;
    }
}
