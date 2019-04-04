package timing.ukulele.service.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.api.model.system.OAuthClientDetailsModel;

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OAuthClientDetailsModel> {

}