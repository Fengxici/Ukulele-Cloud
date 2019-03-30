package timing.ukulele.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthRefreshTokenModel {
    private String tokenId;
    private byte[] token;
    private byte[] authentication;
}
