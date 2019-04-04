package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;


@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dept_relation")
public class SysDeptRelation extends BaseModel {


    /**
     * 祖先节点
     */
	private Integer ancestor;
    /**
     * 后代节点
     */
	private Integer descendant;
}
