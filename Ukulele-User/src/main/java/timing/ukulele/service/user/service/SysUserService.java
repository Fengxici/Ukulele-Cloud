package timing.ukulele.service.user.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.user.mapper.SysUserMapper;
import timing.ukulele.service.user.persistent.SysUser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fengxici
 */
@Slf4j
@Service
public class SysUserService extends BaseService<SysUserMapper, SysUser> {
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

    public SysUser selectUserByParam(String param) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>(new SysUser());
        queryWrapper.eq(SysUser::getPhone, param).or().eq(SysUser::getUsername, param);
        List<SysUser> list = list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        //脱敏
        list.get(0).setPassword(null);
        return list.get(0);
    }

    public IPage<UserVO> getPage(SysUser user, int current, int size) {
        Page<SysUser> page = new Page<>(current, size);
        IPage<SysUser> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(user));
        Page<UserVO> voPage = new Page<>(current, size);
        if (iPage != null && !CollectionUtils.isEmpty(iPage.getRecords())) {
            List<UserVO> voList = new ArrayList<>(iPage.getRecords().size());
            iPage.getRecords().forEach(po -> {
                UserVO vo = new UserVO();
                po.setPassword(null);
                BeanUtils.copyProperties(po, vo);
                if (StringUtils.isNotEmpty(po.getLabel())) {
                    vo.setLabel(JSON.parseArray(po.getLabel(), String.class));
                }
                voList.add(vo);
            });
            voPage.setRecords(voList);
        }
        return voPage;
    }
}
