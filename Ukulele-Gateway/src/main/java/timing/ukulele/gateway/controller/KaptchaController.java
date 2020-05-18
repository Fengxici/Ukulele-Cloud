package timing.ukulele.gateway.controller;

import org.springframework.web.bind.annotation.*;
import timing.ukulele.gateway.kaptcha.Kaptcha;

/**
 * •Kaptcha验证码接口
 * •@className: KaptchaController
 * •@author: 吕自聪
 * •@date: 2019/10/8
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    private final Kaptcha kaptcha;

    public KaptchaController(Kaptcha kaptcha) {
        this.kaptcha = kaptcha;
    }

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }

    @PostMapping("/validTime")
    public void validCustomTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }

}
