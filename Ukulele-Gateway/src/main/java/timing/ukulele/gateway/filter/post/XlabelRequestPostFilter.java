package timing.ukulele.gateway.filter.post;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;
import timing.ukulele.ribbon.XlabelMvcHeaderInterceptor;

import static timing.ukulele.gateway.filter.GatewayFilterConstants.POST_REQUEST_XLABEL_ORDER;
import static timing.ukulele.gateway.filter.GatewayFilterConstants.POST_TYPE;

/**
 * xlabel标签拦截器,主要是为了XlabelHeaderInterceptor.shutdownHystrixRequestContext
 */
@Component
public class XlabelRequestPostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return POST_REQUEST_XLABEL_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        XlabelMvcHeaderInterceptor.shutdownHystrixRequestContext();
        return null;
    }
}
