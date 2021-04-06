package timing.ukulele.service.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.service.user.mapper.SysThirdpartyUserMapper;
import timing.ukulele.service.user.persistent.SysThirdpartyUser;

import java.util.Collection;
import java.util.Date;

/**
 * @author fengxici
 */
@Service
public class SysThirdpartyUserService extends ServiceImpl<SysThirdpartyUserMapper, SysThirdpartyUser> {

    @Override
    public boolean save(SysThirdpartyUser entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        if (entity.getDeleted() == null) {
            entity.setDeleted(Boolean.FALSE);
        }

        return super.save(entity);
    }

    @Override
    public boolean saveBatch(Collection<SysThirdpartyUser> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        } else {
            Date now = new Date();
            entityList.forEach((entity) -> {
                entity.setCreateTime(now);
                if (entity.getDeleted() == null) {
                    entity.setDeleted(Boolean.FALSE);
                }

            });
            return super.saveBatch(entityList, batchSize);
        }
    }

    @Override
    public boolean saveOrUpdate(SysThirdpartyUser entity) {
        Date now = new Date();
        if (entity.getId() == null) {
            entity.setCreateTime(now);
            if (entity.getDeleted() == null) {
                entity.setDeleted(Boolean.FALSE);
            }
        }

        return super.saveOrUpdate(entity);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysThirdpartyUser> entityList) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        } else {
            Date now = new Date();
            entityList.forEach((entity) -> {
                if (entity.getId() == null) {
                    entity.setCreateTime(now);
                    if (entity.getDeleted() == null) {
                        entity.setDeleted(Boolean.FALSE);
                    }
                }

            });
            return super.saveOrUpdateBatch(entityList);
        }
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysThirdpartyUser> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        } else {
            Date now = new Date();
            entityList.forEach((entity) -> {
                if (entity.getId() == null) {
                    entity.setCreateTime(now);
                    if (entity.getDeleted() == null) {
                        entity.setDeleted(Boolean.FALSE);
                    }
                }

            });
            return super.saveOrUpdateBatch(entityList, batchSize);
        }
    }

    @Override
    public boolean updateBatchById(Collection<SysThirdpartyUser> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        } else {
            return super.updateBatchById(entityList, batchSize);
        }
    }
}
