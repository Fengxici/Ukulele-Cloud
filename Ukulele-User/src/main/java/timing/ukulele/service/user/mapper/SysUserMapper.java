package timing.ukulele.service.user.mapper;

import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.facade.user.model.view.UserVO;
import timing.ukulele.persistence.mapper.BaseMapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户名查询用户信息（含有角色信息）
     *
     * @param username 用户名
     * @return userVo
     */
    SysUser selectUserVoByUsername(String username);


    /**
     * 通过手机号查询用户信息（含有角色信息）
     *
     * @param mobile 用户名
     * @return userVo
     */
    SysUser selectUserVoByMobile(String mobile);
}
