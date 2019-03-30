package timing.ukulele.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.api.model.RoleModuleModel;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface RoleModuleMapper extends BaseMapper<RoleModuleModel> {
    List<RoleModuleModel> selectLeafRoleModule(String roleId);
}