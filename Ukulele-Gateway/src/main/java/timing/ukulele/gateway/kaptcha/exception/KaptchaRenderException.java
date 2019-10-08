package timing.ukulele.gateway.kaptcha.exception;

import java.io.IOException;

/**
 * •Kaptcha验证码渲染失败异常
 * •@className: KaptchaRenderException
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
public class KaptchaRenderException extends KaptchaException {

    public KaptchaRenderException(IOException e) {
        super(e);
    }
}
