package timing.ukulele.service.user.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.user.api.IUserFacade;
import timing.ukulele.facade.user.model.data.UserDTO;
import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.facade.user.model.view.UserVO;
import timing.ukulele.service.user.service.SysUserService;
import timing.ukulele.web.controller.BaseController;

import java.util.List;
import java.util.Map;

@RestController
public class UserController extends BaseController implements IUserFacade {
    private final SysUserService userService;

    @Autowired
    public UserController(SysUserService userService) {
        this.userService = userService;
    }

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param username 用户名
     * @return UseVo 对象
     */
    @Override
    public ResponseData<SysUser> getUserByUserName(String username) {
        if (StringUtils.isEmpty(username))
            return paraErrorResponse();
        SysUser user = userService.findUserByUsername(username);
        return successResponse(user);
    }

    /**
     * 通过手机号查询用户及其角色信息
     *
     * @param mobile 手机号
     * @return UseVo 对象
     */
    @Override
    public ResponseData<SysUser> getUserByPhone(String mobile) {
        if (StringUtils.isEmpty(mobile))
            return paraErrorResponse();
        SysUser user = userService.findUserByMobile(mobile);
        return successResponse(user);
    }

    @Override
    public ResponseData<List<SysUser>> getUserByParam(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseData<UserVO> user(UserVO userVO) {
        return null;
    }

    @Override
    public ResponseData<UserVO> user(Integer integer) {
        return null;
    }

    @Override
    public ResponseData<Boolean> userDel(Integer integer) {
        return null;
    }

    @Override
    public ResponseData<Boolean> user(UserDTO userDTO) {
        return null;
    }

    @Override
    public ResponseData<Boolean> userUpdate(UserDTO userDTO) {
        return null;
    }


}
