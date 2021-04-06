package timing.ukulele.service.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.PrefixConstant;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.auth.persistent.OAuthClientDetailsModel;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.auth.IClientFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;
import timing.ukulele.redisson.cache.CacheManager;
import timing.ukulele.service.auth.service.OauthClientDetailsService;
import timing.ukulele.web.controller.BaseController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static timing.ukulele.service.auth.Constant.SPRING_SECURITY_SMS_CODE_LENGTH;

/**
 * @author fengxici
 */
@Slf4j
@RestController
public final class SystemController extends BaseController implements IClientFacade {

    private final CacheManager cacheManager;
    private final OauthClientDetailsService oauthClientDetailsService;
    private final IUserFeignFacade userService;

    @Autowired
    public SystemController(CacheManager cacheManager, OauthClientDetailsService oauthClientDetailsService, IUserFeignFacade userService) {
        this.cacheManager = cacheManager;
        this.oauthClientDetailsService = oauthClientDetailsService;
        this.userService = userService;
    }

    public ResponseData<List<OAuthClientDetailsModel>> getAllClient() {
        log.debug("获取所有客户端应用");
        List<OAuthClientDetailsModel> list;
        try {
            list = oauthClientDetailsService.getAll();
        } catch (Exception e) {
            log.error("获取所有客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR);
        }
        return new ResponseData<>(ResponseCode.SUCCESS, list);
    }

    @Override
    public ResponseData<List<OAuthClientDetailsModel>> getClientByParam(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseData<OAuthClientDetailsModel> get(Integer integer) {
        return null;
    }

    @Override
    public ResponseData<Boolean> add(OAuthClientDetailsModel oAuthClientDetailsModel) {
        log.debug("添加客户端应用");
        try {
            oauthClientDetailsService.save(oAuthClientDetailsModel);
        } catch (Exception e) {
            log.error("添加客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR, Boolean.FALSE);
        }
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.FALSE);
    }

    @Override
    public ResponseData<Boolean> delete(String s) {
        log.debug("删除客户端应用");
        try {
            List<String> ids = new ArrayList<>();
//            record.forEach(model -> ids.add(model.getClientId()));
//            oauthClientDetailsService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR, Boolean.FALSE);
        }
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.FALSE);
    }

    @Override
    public ResponseData<Boolean> edit(OAuthClientDetailsModel oAuthClientDetailsModel) {
        log.debug("更新客户端应用");
        try {
            oauthClientDetailsService.saveOrUpdate(oAuthClientDetailsModel);
        } catch (Exception e) {
            log.error("更新客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR, Boolean.FALSE);
        }
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.FALSE);
    }

    /**
     * 扫码准备登录
     *
     * @param connectId 连接标识
     * @param username  用户名
     * @return
     */
    @GetMapping("prepareScanLogin")
    public ResponseData<String> prepareScanLogin(@RequestParam("connectId") String connectId, @RequestParam("username") String username) {
        String code = RandomStringUtils.randomNumeric(SPRING_SECURITY_SMS_CODE_LENGTH);
        cacheManager.set(PrefixConstant.QR_CONNECT_ID + connectId, code, 3 * 60);
        ResponseData<UserVO> userResponse = userService.getUserByUserName(username);
        if (null == userResponse || null == userResponse.getData()) {
            errorResponse("没有查到该用户");
        }
        return successResponse();
    }

    @GetMapping("/page")
    public IPage page(Map<String, Object> map) {
        return null;
    }

}
