package timing.ukulele.service.user.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.service.user.mapper.SysThirdpartyUserMapper;
import timing.ukulele.service.user.persistent.SysThirdpartyUser;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@DS("slave")
@Service
public class SysThirdpartyUserService extends ServiceImpl<SysThirdpartyUserMapper,SysThirdpartyUser> {
    @DS("master")
    public boolean save(SysThirdpartyUser entity) {
        Date now = new Date();
        entity.setCreateTime(now);
        if (entity.getDeleted() == null) {
            entity.setDeleted(Boolean.FALSE);
        }

        return super.save(entity);
    }

    @DS("master")
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

    @DS("master")
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

    @DS("master")
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

    @DS("master")
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

    @DS("master")
    public boolean removeById(Serializable id) {
        return super.removeById(id);
    }

    @DS("master")
    public boolean removeByMap(Map<String, Object> columnMap) {
        return super.removeByMap(columnMap);
    }

    @DS("master")
    public boolean remove(Wrapper<SysThirdpartyUser> wrapper) {
        return super.remove(wrapper);
    }

    @DS("master")
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return super.removeByIds(idList);
    }

    @DS("master")
    public boolean updateById(SysThirdpartyUser entity) {
        return super.updateById(entity);
    }

    @DS("master")
    public boolean update(SysThirdpartyUser entity, Wrapper<SysThirdpartyUser> updateWrapper) {
        return super.update(entity, updateWrapper);
    }

    @DS("master")
    public boolean updateBatchById(Collection<SysThirdpartyUser> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        } else {
            return super.updateBatchById(entityList, batchSize);
        }
    }
}
