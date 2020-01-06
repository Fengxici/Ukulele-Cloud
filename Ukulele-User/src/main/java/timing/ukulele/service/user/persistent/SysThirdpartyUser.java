package timing.ukulele.service.user.persistent;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_thirdparty_user")
public class SysThirdpartyUser implements Serializable {
    @TableId(value = "id_", type = IdType.ID_WORKER)
    private Long id;
    @TableField("deleted_")
    private Boolean deleted;
    private Date createTime;
    /**
     * 系统用户id
     */
    private Long userId;
    /**
     * 第三方用户标识，如openid
     */
    private String platId;
    /**
     * 第三方平台类型 1小程序
     */
    private Integer platSource;
}
