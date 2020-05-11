package timing.ukulele.service.portal.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.SysDeptMapper;
import timing.ukulele.service.portal.persistent.SysDept;

import java.util.HashMap;
import java.util.Map;

@Service
public class SysDeptService extends BaseService<SysDeptMapper, SysDept> {
    public ResponseData<Boolean> removeDept(Long id) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("parent_id", id);
        if (!CollectionUtils.isEmpty(listByMap(filter))) {
            return new ResponseData<>(ResponseCode.BUSINESS_ERROR.getCode(), "请先删除下级部门", Boolean.FALSE);
        }
        removeById(id);
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
    }
}
