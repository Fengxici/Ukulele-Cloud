package timing.ukulele.api.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

@Getter
@Setter
@TableName("system_")
public class SystemModel extends BaseModel {
    private String systemName;
    private String projectName;
    @TableField("sort_")
    private int sort;

}
