package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import timing.ukulele.persistence.mapper.BaseMapper;
import timing.ukulele.service.portal.persistent.SysDictIndex;

@Mapper
@Repository
public interface SysDictIndexMapper extends BaseMapper<SysDictIndex> {
}
