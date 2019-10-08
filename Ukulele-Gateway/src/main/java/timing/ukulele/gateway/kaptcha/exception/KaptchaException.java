package timing.ukulele.gateway.kaptcha.exception;

/**
 * •Kaptcha验证码异常基类
 * •@className: KaptchaException
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
public class KaptchaException extends RuntimeException {

    public KaptchaException() {
        super();
    }

    public KaptchaException(Throwable e) {
        super(e);
    }
}