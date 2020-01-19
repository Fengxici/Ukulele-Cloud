package timing.ukulele.service.user.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.user.view.ThirdPartyUserVO;
import timing.ukulele.facade.user.IThirdPartyUserFacade;
import timing.ukulele.http.OkHttpManager;
import timing.ukulele.service.user.model.WxAppSessionResponse;
import timing.ukulele.service.user.persistent.SysThirdpartyUser;
import timing.ukulele.service.user.service.SysThirdpartyUserService;
import timing.ukulele.service.user.service.SysUserService;
import timing.ukulele.web.controller.BaseController;

import java.io.IOException;
import java.util.List;

@RestController
public class ThirdPartyUserController extends BaseController implements IThirdPartyUserFacade {

    @Value("${wx.appid}")
    private String wxAppid;
    @Value("${wx.appsecret}")
    private String wxSecret;

    @Autowired
    SysUserService userService;
    @Autowired
    SysThirdpartyUserService thirdpartyUserService;

    private String wxAppCode2Session = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @GetMapping("/wx/app/login")
    public ResponseData<String> wxAppLogin(@RequestParam("code") String code) {
        if (StringUtils.isEmpty(code))
            return paraErrorResponse();
        String code2SessionUrl = String.format(wxAppCode2Session, wxAppid, wxSecret, code);
        Request request = new Request.Builder()
                .url(code2SessionUrl).get()
                .build();
        try {
            Response response = OkHttpManager.INSTANCE.build(null).getClient().newCall(request).execute();
            if (response.isSuccessful()) {
                try {
                    String responseBody = response.body().string();
                    WxAppSessionResponse model = JSON.parseObject(responseBody, new TypeReference<WxAppSessionResponse>() {
                    });
                    if (model != null && model.getOpenid() != null && model.getOpenid().length() > 0)
                        return successResponse(model.getOpenid());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorResponse("获取用户微信标识失败");
    }

    //    @Override
    public ResponseData<ThirdPartyUserVO> getThirdUserByAll(String platId, Long userId, Integer plat) {
        LambdaQueryWrapper<SysThirdpartyUser> queryWrapper = new LambdaQueryWrapper<>(new SysThirdpartyUser());
        queryWrapper.eq(SysThirdpartyUser::getPlatSource, plat).eq(SysThirdpartyUser::getPlatId, platId).eq(SysThirdpartyUser::getUserId, userId).eq(SysThirdpartyUser::getDeleted, Boolean.FALSE);
        ThirdPartyUserVO vo = getVO(queryWrapper);
        return successResponse(vo);
    }

    @Override
    public ResponseData<ThirdPartyUserVO> getUserByThirdInfo(String platId, Integer plat) {
        LambdaQueryWrapper<SysThirdpartyUser> queryWrapper = new LambdaQueryWrapper<>(new SysThirdpartyUser());
        queryWrapper.eq(SysThirdpartyUser::getPlatSource, plat).eq(SysThirdpartyUser::getPlatId, platId).eq(SysThirdpartyUser::getDeleted, Boolean.FALSE);
        ThirdPartyUserVO vo = getVO(queryWrapper);
        return successResponse(vo);
    }

    private ThirdPartyUserVO getVO(LambdaQueryWrapper<SysThirdpartyUser> queryWrapper) {
        List<SysThirdpartyUser> list = thirdpartyUserService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list))
            return null;
        ThirdPartyUserVO vo = new ThirdPartyUserVO();
        BeanUtils.copyProperties(list.get(0), vo);
        return vo;
    }
}
