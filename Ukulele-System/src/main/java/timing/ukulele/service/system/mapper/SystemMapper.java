package timing.ukulele.service.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.api.model.system.ModuleAndSystemModel;
import timing.ukulele.api.model.system.SystemModel;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface SystemMapper extends BaseMapper<SystemModel> {
    List<ModuleAndSystemModel> selectModuleAndSystem();
}