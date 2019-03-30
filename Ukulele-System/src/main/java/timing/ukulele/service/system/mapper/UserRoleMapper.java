package timing.ukulele.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.api.model.UserRoleModel;
import timing.ukulele.persistence.mapper.BaseMapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleModel> {
}