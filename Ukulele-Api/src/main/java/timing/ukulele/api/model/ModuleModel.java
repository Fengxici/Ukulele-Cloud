package timing.ukulele.api.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

import java.util.List;

@Getter
@Setter
@TableName("module_")
public class ModuleModel extends BaseModel {
    private String moduleName;
    private String moduleCode;
    private String modulePath;
    private Long parentId;
    private String moduleIcon;
    private String httpMothed;
    private boolean isOperating;
    @TableField("sort_")
    private int sort;
    private Long systemId;
    @TableField(exist = false)
    private String systemName;
    /**
     * 资源子项
     */
    @TableField(exist = false)
    private List<ModuleModel> subModules;

    /**
     * 资源所属系统
     */
    @TableField(exist = false)
    private String projectName;
}
