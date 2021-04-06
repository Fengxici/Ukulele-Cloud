package timing.ukulele.service.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.data.auth.persistent.OAuthClientDetailsModel;

/**
 * @author fengxici
 */
@Mapper
public interface OauthClientDetailsMapper extends BaseMapper<OAuthClientDetailsModel> {

}