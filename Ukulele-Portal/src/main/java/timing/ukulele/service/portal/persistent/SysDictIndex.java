package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

/**
 * 字典索引表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict_index")
public class SysDictIndex extends BaseModel {
    @TableField("key_")
    private String key;
    @TableField("name_")
    private String name;
}
