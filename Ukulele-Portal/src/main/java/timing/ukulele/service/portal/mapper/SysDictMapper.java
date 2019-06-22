package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.persistence.mapper.BaseMapper;
import timing.ukulele.service.portal.persistent.SysDict;

@Mapper
public interface SysDictMapper extends BaseMapper<SysDict> {
}
