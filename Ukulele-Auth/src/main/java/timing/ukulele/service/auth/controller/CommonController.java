package timing.ukulele.service.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.MobileMsgTemplate;
import timing.ukulele.common.constant.PrefixConstant;
import timing.ukulele.common.constant.SmsChannel;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.RegexUtil;
import timing.ukulele.redisson.cache.CacheManager;
import timing.ukulele.web.controller.BaseController;

import static timing.ukulele.common.constant.PrefixConstant.SMS_PHONE_SENDED;
import static timing.ukulele.service.auth.Constant.SPRING_SECURITY_CONNECT_ID_LENGTH;
import static timing.ukulele.service.auth.Constant.SPRING_SECURITY_SMS_CODE_LENGTH;

/**
 * 常规接口，不需要登录的
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController extends BaseController {
    @Value("${host.baseUrl}")
    private String baseUrl;
    private final String msgTemplate = "【Ukulele】您的登录验证码为%s，请在$s分钟内使用。";
    private final CacheManager cacheManager;

    public CommonController(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * 获取短信验证码
     *
     * @param mobile 手机号
     * @return
     */
    @GetMapping("/sms_code")
    public ResponseData<String> getSmsCode(@RequestParam("mobile") String mobile) {
        if (StringUtils.isEmpty(mobile) || !RegexUtil.checkMobileNumber(mobile))
            return paraErrorResponse("手机号不能为空，且必须是正确的11位格式");
        Object phone = cacheManager.get(SMS_PHONE_SENDED + mobile);
        if (null == phone) {
            cacheManager.set(SMS_PHONE_SENDED + mobile, 1, 3 * 60);
        } else {
            int sendTime = (int) phone;
            if (4 > sendTime) {
                cacheManager.set(SMS_PHONE_SENDED + mobile, ++sendTime);
            } else {
                errorResponse("操作太频繁，请稍后再试！");
            }
        }
        String code = RandomStringUtils.randomNumeric(SPRING_SECURITY_SMS_CODE_LENGTH);
        log.info("手机验证码----->手机号:" + mobile + ",验证码:" + code);
        String msgContent = String.format(msgTemplate, code, "1");
        MobileMsgTemplate msg = new MobileMsgTemplate(mobile, msgContent, SmsChannel.ALIYUN.getName());
        // todo 发送短信
        cacheManager.set(PrefixConstant.SMS_CODE + mobile, code, 60);
        return successResponse(code);
    }

    @GetMapping("/qr_code")
    public ResponseData<String> getQrCode() {
        String code = baseUrl + "?connectId=" + RandomStringUtils.random(SPRING_SECURITY_CONNECT_ID_LENGTH);
        return successResponse(code);
    }
}
