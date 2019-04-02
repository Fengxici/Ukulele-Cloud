package timing.ukulele.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import static timing.ukulele.gateway.filter.GatewayFilterConstants.PRE_REQUEST_XLABEL_ORDER;
import static timing.ukulele.gateway.filter.GatewayFilterConstants.PRE_TYPE;


/**
 * xlabel标签拦截器,主要是为了初始化XlabelHeaderInterceptor.LABEL
 * 不然XlabelWeightMetadataRule在判断某个具有标签的server的时候,会报错:
 * HystrixRequestContext.initializeContext() must be called at the beginning of each request before RequestVariable functionality can be used.
 */
@Component
public class XlabelRequestPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_REQUEST_XLABEL_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        XlabelMvcHeaderInterceptor.initHystrixRequestContext(requestContext.getZuulRequestHeaders().get(CommonConstant.HEADER_LABEL));
        return null;
    }
}
