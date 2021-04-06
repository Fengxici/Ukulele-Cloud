package timing.ukulele.service.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import timing.ukulele.persistence.mapper.BaseMapper;
import timing.ukulele.service.user.persistent.SysUser;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author fengxici
 */
@Mapper
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户名查询用户信息（含有角色信息）
     *
     * @param username 用户名
     * @return SysUser
     */
    SysUser selectUserVoByUsername(String username);


    /**
     * 通过手机号查询用户信息（含有角色信息）
     *
     * @param mobile 用户名
     * @return SysUser
     */
    SysUser selectUserVoByMobile(String mobile);
}
