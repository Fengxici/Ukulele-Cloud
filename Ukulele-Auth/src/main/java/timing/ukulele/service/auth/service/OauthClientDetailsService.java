package timing.ukulele.service.auth.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.auth.model.persistent.OAuthClientDetailsModel;

import java.util.List;

@Service
public class OauthClientDetailsService  extends ServiceImpl<BaseMapper<OAuthClientDetailsModel>,OAuthClientDetailsModel> {
    /**
     * 获取所有数据
     *
     * @return 所有数据 List
     */
    public List<OAuthClientDetailsModel> getAll() {
        return baseMapper.selectList(new Wrapper<OAuthClientDetailsModel>() {
            @Override
            public OAuthClientDetailsModel getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getCustomSqlSegment() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        });
    }

    /**
     * 根据对象中的属性之获取一条记录
     *
     * @param record
     * @return
     */
    public OAuthClientDetailsModel queryOneByParam(final OAuthClientDetailsModel record) {
            return getOne(new Wrapper<OAuthClientDetailsModel>() {
            @Override
            public OAuthClientDetailsModel getEntity() {
                return record;
            }

                @Override
                public MergeSegments getExpression() {
                    return null;
                }

                @Override
                public String getCustomSqlSegment() {
                    return null;
                }

                @Override
            public String getSqlSegment() {
                return null;
            }
        });
    }

    /**
     * 根据where条件查询一条数据
     *
     * @param sqlSegment
     * @return
     */
    public OAuthClientDetailsModel queryOneByParam(final String sqlSegment) {
        return getOne(new Wrapper<OAuthClientDetailsModel>() {
            @Override
            public OAuthClientDetailsModel getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getCustomSqlSegment() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return sqlSegment;
            }
        });
    }

    /**
     * 根据对象属性获取列表
     *
     * @param record
     * @return
     */
    public List<OAuthClientDetailsModel> listByParam(final OAuthClientDetailsModel record) {
        Wrapper<OAuthClientDetailsModel> wrapper = new Wrapper<OAuthClientDetailsModel>() {
            @Override
            public OAuthClientDetailsModel getEntity() {
                return record;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getCustomSqlSegment() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return null;
            }
        };
        return list(wrapper);
    }

    /**
     * 根据where条件获取列表
     * @param segment
     * @return
     */
    public List<OAuthClientDetailsModel> listByParam(final String segment) {
        Wrapper<OAuthClientDetailsModel> wrapper = new Wrapper<OAuthClientDetailsModel>() {
            @Override
            public OAuthClientDetailsModel getEntity() {
                return null;
            }

            @Override
            public MergeSegments getExpression() {
                return null;
            }

            @Override
            public String getCustomSqlSegment() {
                return null;
            }

            @Override
            public String getSqlSegment() {
                return segment;
            }
        };
        return list(wrapper);
    }

}
