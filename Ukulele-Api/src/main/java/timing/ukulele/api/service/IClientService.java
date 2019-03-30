package timing.ukulele.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.api.model.OAuthClientDetailsModel;
import timing.ukulele.common.data.ResponseData;

import java.util.List;

public interface IClientService {
    @RequestMapping(value = "/client/all", method = RequestMethod.GET)
    ResponseData<List<OAuthClientDetailsModel>> getAllClient();
}
