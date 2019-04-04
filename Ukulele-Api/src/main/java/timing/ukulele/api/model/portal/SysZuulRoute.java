package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

/**
 * <p>
 * 动态路由配置表
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_zuul_route")
public class SysZuulRoute extends BaseModel {
    /**
     * 路由路径
     */
    private String path;
    /**
     * 服务名称
     */
    @TableField("service_id")
    private String serviceId;
    /**
     * url代理
     */
    private String url;
    /**
     * 转发去掉前缀
     */
    @TableField("strip_prefix")
    private String stripPrefix;
    /**
     * 是否重试
     */
    private String retryable;
    /**
     * 是否启用
     */
    private String enabled;
    /**
     * 敏感请求头
     */
    @TableField("sensitiveHeaders_list")
    private String sensitiveHeadersList;
    /**
     * 删除标识（0-正常,1-删除）
     */
    @TableField("del_flag")
    private String delFlag;

}
