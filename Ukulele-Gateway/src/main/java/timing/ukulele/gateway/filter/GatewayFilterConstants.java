package timing.ukulele.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import timing.ukulele.gateway.filter.pre.PreRequestLogFilter;

public interface GatewayFilterConstants {

    /**
     * Filter Order for {@link PreRequestLogFilter#filterOrder()}
     */
    int PRE_REQUEST_LOG_ORDER = Integer.MAX_VALUE;

    /**
     * {@link ZuulFilter#filterType()} pre type.
     */
    String PRE_TYPE = "pre";

}
