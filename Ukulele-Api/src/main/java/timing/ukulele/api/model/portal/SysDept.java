package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

import java.util.Date;

/**
 * <p>
 * 部门管理
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dept")
public class SysDept extends BaseModel {

    /**
     * 部门名称
     */
    private String name;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    @TableField("parent_id")
    private Integer parentId;

}
