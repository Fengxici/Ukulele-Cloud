package timing.ukulele.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import timing.ukulele.api.model.system.RoleModel;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleModel> {
    List<RoleModel> getRoleByUserId(@Param("userId") long userId);
}