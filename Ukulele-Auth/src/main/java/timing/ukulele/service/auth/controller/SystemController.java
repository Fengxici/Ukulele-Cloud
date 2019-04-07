package timing.ukulele.service.auth.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.common.data.TableData;
import timing.ukulele.facade.auth.api.IClientService;
import timing.ukulele.facade.auth.model.persistent.OAuthClientDetailsModel;
import timing.ukulele.service.auth.service.OauthClientDetailsService;
import timing.ukulele.web.pojo.ResponseCode;

import java.util.*;

@Slf4j
@RestController
public final class SystemController implements IClientService {


    private final OauthClientDetailsService oauthClientDetailsService;

    @Autowired
    public SystemController(OauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    @PostMapping("/client/table")
    protected ResponseData<TableData<OAuthClientDetailsModel>> queryClientRecord(@RequestBody OAuthClientDetailsModel query) {
        log.debug("查询客户端表格");
        PageInfo<OAuthClientDetailsModel> pageInfo = oauthClientDetailsService.pageInfo(query);
        TableData<OAuthClientDetailsModel> data = new TableData<>();
        data.setTotal(pageInfo.getTotal());
        data.setRows(pageInfo.getList());
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), data);
    }

    @PostMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> addClientRecord(@RequestBody OAuthClientDetailsModel record) {
        log.debug("添加客户端应用");
        try {
            oauthClientDetailsService.save(record);
        } catch (Exception e) {
            log.error("添加客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @DeleteMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> deleteClientRecord(@RequestBody List<OAuthClientDetailsModel> record) {
        log.debug("删除客户端应用");
        try {
            List<String> ids = new ArrayList<>();
            record.forEach(model -> ids.add(model.getClientId()));
            oauthClientDetailsService.removeByIds(ids);
        } catch (Exception e) {
            log.error("删除客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @PutMapping("/client")
    protected ResponseData<OAuthClientDetailsModel> updateClientRecord(@RequestBody OAuthClientDetailsModel record) {
        log.debug("更新客户端应用");
        try {
            oauthClientDetailsService.saveOrUpdate(record);
        } catch (Exception e) {
            log.error("更新客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
    }

    @GetMapping("/client/validate/{clientId}")
    protected ResponseData<OAuthClientDetailsModel> validateClientId(@PathVariable("clientId") String clientId) {
        log.debug("校验应用id是否存在");
        OAuthClientDetailsModel oauthClientDetails = new OAuthClientDetailsModel();
        oauthClientDetails.setClientId(clientId);
        oauthClientDetails = oauthClientDetailsService.queryOneByParam(oauthClientDetails);
        if (oauthClientDetails == null) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @Override
    public ResponseData<List<OAuthClientDetailsModel>> getAllClient() {
        log.debug("获取所有客户端应用");
        List<OAuthClientDetailsModel> list;
        try {
            list = oauthClientDetailsService.getAll();
        } catch (Exception e) {
            log.error("获取所有客户端应用失败：" + e.getMessage());
            e.printStackTrace();
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
        return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(), list);
    }

    @Override
    public OAuthClientDetailsModel get(Integer integer) {
        return null;
    }

    @Override
    public Page page(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseVO add(OAuthClientDetailsModel oAuthClientDetailsModel) {
        return null;
    }

    @Override
    public ResponseVO delete(String s) {
        return null;
    }

    @Override
    public ResponseVO edit(OAuthClientDetailsModel oAuthClientDetailsModel) {
        return null;
    }

}
