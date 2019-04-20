package timing.ukulele.service.portal.controller;

import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.facade.portal.api.IValidateCodeFacade;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ValidateCodeController implements IValidateCodeFacade {
    @Override
    public void createCode(String s, HttpServletResponse httpServletResponse) throws Exception {


    }
}
