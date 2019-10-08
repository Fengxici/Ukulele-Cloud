package timing.ukulele.gateway.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import timing.ukulele.gateway.kaptcha.exception.KaptchaIncorrectException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaNotFoundException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaRenderException;
import timing.ukulele.gateway.kaptcha.exception.KaptchaTimeoutException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE;
import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * •谷歌默认验证码组件
 * •@className: GoogleKaptcha
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
@Slf4j
public class GoogleKaptcha implements Kaptcha {

    private DefaultKaptcha kaptcha;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public GoogleKaptcha(DefaultKaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    @Override
    public String render() {
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store, no-cache, must-revalidate");
        response.addHeader(HttpHeaders.CACHE_CONTROL, "post-check=0, pre-check=0");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setContentType("image/jpeg");
        String sessionCode = kaptcha.createText();
        try (ServletOutputStream out = response.getOutputStream()) {
            request.getSession().setAttribute(KAPTCHA_SESSION_KEY, sessionCode);
            request.getSession().setAttribute(KAPTCHA_SESSION_DATE, System.currentTimeMillis());
            ImageIO.write(kaptcha.createImage(sessionCode), "jpg", out);
            return sessionCode;
        } catch (IOException e) {
            throw new KaptchaRenderException(e);
        }
    }

    @Override
    public boolean validate(String code) {
        return validate(code, 900);
    }

    @Override
    public boolean validate(@NonNull String code, long second) {
        HttpSession httpSession = request.getSession(false);
        String sessionCode;
        if (httpSession != null && (sessionCode = (String) httpSession.getAttribute(KAPTCHA_SESSION_KEY)) != null) {
            if (sessionCode.equalsIgnoreCase(code)) {
                long sessionTime = (long) httpSession.getAttribute(KAPTCHA_SESSION_DATE);
                long duration = (System.currentTimeMillis() - sessionTime) / 1000;
                if (duration < second) {
                    httpSession.removeAttribute(KAPTCHA_SESSION_KEY);
                    httpSession.removeAttribute(KAPTCHA_SESSION_DATE);
                    return true;
                } else {
                    throw new KaptchaTimeoutException();
                }
            } else {
                throw new KaptchaIncorrectException();
            }
        } else {
            throw new KaptchaNotFoundException();
        }
    }

}