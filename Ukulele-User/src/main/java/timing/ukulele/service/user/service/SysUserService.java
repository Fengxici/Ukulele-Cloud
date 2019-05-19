package timing.ukulele.service.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class SysUserService extends BaseService<SysUser> {
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

    public IPage<SysUser> getPage(SysUser user, int current, int size) {
        Page<SysUser> page = new Page<>(current, size);
        IPage<SysUser> iPage=this.baseMapper.selectPage(page, new QueryWrapper<>(user));
        return page.setRecords(iPage.getRecords());
    }
}
