package timing.ukulele.api.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

@Getter
@Setter
@TableName("global_user")
public class GlobalUserModel extends BaseModel {
    @TableField("account_")
    private String account;

    @TableField("nickname_")
    private String nickname;

    @TableField("password_")
    private String password;

    @TableField("avatar_")
    private String avatar;

    @TableField("gender_")
    private int gender;

    @TableField("phone_")
    private String phone;

    @TableField(exist = false)
    private String GenderText;
}
