package timing.ukulele.service.auth.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import timing.ukulele.common.constant.LoginTypeEnum;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.user.view.ThirdPartyUserVO;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.user.feign.IThirdPartyUserFeignFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;
import timing.ukulele.http.OkHttpManager;
import timing.ukulele.service.auth.BaseUserDetail;
import timing.ukulele.service.auth.Constant;
import timing.ukulele.service.auth.model.WxAppSessionResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fengxici
 */
@Service
@Slf4j
public class TimingUserDetailService implements UserDetailsService {
    @Value("${wx.appid}")
    private String wxAppid;
    @Value("${wx.appsecret}")
    private String wxSecret;
    private final IUserFeignFacade userService;
    private final IThirdPartyUserFeignFacade thirdPartyUserService;

    @Autowired
    public TimingUserDetailService(IUserFeignFacade userService, IThirdPartyUserFeignFacade thirdPartyUserService) {
        this.userService = userService;
        this.thirdPartyUserService = thirdPartyUserService;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 账号密码登陆调用FeignClient根据用户名查询用户
        ResponseData<UserVO> baseUserResponseData = userService.getUserByUserName(username);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        UserVO baseUser = baseUserResponseData.getData();
        return getUserDetails(baseUser, LoginTypeEnum.PASSWORD.getValue());
    }

    /**
     * 根据手机号查询用户
     *
     * @param mobile 手机号码
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByPhone(String mobile) {
        // 手机验证码调用FeignClient根据电话号码查询用户
        ResponseData<UserVO> baseUserResponseData = userService.getUserByPhone(mobile);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，手机号码：" + mobile);
            throw new UsernameNotFoundException("找不到该用户，手机号码：" + mobile);
        }
        UserVO user = baseUserResponseData.getData();
        return getUserDetails(user, LoginTypeEnum.PHONE.getValue());
    }

    /**
     * 根据第三方开放平台code获取用户
     *
     * @param code 第三方平台
     * @param type 类型
     * @return
     */
    public UserDetails loadUserByThirdOpenCode(String code, Integer type) {
        if (!StringUtils.hasLength(code) || null == type) {
            log.error("传入的第三方数据错误");
            throw new UsernameNotFoundException("传入的第三方数据错误");
        }
        String openId = code2OpenId(code);
        if (!StringUtils.hasLength(openId)) {
            log.error("获取openId错误");
            throw new UsernameNotFoundException("获取openId错误");
        }
        ResponseData<ThirdPartyUserVO> userByThirdInfo = thirdPartyUserService.getUserByThirdInfo(openId, type);
        if (userByThirdInfo == null || userByThirdInfo.getData() == null || userByThirdInfo.getData().getId() == null) {
            log.error("找不到该用户，用户名：" + openId);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + openId);
        }
        ResponseData<UserVO> baseUserResponseData = userService.userInfo(userByThirdInfo.getData().getUserId());
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + userByThirdInfo.getData().getUserId());
            throw new UsernameNotFoundException("找不到该用户，用户名：" + userByThirdInfo.getData().getUserId());
        }
        UserVO user = baseUserResponseData.getData();
        return getUserDetails(user, LoginTypeEnum.THIRD_OPEN.getValue());
    }

    /**
     * 生成用户信息
     *
     * @param baseUser
     * @param type
     * @return
     */
    private UserDetails getUserDetails(UserVO baseUser, Integer type) {
        List<String> roles;
        if (!CollectionUtils.isEmpty(baseUser.getLabel())) {
            roles = baseUser.getLabel();
        } else {
            roles = new ArrayList<>(0);
        }

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(roles);
        boolean enabled = baseUser.getDeleted() != null && !baseUser.getDeleted();
        // 返回带有用户权限信息的User
        User user = new User(baseUser.getUsername(),
                baseUser.getPassword(), enabled, true, true, true, authorities);

        return new BaseUserDetail(baseUser, user, type);
    }

//    private UserVO getUser(String principal) {
//        if (StringUtils.isEmpty(principal)) {
//            log.error("用户信息未传");
//            throw new UsernameNotFoundException("用户信息未传");
//        }
//        String[] parameter = principal.split(Constant.SPRING_SECURITY_RESTFUN_USERNAME_SPLIT);
//        if (parameter.length == 1) {
//            // 账号密码登陆调用FeignClient根据用户名查询用户
//            ResponseData<UserVO> baseUserResponseData = userService.getUserByUserName(parameter[0]);
//            if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
//                log.error("找不到该用户，用户名：" + parameter[0]);
//                throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
//            }
//            return baseUserResponseData.getData();
//        } else {
//            if (Constant.SPRING_SECURITY_TYPE_PHONE.equals(parameter[1])) {//手机
//            } else if (Constant.SPRING_SECURITY_TYPE_QR.equals(parameter[1])) {//二维码
//
//            } else if (Constant.SPRING_SECURITY_TYPE_THIRD.equals(parameter[1])) {//第三方
//                if (parameter.length != 3) {
//                    log.error("传入的第三方数据错误");
//                    throw new UsernameNotFoundException("传入的第三方数据错误");
//                }
//                String platId = parameter[0];
//                String plat = parameter[2];
//                ResponseData<ThirdPartyUserVO> userByThirdInfo = thirdPartyUserService.getUserByThirdInfo(platId, Integer.valueOf(plat));
//                if (userByThirdInfo == null || userByThirdInfo.getData() == null || userByThirdInfo.getData().getId() == null) {
//                    log.error("找不到该用户，用户名：" + parameter[0]);
//                    throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
//                }
//                ResponseData<UserVO> baseUserResponseData = userService.userInfo(userByThirdInfo.getData().getUserId());
//                if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
//                    log.error("找不到该用户，用户名：" + parameter[0]);
//                    throw new UsernameNotFoundException("找不到该用户，用户名：" + parameter[0]);
//                }
//                return baseUserResponseData.getData();
//            }
//            return null;
//        }
//    }

    /**
     * 获取微信小程序openid
     */
    private String code2OpenId(String code) {
        if (org.apache.commons.lang.StringUtils.isEmpty(code)) {
            return null;
        }
        String code2SessionUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", wxAppid, wxSecret, code);
        Request request = new Request.Builder()
                .url(code2SessionUrl).get()
                .build();
        try {
            Response response = OkHttpManager.INSTANCE.build(null).getClient().newCall(request).execute();
            if (response.isSuccessful()) {
                try {
                    String responseBody = response.body().string();
                    WxAppSessionResponse model = JSON.parseObject(responseBody, new TypeReference<WxAppSessionResponse>() {
                    });
                    if (model != null && model.getOpenid() != null && model.getOpenid().length() > 0) {
                        return model.getOpenid();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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