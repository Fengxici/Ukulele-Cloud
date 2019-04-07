package timing.ukulele.service.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.user.mapper.SysUserMapper;

@Slf4j
@Service
public final class SysUserService extends BaseService<SysUser> {
    private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    private final SysUserMapper sysUserMapper;

    @Autowired
    public SysUserService(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    public SysUser findUserByUsername(String username) {
        return sysUserMapper.selectUserVoByUsername(username);
    }

    /**
     * 通过手机号查询用户信息
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    public SysUser findUserByMobile(String mobile) {
        return sysUserMapper.selectUserVoByMobile(mobile);
    }
}
