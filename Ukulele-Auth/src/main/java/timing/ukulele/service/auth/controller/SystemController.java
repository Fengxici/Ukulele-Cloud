package timing.ukulele.service.auth.controller;

import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.common.exception.DefaultError;
import timing.ukulele.facade.auth.api.IClientFacade;
import timing.ukulele.facade.auth.model.persistent.OAuthClientDetailsModel;
import timing.ukulele.service.auth.service.OauthClientDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public final class SystemController implements IClientFacade {


    private final OauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public SystemController(OauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
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


    @GetMapping("/page")
    public Page page(Map<String, Object> map) {
        return null;
    }

}
