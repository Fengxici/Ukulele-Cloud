//package timing.ukulele.service.auth.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import timing.ukulele.data.user.view.UserVO;
//import timing.ukulele.facade.user.feign.IThirdPartyUserFeignFacade;
//import timing.ukulele.facade.user.feign.IUserFeignFacade;
//
//@Service
//public class ThirdPartyUserDetailService extends BaseUserDetailService {
//    @Autowired
//    IThirdPartyUserFeignFacade thirdPartyUserFeignFacade;
//    public ThirdPartyUserDetailService(IUserFeignFacade userService) {
//        super(userService);
//    }
//
//    @Override
//    protected UserVO getUser(String username) {
//        return null;
//    }
//}
