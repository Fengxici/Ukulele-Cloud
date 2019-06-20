package timing.ukulele.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.JsonUtils;
import timing.ukulele.facade.user.api.IUserFacade;
import timing.ukulele.facade.user.model.view.UserVO;
import timing.ukulele.service.user.persistent.SysUser;
import timing.ukulele.service.user.service.SysUserService;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public ResponseData<UserVO> getUserByUserName(String username) {
        if (StringUtils.isEmpty(username))
            return paraErrorResponse();
        SysUser user = userService.findUserByUsername(username);
        user.setPassword(null);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
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
        user.setPassword(null);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return successResponse(vo);
    }

    @Override
    public ResponseData<List<UserVO>> getUserByParam(Map<String, Object> map) {
        List<SysUser> list = new ArrayList<>(userService.listByMap(map));
        if (!CollectionUtils.isEmpty(list)) {
            List<UserVO> voList = new ArrayList<>(list.size());
            list.forEach(user -> {
                user.setPassword(null);
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(user, vo);
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
    public ResponseData<Boolean> userDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        Boolean success = userService.removeById(id);
        return successResponse(success);
    }

    @Override
    public ResponseData<Boolean> user(UserVO user) {
        if (user == null || user.getId() != null)
            return paraErrorResponse();
        SysUser userPO = new SysUser();
        BeanUtils.copyProperties(user, userPO);
        if (!CollectionUtils.isEmpty(user.getLabel()))
            userPO.setLabel(Arrays.toString(user.getLabel().toArray()));
        // TODO 部分属性暂时默认值
        userPO.setPassword(new BCryptPasswordEncoder(6).encode("123456"));//密码
        Random random = new Random();
        int s = random.nextInt(6) % (6 - 1 + 1) + 1;
        userPO.setAvatar("assets/tmp/img/" + s + ".png");//头像
        userPO.setLabel("admin,super");
        userPO.setDeptId(1L);
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
            po.setLabel(Arrays.toString(user.getLabel().toArray()));
        Boolean success = userService.updateById(po);
        return successResponse(success);
    }

    @GetMapping("/page/{current}/{size}")
    public ResponseData<IPage<UserVO>> getPage(@PathVariable(name = "current") int current,
                                               @PathVariable(name = "size") int size, HttpServletRequest request) {
        SysUser user = Request2ModelUtil.covert(SysUser.class, request);
        if (size == 0) size = 10;
        if (current == 0) current = 1;
        IPage<UserVO> page = this.userService.getPage(user, current, size);
        return successResponse(page);
    }
}
