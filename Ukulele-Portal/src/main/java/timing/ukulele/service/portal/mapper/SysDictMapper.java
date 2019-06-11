package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.facade.portal.model.persistent.SysDict;
import timing.ukulele.persistence.mapper.BaseMapper;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {
}
