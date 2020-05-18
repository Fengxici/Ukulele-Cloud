package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;

/**
 * <p>
 * 菜单权限表
 * </p>
 * @author fengxici
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class SysMenu extends BaseIdWorkerModel {

    /**
     * 菜单名称
     */
    @TableField("name_")
    private String name;
    /**
     * 菜单权限标识
     */
    @TableField("permission_")
    private String permission;
    /**
     * 请求链接
     */
    @TableField("url_")
    private String url;
    /**
     * 请求方法
     */
    @TableField("method_")
    private String method;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 图标
     */
    @TableField("icon_")
    private String icon;
    /**
     * VUE页面
     */
    @TableField("component_")
    private String component;
    /**
     * 排序值
     */
    @TableField("sort_")
    private Integer sort;
    /**
     * 菜单类型 （0菜单 1按钮）
     */
    @TableField("type_")
    private String type;
    /**
     * 前端URL
     */
    @TableField("path_")
    private String path;
}
