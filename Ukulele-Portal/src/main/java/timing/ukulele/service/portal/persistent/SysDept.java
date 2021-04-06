package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;

/**
 * <p>
 * 部门管理
 * </p>
 * @author fengxici
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dept")
public class SysDept extends BaseIdWorkerModel {

    /**
     * 部门名称
     */
    @TableField("name_")
    private String name;
    /**
     * 排序
     */
    private Integer orderNum;

    private Long parentId;

}
