package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.user.view.ThirdPartyUserVO;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.user.feign.IThirdPartyUserFeignFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;
import timing.ukulele.service.auth.BaseUserDetail;
import timing.ukulele.service.auth.Constant;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TimingUserDetailService implements UserDetailsService {

    private final IUserFeignFacade userService;
    private final IThirdPartyUserFeignFacade thirdPartyUserService;

    @Autowired
    public TimingUserDetailService(IUserFeignFacade userService, IThirdPartyUserFeignFacade thirdPartyUserService) {
        this.userService = userService;
        this.thirdPartyUserService = thirdPartyUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        UserVO baseUser = getUser(var1);

        List<String> roles;
        if (!StringUtils.isEmpty(baseUser.getLabel()))
            roles = baseUser.getLabel();
        else
            roles = new ArrayList<>(0);

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(roles);
        boolean enabled = baseUser.getDeleted() != null && !baseUser.getDeleted();
        // 返回带有用户权限信息的User
        User user = new User(baseUser.getUsername(),
                baseUser.getPassword(), enabled, true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected UserVO getUser(String principal) {
        if (StringUtils.isEmpty(principal)) {
            log.error("用户信息未传");
            throw new UsernameNotFoundException("用户信息未传");
        }
        String[] parameter = principal.split("#=#");
        if (parameter.length == 1) {
            // 账号密码登陆调用FeignClient根据用户名查询用户
            ResponseData<UserVO> baseUserResponseData = userService.getUserByUserName(parameter[0]);
            if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
                log.error("找不到该用户，用户名：" + parameter[0]);
                throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
            }
            return baseUserResponseData.getData();
        } else {
            if (Constant.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(parameter[1])) {//手机
                // 手机验证码调用FeignClient根据电话号码查询用户
                ResponseData<UserVO> baseUserResponseData = userService.getUserByPhone(parameter[1]);
                if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
                    log.error("找不到该用户，手机号码：" + parameter[1]);
                    throw new UsernameNotFoundException("找不到该用户，手机号码：" + parameter[1]);
                }
                return baseUserResponseData.getData();
            } else if (Constant.SPRING_SECURITY_RESTFUL_TYPE_QR.equals(parameter[1])) {//二维码

            } else if (Constant.SPRING_SECURITY_RESTFUL_TYPE_THIRD.equals(parameter[1])) {//第三方
                if (parameter.length != 3) {
                    log.error("传入的第三方数据错误");
                    throw new UsernameNotFoundException("传入的第三方数据错误");
                }
                ResponseData<ThirdPartyUserVO> userByThirdInfo = thirdPartyUserService.getUserByThirdInfo(parameter[0], Integer.getInteger(parameter[2]));
                if (userByThirdInfo == null || userByThirdInfo.getData() == null || userByThirdInfo.getData().getId() == null) {
                    log.error("找不到该用户，用户名：" + parameter[0]);
                    throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
                }
                ResponseData<UserVO> baseUserResponseData = userService.getUserById(userByThirdInfo.getData().getId());
                if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
                    log.error("找不到该用户，用户名：" + parameter[0]);
                    throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
                }
                return baseUserResponseData.getData();
            }
            return null;
        }
    }


    private List<GrantedAuthority> convertToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.toUpperCase());
            authorities.add(authority);
        });
        return authorities;
    }
}