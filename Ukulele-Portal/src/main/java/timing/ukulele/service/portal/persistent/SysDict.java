package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;
import timing.ukulele.persistence.model.BaseModel;

/**
 * <p>
 * 字典表
 * </p>
 * @author fengxici
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict")
public class SysDict extends BaseIdWorkerModel {

    /**
     * 数据值
     */
    @TableField("value_")
    private String value;
    /**
     * 标签名
     */
    @TableField("label_")
    private String label;
    /**
     * 索引表id
     */
    @TableField("index_id")
    private Long indexId;
    /**
     * 排序（升序）
     */
    @TableField("sort_")
    private Integer sort;
    /**
     * 备注信息
     */
    @TableField("remark_")
    private String remark;

}
