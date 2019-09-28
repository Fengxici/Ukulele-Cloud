package timing.ukulele.service.auth.service;

import org.springframework.stereotype.Service;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.user.feign.IUserFeignFacade;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(IUserFeignFacade userService) {
        super(userService);
    }

    @Override
    protected UserVO getUser(String qrCode) {
        return null;
    }
}