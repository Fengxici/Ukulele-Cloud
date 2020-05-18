package timing.ukulele.service.auth;

/**
 * @author fengxici
 */
public interface Constant {

    String USER_INFO = "user_info";

    String SPRING_SECURITY_TYPE_PHONE = "phone";
    String SPRING_SECURITY_TYPE_QR = "qr";
    String SPRING_SECURITY_TYPE_THIRD = "third";

    /**
     * 登陆类型：user:用户密码登陆；phone:手机验证码登陆；qr:二维码扫码登陆；third:第三方平台
     */
    String SPRING_SECURITY_TYPE_KEY = "type";

    String SPRING_SECURITY_USERNAME_KEY = "username";
    String SPRING_SECURITY_PASSWORD_KEY = "password";

    String SPRING_SECURITY_PHONE_KEY = "phone";
    String SPRING_SECURITY_SMS_CODE_KEY = "code";

    String SPRING_SECURITY_QR_CODE_KEY = "code";
    String SPRING_SECURITY_CONNECT_ID_KEY = "connectId";
    String SPRING_SECURITY_CONNECT_USERNAME_KEY = "username";

    String SPRING_SECURITY_PLAT_CODE_KEY = "platCode";
    String SPRING_SECURITY_USER_ID_KEY = "userId";
    String SPRING_SECURITY_PLAT_TYPE_KEY = "platType";

    /**
     * 短信验证码长度
     */
    Integer SPRING_SECURITY_SMS_CODE_LENGTH = 6;

    /**
     * 二维码客户端标识长度
     */
    Integer SPRING_SECURITY_CONNECT_ID_LENGTH = 11;
}
