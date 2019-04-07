package timing.ukulele.service.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.facade.auth.model.persistent.OAuthClientDetailsModel;

@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OAuthClientDetailsModel> {

}