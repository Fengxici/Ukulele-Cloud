package timing.ukulele.api.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("oauth_access_token")
public class OAuthAccessTokenModel {
    private String tokenId;
    private byte[] token;
    private String authenticationId;
    private String userName;
    private String clientId;
    private String authentication;
    private String refreshToken;
}
