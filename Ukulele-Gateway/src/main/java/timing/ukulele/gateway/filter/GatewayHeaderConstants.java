package timing.ukulele.gateway.filter;

import timing.ukulele.ribbon.RibbonConstant;

public interface GatewayHeaderConstants extends RibbonConstant {
    /**
     * 用户信息头
     */
    String USER_HEADER = "x-user-header";
    /**
     * 角色信息头
     */
    String ROLE_HEADER = "x-role-header";
//    /**
////     * 标签 header key
////     */
////    String HEADER_LABEL = "x-label";
    /**
     * token请求头名称
     */
    String TOKEN_HEADER = "Authorization";
}
