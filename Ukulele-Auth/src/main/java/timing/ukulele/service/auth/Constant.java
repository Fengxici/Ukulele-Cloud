package timing.ukulele.service.auth;

public interface Constant {

     String USER_INFO = "user_info";

    String SPRING_SECURITY_RESTFUL_TYPE_PHONE = "phone";
    String SPRING_SECURITY_RESTFUL_TYPE_QR = "qr";
    String SPRING_SECURITY_RESTFUL_TYPE_THIRD = "third";

    // 登陆类型：user:用户密码登陆；phone:手机验证码登陆；qr:二维码扫码登陆；third:第三方平台
    String SPRING_SECURITY_RESTFUL_TYPE_KEY = "type";

    String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username";
    String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password";
    String SPRING_SECURITY_RESTFUL_PHONE_KEY = "phone";
    String SPRING_SECURITY_RESTFUL_VERIFY_CODE_KEY = "verifyCode";
    String SPRING_SECURITY_RESTFUL_QR_CODE_KEY = "qrCode";

    String SPRING_SECURITY_RESTFUL_PLAT_ID_KEY = "platId";
    String SPRING_SECURITY_RESTFUL_USER_ID_KEY = "userId";
    String SPRING_SECURITY_RESTFUL_PLAT_KEY = "plat";
}
