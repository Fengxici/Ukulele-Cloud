package timing.ukulele.service.system.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import timing.ukulele.api.model.*;
import timing.ukulele.api.service.ISystemService;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.TableData;
import timing.ukulele.service.system.service.*;
import timing.ukulele.web.pojo.ResponseCode;

import java.util.*;

@Slf4j
@RestController
public final class SystemController implements ISystemService {

    private final RoleService roleService;

    private final RoleModuleService roleModuleService;

    private final UserRoleService userRoleService;

    private final OauthClientDetailsService oauthClientDetailsService;

    private final ModuleService baseModuleResourceService;

    private final RoleModuleService baseRoleModuleService;

    private final SystemService systemService;

    private final UserService userService;

    @Autowired
    public SystemController(RoleService roleService, RoleModuleService roleModuleService, UserRoleService userRoleService, OauthClientDetailsService oauthClientDetailsService, ModuleService baseModuleResourceService, RoleModuleService baseRoleModuleService, SystemService systemService, UserService userService) {
        this.roleService = roleService;
        this.roleModuleService = roleModuleService;
        this.userRoleService = userRoleService;
        this.oauthClientDetailsService = oauthClientDetailsService;
        this.baseModuleResourceService = baseModuleResourceService;
        this.baseRoleModuleService = baseRoleModuleService;
        this.systemService = systemService;
        this.userService = userService;
    }


