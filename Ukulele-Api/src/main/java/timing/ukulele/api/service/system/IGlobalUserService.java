package timing.ukulele.api.service.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.api.model.system.GlobalUserModel;
import timing.ukulele.common.data.ResponseData;

public interface IGlobalUserService {
    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     *
     */
    @RequestMapping(value = "/user/name/{userName}", method = RequestMethod.GET)
    ResponseData<GlobalUserModel> getUserByUserName(@PathVariable("userName") String userName);

    /**
     * 根据电话号码查询用户
     * @param phone
     * @return
     */
    @RequestMapping(value = "/user/phone/{phone}", method = RequestMethod.GET)
    ResponseData<GlobalUserModel> getUserByPhone(@PathVariable("phone") String phone);
}
