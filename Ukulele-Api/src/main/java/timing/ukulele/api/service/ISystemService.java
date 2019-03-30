package timing.ukulele.api.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.api.model.GlobalUserModel;
import timing.ukulele.api.model.ModuleModel;
import timing.ukulele.api.model.OAuthClientDetailsModel;
import timing.ukulele.api.model.RoleModel;
import timing.ukulele.common.data.ResponseData;

import java.util.List;

public interface ISystemService  {
    @RequestMapping(value = "/client/all", method = RequestMethod.GET)
    ResponseData<List<OAuthClientDetailsModel>> getAllClient();

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @RequestMapping(value = "/user/name/{userName}", method = RequestMethod.GET)
    ResponseData<GlobalUserModel> getUserByUserName(@PathVariable("userName") String userName);

    /**
     * 根据电话号码查询用户
     *
     * @param phone 电话号码
     * @return 用户信息
     */
    @RequestMapping(value = "/user/phone/{phone}", method = RequestMethod.GET)
    ResponseData<GlobalUserModel> getUserByPhone(@PathVariable("phone") String phone);

    /**
     * 根据userId查询菜单
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @RequestMapping(value = "/menu/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<ModuleModel>> getMenusByUserId(@PathVariable("userId") long userId);

    /**
     * 根据userId查询角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @RequestMapping(value = "/role/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<RoleModel>> getRoleByUserId(@PathVariable("userId") long userId);
}
