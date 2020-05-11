package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ant_menu")
public class AntMenu extends BaseIdWorkerModel {
    private Long parentId;
    @TableField("key_")
    private String key;
    @TableField("text_")
    private String text;
    @TableField("i18n_")
    private String i18n;
    @TableField("group_")
    private Boolean group;
    @TableField("link_")
    private String link;
    private Boolean linkExact;
    private String externalLink;
    @TableField("target_")
    private String target;
    private Long iconId;
    @TableField("disabled_")
    private Boolean disabled;
    @TableField("hide_")
    private Boolean hide;
    private Boolean hideInBreadcrumb;
    @TableField("acl_")
    private String acl;
    @TableField("shortcut_")
    private Boolean shortcut;
    private Boolean shortcutRoot;
    @TableField("reuse_")
    private Boolean reuse;
    @TableField("open_")
    private Boolean open;

    @TableField(exist = false)
    private AntIcon antIcon;
}
