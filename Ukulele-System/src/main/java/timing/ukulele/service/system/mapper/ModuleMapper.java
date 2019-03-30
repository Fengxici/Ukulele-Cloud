package timing.ukulele.service.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import timing.ukulele.api.model.ModuleModel;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface ModuleMapper extends BaseMapper<ModuleModel> {
    List<ModuleModel> getMenusByUserId(@Param("userId") long userId);

    List<ModuleModel> selectModuleTree(@Param("id") long id, @Param("systemId") long systemId);
}