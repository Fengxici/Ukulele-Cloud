package timing.ukulele.gateway.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import timing.ukulele.gateway.kaptcha.exception.KaptchaException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaIncorrectException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaNotFoundException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaTimeoutException;

/**
 * •全局异常来处理
 * •@className: GlobalExceptionHandler
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = KaptchaException.class)
    public String kaptchaExceptionHandler(KaptchaException kaptchaException) {
        if (kaptchaException instanceof KaptchaIncorrectException) {
            return "验证码不正确";
        } else if (kaptchaException instanceof KaptchaNotFoundException) {
            return "验证码未找到";
        } else if (kaptchaException instanceof KaptchaTimeoutException) {
            return "验证码过期";
        } else {
            return "验证码渲染失败";
        }

    }

}
