package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timing.ukulele.api.model.system.GlobalUserModel;
import timing.ukulele.persistence.service.BaseService;

import java.util.Date;
import java.util.List;


@DS("slave")
@Service
public class UserService extends BaseService<GlobalUserModel> {

    /**
     * 批量重置密码
     * @param record
     * @param newPassword
     */
    @DS("master")
    @Transactional
    public void resetPassword(List<GlobalUserModel> record, String newPassword) {
        record.forEach(e -> {
            GlobalUserModel baseUser = new GlobalUserModel();
            baseUser.setId(e.getId());
            baseUser.setPassword(new BCryptPasswordEncoder(6).encode(newPassword));
            baseUser.setUpdateTime(new Date());
            saveOrUpdate(baseUser);
        });
    }

    /**
     * 删除用户
     * @param record
     */
    @DS("master")
    @Transactional
    public void deleteBatch(List<GlobalUserModel> record) {
        deleteBatch(record);
    }
}
