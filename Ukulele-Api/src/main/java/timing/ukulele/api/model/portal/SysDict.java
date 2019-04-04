package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

import java.math.BigDecimal;

/**
 * <p>
 * 字典表
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_dict")
public class SysDict extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 数据值
     */
	private String value;
    /**
     * 标签名
     */
	private String label;
    /**
     * 类型
     */
	private String type;
    /**
     * 描述
     */
	private String description;
    /**
     * 排序（升序）
     */
	private BigDecimal sort;

}
