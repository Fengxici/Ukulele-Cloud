package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

/**
 * <p>
 * 菜单权限表
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_menu")
public class SysMenu extends BaseModel {

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.INPUT)
    private Integer menuId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单权限标识
     */
    private String permission;
    /**
     * 请求链接
     */
    private String url;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    private Integer parentId;
    /**
     * 图标
     */
    private String icon;
    /**
     * VUE页面
     */
    private String component;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 菜单类型 （0菜单 1按钮）
     */
    private String type;

    /**
     * 前端URL
     */
    private String path;

}