    @Override
    public ResponseData<List<RoleModel>> getRoleByUserId(@PathVariable("userId") long userId) {
        log.debug("根据用户查询角色");
        List<RoleModel> list;
        try {
            list = roleService.getRoleByUserId(userId);
        } catch (Exception e) {
            log.error("根据用户查询角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @GetMapping("/role")
    public ResponseData<List<RoleModel>> getAllRole() {
        log.debug("获取所有角色");
        List<RoleModel> list;
        try {
            list = roleService.getAll();
        } catch (Exception e) {
            log.error("获取所有角色失败");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/role/table")
    protected ResponseData<TableData<RoleModel>> queryRoleRecord(@RequestBody RoleModel query) {
        log.debug("查询角色表格");
//        PageInfo<RoleModel> pageInfo = roleService.pageInfo(query,null,null);
        IPage<RoleModel> pageInfo = roleService.pageInfo(query, null, null);
        TableData<RoleModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getRecords());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    @PostMapping("/role")
    protected ResponseData<RoleModel> addRoleRecord(@RequestBody RoleModel record) {
        log.debug("添加角色");
        try {
            record.setId(IdWorker.getId());
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            roleService.save(record);
        } catch (Exception e) {
            log.error("添加角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/role")
    protected ResponseData<RoleModel> deleteRoleRecord(@RequestBody List<RoleModel> record) {
        log.debug("删除角色");
        try {
            List<Long> ids = new ArrayList<>();
            record.forEach(role -> ids.add(role.getId()));
            roleService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/role")
    protected ResponseData<RoleModel> updateRoleRecord(@RequestBody RoleModel record) {
        log.debug("更新角色");
        try {
            record.setUpdateTime(new Date());
            roleService.updateById(record);
        } catch (Exception e) {
            log.error("更新角色失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/role/validate/{roleCode}")
    public ResponseData<RoleModel> validateRoleCode(@PathVariable("roleCode") String roleCode) {
        log.debug("校验角色编码是否存在");
        RoleModel baseRole = new RoleModel();
        baseRole.setRoleCode(roleCode);
        Map<String, Object> segment = new HashMap<>();
        segment.put("role_code", roleCode);
        baseRole = roleService.queryOneByParam(segment);
        if (baseRole == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @GetMapping("/role/auth/{roleId}")
    public ResponseData<List<RoleModuleModel>> getAuthModule(@PathVariable("roleId") String roleId) {
        log.debug("查询角色已授权模块");
        List<RoleModuleModel> list;
        try {
            list = roleModuleService.selectLeafRoleModule(roleId);
        } catch (Exception e) {
            log.error("查询角色已授权模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @GetMapping("/user/role/{userId}")
    public ResponseData<List<UserRoleModel>> saveUserRole(@PathVariable("userId") String userId) {
        log.debug("获取用户授权角色");
        List<UserRoleModel> list;
        try {
            Map<String, Object> segment = new HashMap<>();
            segment.put("user_id", userId);
            list = userRoleService.listByParam(segment);
        } catch (Exception e) {
            log.error("获取用户授权角色失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/user/role")
    public ResponseData<GlobalUserModel> saveUserRole(@RequestBody List<UserRoleModel> baseUserRoleList) {
        log.debug("保存用户授权角色");
        try {
            userRoleService.saveUserRole(baseUserRoleList);
        } catch (Exception e) {
            log.error("保存用户授权角色失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PostMapping("/client/table")
    protected ResponseData<TableData<OAuthClientDetailsModel>> queryClientRecord(@RequestBody OAuthClientDetailsModel query) {
        log.debug("查询客户端表格");
        PageInfo<OAuthClientDetailsModel> pageInfo = oauthClientDetailsService.pageInfo(query);
        TableData<OAuthClientDetailsModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getList());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    @PostMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> addClientRecord(@RequestBody OAuthClientDetailsModel record) {
        log.debug("添加客户端应用");
        try {
            oauthClientDetailsService.save(record);
        } catch (Exception e) {
            log.error("添加客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> deleteClientRecord(@RequestBody List<OAuthClientDetailsModel> record) {
        log.debug("删除客户端应用");
        try {
            List<String> ids = new ArrayList<>();
            record.forEach(model -> ids.add(model.getClientId()));
            oauthClientDetailsService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> updateClientRecord(@RequestBody OAuthClientDetailsModel record) {
        log.debug("更新客户端应用");
        try {
            oauthClientDetailsService.saveOrUpdate(record);
        } catch (Exception e) {
            log.error("更新客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/client/validate/{clientId}")
    protected ResponseData<OAuthClientDetailsModel> validateClientId(@PathVariable("clientId") String clientId) {
        log.debug("校验应用id是否存在");
        OAuthClientDetailsModel oauthClientDetails = new OAuthClientDetailsModel();
        oauthClientDetails.setClientId(clientId);
        oauthClientDetails = oauthClientDetailsService.queryOneByParam(oauthClientDetails);
        if (oauthClientDetails == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @Override
    public ResponseData<List<OAuthClientDetailsModel>> getAllClient() {
        log.debug("获取所有客户端应用");
        List<OAuthClientDetailsModel> list;
        try {
            list = oauthClientDetailsService.getAll();
        } catch (Exception e) {
            log.error("获取所有客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }
    
    @Override
    public ResponseData<List<ModuleModel>> getMenusByUserId(@PathVariable("userId") long userId) {
        log.debug("根据用户查询菜单");
        List<ModuleModel> list;
        try {
            list = baseModuleResourceService.getMenusByUserId(userId);
        } catch (Exception e) {
            log.error("根据用户查询菜单错误");
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @GetMapping("/menu/{userId}")
    public ResponseData<List<ModuleModel>> getCurrentMenu(@PathVariable("userId") long userId) {
        log.debug("查询当前用户菜单");
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), baseModuleResourceService.getMenusByUserId(userId));
    }

    @PostMapping(value = "/module/tree")
    private ResponseData<List<ModuleModel>> getModuleTree(@RequestBody ModuleModel moduleResources) {
        log.debug("查询模块树");
        List<ModuleModel> list;
        try {
            list = baseModuleResourceService.getModuleTree(moduleResources.getId(), moduleResources.getSystemId());
        } catch (Exception e) {
            log.error("查询模块树异常" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @PostMapping("/module/table")
    protected ResponseData<TableData<ModuleModel>> queryModuleRecord(@RequestBody ModuleModel query) {
        log.debug("查询模块表格");
//        PageInfo<ModuleModel> pageInfo = baseModuleResourceService.pageInfo(query,null,null);
        IPage<ModuleModel> pageInfo = baseModuleResourceService.pageInfo(query, null, null);
        TableData<ModuleModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getRecords());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    @PostMapping("/module")
    protected ResponseData<ModuleModel> addModuleRecord(@RequestBody ModuleModel record) {
        log.debug("添加模块");
        try {
            record.setId(IdWorker.getId());
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            baseModuleResourceService.save(record);
        } catch (Exception e) {
            log.error("添加模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/module")
    protected ResponseData<ModuleModel> deleteModuleRecord(@RequestBody List<ModuleModel> record) {
        log.debug("删除模块");
        try {
            List<Long> ids = new ArrayList<>();
            record.forEach(item -> ids.add(item.getId()));
            baseModuleResourceService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/module")
    protected ResponseData<ModuleModel> updateModuleRecord(@RequestBody ModuleModel record) {
        log.debug("更新模块");
        try {
            record.setUpdateTime(new Date());
            baseModuleResourceService.updateById(record);
        } catch (Exception e) {
            log.error("更新模块失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/module/validate/{moduleCode}")
    public ResponseData<ModuleModel> validateModuleCode(@PathVariable("moduleCode") String moduleCode) {
        log.debug("校验模块编码是否存在");
        ModuleModel baseModuleResources = new ModuleModel();
        baseModuleResources.setModuleCode(moduleCode);
        Map<String, Object> segment = new HashMap<>();
        segment.put("module_code", moduleCode);
        baseModuleResources = baseModuleResourceService.queryOneByParam(segment);
        if (baseModuleResources == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @PostMapping("/module/role")
    public ResponseData saveRoleResourcesAuth(@RequestBody List<RoleModuleModel> roleModule) {
        log.debug("保存角色权限");
        try {
            baseRoleModuleService.saveRoleModule(roleModule);
        } catch (RuntimeException e) {
            log.error("保存角色权限失败" + e.getMessage());
            e.printStackTrace();
            return new ResponseData(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }

        return new ResponseData(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }


    @PostMapping("/system/table")
    protected ResponseData<TableData<SystemModel>> querySystemRecord(@RequestBody SystemModel query) {
        log.debug("查询系统表格");
//        PageInfo<SystemModel> pageInfo = systemService.pageInfo(query,null,null);
        IPage<SystemModel> pageInfo = systemService.pageInfo(query, null, null);
        TableData<SystemModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getRecords());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    @PostMapping("/system")
    protected ResponseData<SystemModel> addSystemRecord(@RequestBody SystemModel record) {
        log.debug("添加系统");
        try {
            record.setId(IdWorker.getId());
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            systemService.save(record);
        } catch (Exception e) {
            log.error("添加系统失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/system")
    protected ResponseData<SystemModel> deleteSystemRecord(@RequestBody List<SystemModel> record) {
        log.debug("删除系统");
        try {
            List<Long> ids = new ArrayList<>();
            record.forEach(system -> ids.add(system.getId()));
            systemService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除系统失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/system")
    protected ResponseData<SystemModel> updateSystemRecord(@RequestBody SystemModel record) {
        log.debug("更新系统");
        try {
            record.setUpdateTime(new Date());
            systemService.updateById(record);
        } catch (Exception e) {
            log.error("更新系统失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/system/validate/{projectName}")
    public ResponseData<SystemModel> validateProjectName(@PathVariable("projectName") String projectName) {
        log.debug("校验系统项目名是否存在");
        SystemModel baseSystem = new SystemModel();
        baseSystem.setProjectName(projectName);
        Map<String, Object> segment = new HashMap<>();
        segment.put("project_name", projectName);
        baseSystem = systemService.queryOneByParam(segment);
        if (baseSystem == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @GetMapping("/system")
    public ResponseData<List<SystemModel>> getSystem() {
        log.debug("查询所有系统");
        List<SystemModel> list;
        try {
            list = systemService.getAll();
        } catch (Exception e) {
            log.error("查询所有系统失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @GetMapping("/system/module")
    public ResponseData<List<ModuleAndSystemModel>> getModuleAndSystem() {
        log.debug("查询系统及模块树");
        List<ModuleAndSystemModel> list;
        try {
            list = systemService.selectModuleAndSystem();
        } catch (Exception e) {
            log.error("查询系统及模块树失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public ResponseData<GlobalUserModel> getUserByUserName(@PathVariable("userName") String userName) {
        log.debug("根据用户名查询用户");
        if (StringUtils.isEmpty(userName)) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        Map<String, Object> segment = new HashMap<>();
        segment.put("account_", userName);
        GlobalUserModel baseUser = userService.queryOneByParam(segment);
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), baseUser);
    }

    /**
     * 根据电话号码查询用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public ResponseData<GlobalUserModel> getUserByPhone(@PathVariable("phone") String phone) {
        log.debug("根据电话号码查询用户");
        if (StringUtils.isEmpty(phone)) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        Map<String, Object> segment = new HashMap<>();
        segment.put("phone_", phone);
        GlobalUserModel baseUser = userService.queryOneByParam(segment);
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), baseUser);
    }

    @PostMapping("/user/table")
    protected ResponseData<TableData<GlobalUserModel>> queryGlobalUserRecord(@RequestBody GlobalUserModel query) {
        log.debug("查询用户");
//        PageInfo<GlobalUserModel> pageInfo = userService.pageInfo(query, null, null);
        IPage<GlobalUserModel> pageInfo = userService.pageInfo(query, null, null);
        TableData<GlobalUserModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getRecords());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }


    @PostMapping("/user")
    protected ResponseData<GlobalUserModel> addUserRecord(@RequestBody GlobalUserModel record) {
        log.debug("添加用户");
        try {
            log.debug("用户密码加密");
            record.setId(IdWorker.getId());
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setPassword(new BCryptPasswordEncoder(6).encode(record.getPassword()));
            userService.saveOrUpdate(record);
        } catch (Exception e) {
            log.error("添加用户失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/user")
    protected ResponseData<GlobalUserModel> deleteUserRecord(@RequestBody List<GlobalUserModel> record) {
        log.debug("删除用户");
        try {
            userService.deleteBatch(record);
        } catch (Exception e) {
            log.error("删除用户失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/user")
    protected ResponseData<GlobalUserModel> updateUserRecord(@RequestBody GlobalUserModel record) {
        log.debug("更新用户");
        try {
            record.setPassword(null);
            record.setUpdateTime(new Date());
            userService.saveOrUpdate(record);
        } catch (Exception e) {
            log.error("更新用户失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    /**
     * 重置密码
     *
     * @param record
     * @return
     */
    @PostMapping("/user/password/{newPassword}")
    public ResponseData<GlobalUserModel> resetPassword(@RequestBody List<GlobalUserModel> record, @PathVariable("newPassword") String newPassword) {
        log.debug("重置密码");
        try {
            userService.resetPassword(record, newPassword);
        } catch (Exception e) {
            log.error("重置密码用户失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/user/validate/{userName}")
    public ResponseData<GlobalUserModel> validateUserName(@PathVariable("userName") String userName) {
        log.debug("校验用户名是否存在");
        Map<String, Object> segment = new HashMap<>();
        segment.put("account_", userName);
        GlobalUserModel baseUser = userService.queryOneByParam(segment);
        if (baseUser == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @GetMapping("/user/validate/phone/{phone}")
    public ResponseData<GlobalUserModel> validatePhone(@PathVariable("phone") String phone) {
        log.debug("校验手机号码是否存在");
        Map<String, Object> segment = new HashMap<>();
        segment.put("phone_", phone);
        GlobalUserModel baseUser = userService.queryOneByParam(segment);
        if (baseUser == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

}
