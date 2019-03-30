package timing.ukulele.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.api.model.GlobalUserModel;
import timing.ukulele.persistence.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<GlobalUserModel> {
}