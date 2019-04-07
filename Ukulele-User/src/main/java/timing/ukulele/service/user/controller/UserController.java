package timing.ukulele.service.user.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.facade.user.api.IUserService;
import timing.ukulele.facade.user.model.data.UserDTO;
import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.facade.user.model.view.UserVO;
import timing.ukulele.service.user.service.SysUserService;
import timing.ukulele.web.pojo.ResponseCode;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController implements IUserService {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();
    @Autowired
    private SysUserService userService;

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param username 用户名
     * @return UseVo 对象
     */
    @Override
    public ResponseData<SysUser> getUserByUserName(String username) {
        SysUser user = userService.findUserByUsername(username);
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), user);
    }

    /**
     * 通过手机号查询用户及其角色信息
     *
     * @param mobile 手机号
     * @return UseVo 对象
     */
    @Override
    public ResponseData<SysUser> getUserByPhone(String mobile) {
        SysUser user = userService.findUserByMobile(mobile);
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), user);
    }

    @Override
    public ResponseVO user(UserVO userVO) {
        return null;
    }

    @Override
    public UserVO user(Integer integer) {
        return null;
    }

    @Override
    public ResponseVO userDel(Integer integer) {
        return null;
    }

    @Override
    public ResponseVO user(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseVO userUpdate(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserVO findUserByUsername(String s) {
        return null;
    }

    @Override
    public UserVO findUserByMobile(String s) {
        return null;
    }

    @Override
    public UserVO findUserByOpenId(String s) {
        return null;
    }

    @Override
    public Page userPage(Map<String, Object> map, UserVO userVO) {
        return null;
    }

    @Override
    public ResponseVO editInfo(UserDTO userDTO, UserVO userVO) {
        return null;
    }

}
