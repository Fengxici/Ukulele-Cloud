package timing.ukulele.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.user.IUserFacade;
import timing.ukulele.service.user.persistent.SysThirdpartyUser;
import timing.ukulele.service.user.persistent.SysUser;
import timing.ukulele.service.user.service.SysThirdpartyUserService;
import timing.ukulele.service.user.service.SysUserService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class UserController extends BaseController implements IUserFacade {
    private final String router = "/system/user";
    private final SysUserService userService;
    private final SysThirdpartyUserService thirdpartyUserService;

    @Autowired
    public UserController(SysUserService userService, SysThirdpartyUserService thirdpartyUserService) {
        this.userService = userService;
        this.thirdpartyUserService = thirdpartyUserService;
    }

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param username 用户名
     * @return UseVo 对象
     */
    @Override
    public ResponseData<UserVO> getUserByUserName(String username) {
        if (StringUtils.isEmpty(username))
            return paraErrorResponse();
        SysUser user = userService.findUserByUsername(username);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        if (StringUtils.isNotEmpty(user.getLabel()))
            vo.setLabel(JSON.parseArray(user.getLabel(), String.class));
        return successResponse(vo);
    }

    /**
     * 通过手机号查询用户及其角色信息
     *
     * @param mobile 手机号
     * @return UseVo 对象
     */
    @Override
    public ResponseData<UserVO> getUserByPhone(String mobile) {
        if (StringUtils.isEmpty(mobile))
            return paraErrorResponse();
        SysUser user = userService.findUserByMobile(mobile);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        if (StringUtils.isNotEmpty(user.getLabel()))
            vo.setLabel(JSON.parseArray(user.getLabel(), String.class));
        return successResponse(vo);
    }

    @Override
    public ResponseData<UserVO> getUserByPhoneOrName(String param) {
        SysUser sysUser = userService.selectUserByParam(param);
        if (null == sysUser)
            return successResponse();
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(sysUser, userVO);
        return successResponse(userVO);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<List<UserVO>> getUserByParam(Map<String, Object> map) {
        List<SysUser> list = new ArrayList<>(userService.listByMap(map));
        if (!CollectionUtils.isEmpty(list)) {
            List<UserVO> voList = new ArrayList<>(list.size());
            list.forEach(user -> {
                user.setPassword(null);
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(user, vo);
                if (StringUtils.isNotEmpty(user.getLabel()))
                    vo.setLabel(JSON.parseArray(user.getLabel(), String.class));
                voList.add(vo);
            });
            return successResponse(voList);
        }
        return successResponse();
    }

    @Override
    public ResponseData<UserVO> user(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        SysUser user = userService.getById(id);
        UserVO vo = new UserVO();
        if (user != null) {
            user.setPassword(null);
            BeanUtils.copyProperties(user, vo);
            if (StringUtils.isNotEmpty(user.getLabel()))
                vo.setLabel(JSON.parseArray(user.getLabel(), String.class));
        }
        return successResponse(vo);
    }

    @Override
    public ResponseData<UserVO> userInfo(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        SysUser user = userService.getById(id);
        UserVO vo = new UserVO();
        if (user != null) {
            BeanUtils.copyProperties(user, vo);
            if (StringUtils.isNotEmpty(user.getLabel()))
                vo.setLabel(JSON.parseArray(user.getLabel(), String.class));
        }
        return successResponse(vo);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<Boolean> userDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        Boolean success = userService.removeById(id);
        return successResponse(success);
    }

    @PostMapping("/register")
    public ResponseData<Boolean> register(@RequestBody UserVO user) {
        if (user == null || user.getId() != null)
            return paraErrorResponse();
        if (userService.findUserByUsername(user.getUsername()) != null) {
            return paraErrorResponse(false);
        }
        SysUser userPO = new SysUser();
        BeanUtils.copyProperties(user, userPO);
        userPO.setPassword(new BCryptPasswordEncoder(6).encode(user.getPassword()));//密码
        Random random = new Random();
        int s = random.nextInt(6) % (6 - 1 + 1) + 1;
        userPO.setAvatar("assets/tmp/img/" + s + ".png");//头像
        userPO.setLabel("[\"admin\"]");
        Boolean success = userService.save(userPO);
        if (StringUtils.isNotEmpty(user.getPlatId()) && null != user.getPlat()) {
            SysThirdpartyUser thirdpartyUser = new SysThirdpartyUser();
            thirdpartyUser.setDeleted(Boolean.FALSE);
            thirdpartyUser.setCreateTime(new Date());
            thirdpartyUser.setPlatId(user.getPlatId());
            thirdpartyUser.setPlatSource(user.getPlat());
            thirdpartyUser.setUserId(userPO.getId());
            thirdpartyUserService.save(thirdpartyUser);
        }
        return successResponse(success);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<Boolean> user(UserVO user) {
        if (user == null || user.getId() != null)
            return paraErrorResponse();
        SysUser userPO = new SysUser();
        BeanUtils.copyProperties(user, userPO);
        if (!CollectionUtils.isEmpty(user.getLabel()))
            userPO.setLabel(JSON.toJSONString(user.getLabel()));
        // TODO 部分属性暂时默认值
        userPO.setPassword(new BCryptPasswordEncoder(6).encode("123456"));//密码
        Random random = new Random();
        int s = random.nextInt(6) % (6 - 1 + 1) + 1;
        userPO.setAvatar("assets/tmp/img/" + s + ".png");//头像
        Boolean success = userService.save(userPO);
        return successResponse(success);
    }

    @Override
    public ResponseData<Boolean> userUpdate(UserVO user) {
        if (user == null || user.getId() == null)
            return paraErrorResponse();
        user.setAvatar(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        SysUser po = new SysUser();
        BeanUtils.copyProperties(user, po);
        if (!CollectionUtils.isEmpty(user.getLabel()))
            po.setLabel(JSON.toJSONString(user.getLabel()));
        Boolean success = userService.updateById(po);
        return successResponse(success);
    }

    @GetMapping("/page/{current}/{size}")
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<IPage<UserVO>> getPage(@PathVariable(name = "current") int current,
                                               @PathVariable(name = "size") int size, HttpServletRequest request) {
        SysUser user = Request2ModelUtil.covert(SysUser.class, request);
        if (size == 0) size = 10;
        if (current == 0) current = 1;
        IPage<UserVO> page = this.userService.getPage(user, current, size);
        return successResponse(page);
    }
}
