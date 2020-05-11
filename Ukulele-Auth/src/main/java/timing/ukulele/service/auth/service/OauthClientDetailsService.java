package timing.ukulele.service.auth.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.data.auth.persistent.OAuthClientDetailsModel;
import timing.ukulele.service.auth.mapper.OauthClientDetailsMapper;

import java.util.List;

@Service
public class OauthClientDetailsService extends ServiceImpl<OauthClientDetailsMapper, OAuthClientDetailsModel> {
    /**
     * 获取所有数据
     *
     * @return 所有数据 List
     */
    public List<OAuthClientDetailsModel> getAll() {
        return list();
    }

    /**
     * 根据对象中的属性之获取一条记录
     *
     * @param record
     * @return
     */
    public OAuthClientDetailsModel queryOneByParam(final OAuthClientDetailsModel record) {
        QueryWrapper<OAuthClientDetailsModel> queryWrapper = new QueryWrapper<>(record);
        List<OAuthClientDetailsModel> list = list(queryWrapper);
        if (CollectionUtils.isEmpty(list))
            return null;
        return list.get(0);
    }

//    /**
//     * 根据where条件查询一条数据
//     *
//     * @param sqlSegment
//     * @return
//     */
//    public OAuthClientDetailsModel queryOneByParam(final String sqlSegment) {
//        return getOne(new Wrapper<OAuthClientDetailsModel>() {
//            @Override
//            public OAuthClientDetailsModel getEntity() {
//                return null;
//            }
//
//            @Override
//            public MergeSegments getExpression() {
//                return null;
//            }
//
//            @Override
//            public String getCustomSqlSegment() {
//                return null;
//            }
//
//            @Override
//            public String getSqlSegment() {
//                return sqlSegment;
//            }
//        });
//    }

    /**
     * 根据对象属性获取列表
     *
     * @param record
     * @return
     */
    public List<OAuthClientDetailsModel> listByParam(final OAuthClientDetailsModel record) {
        QueryWrapper<OAuthClientDetailsModel> queryWrapper = new QueryWrapper<>(record);
        List<OAuthClientDetailsModel> list = list(queryWrapper);
        return list;
    }

//    /**
//     * 根据where条件获取列表
//     *
//     * @param segment
//     * @return
//     */
//    public List<OAuthClientDetailsModel> listByParam(final String segment) {
//        Wrapper<OAuthClientDetailsModel> wrapper = new Wrapper<OAuthClientDetailsModel>() {
//            @Override
//            public OAuthClientDetailsModel getEntity() {
//                return null;
//            }
//
//            @Override
//            public MergeSegments getExpression() {
//                return null;
//            }
//
//            @Override
//            public String getCustomSqlSegment() {
//                return null;
//            }
//
//            @Override
//            public String getSqlSegment() {
//                return segment;
//            }
//        };
//        return list(wrapper);
//    }

}
