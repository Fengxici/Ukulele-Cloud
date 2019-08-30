package timing.ukulele.service.portal.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.http.OkHttpManager;
import timing.ukulele.web.controller.BaseController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("eureka")
public class EurekaClientController extends BaseController {

    @Resource
    private EurekaClient eurekaClient;

    /**
     * 获取服务数量和节点数量
     */
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public ResponseData<Map<String, Object>> home() {
        List<Application> apps = eurekaClient.getApplications().getRegisteredApplications();
        int appCount = apps.size();
        int nodeCount = 0;
        int enableNodeCount = 0;
        for (Application app : apps) {
            nodeCount += app.getInstancesAsIsFromEureka().size();
            List<InstanceInfo> instances = app.getInstances();
            for (InstanceInfo instance : instances) {
                if (instance.getStatus().name().equals(InstanceStatus.UP.name())) {
                    enableNodeCount++;
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("appCount", appCount);
        map.put("nodeCount", nodeCount);
        map.put("enableNodeCount", enableNodeCount);
        return successResponse(map);
    }

    /**
     * 获取所有服务节点
     */
    @RequestMapping(value = "apps", method = RequestMethod.GET)
    public ResponseData<List<Application>> apps() {
        List<Application> apps = eurekaClient.getApplications().getRegisteredApplications();
        Collections.sort(apps, Comparator.comparing(Application::getName));
        for (Application app : apps) {
            Collections.sort(app.getInstances(), Comparator.comparingInt(InstanceInfo::getPort));
        }
        return successResponse(apps);
    }

    @RequestMapping(value = "status/{appName}", method = RequestMethod.POST)
    public ResponseData<Boolean> status(@PathVariable String appName, String instanceId, String status) {
        Application application = eurekaClient.getApplication(appName);
        InstanceInfo instanceInfo = application.getByInstanceId(instanceId);
        instanceInfo.setStatus(InstanceStatus.toEnum(status));
        String url = instanceInfo.getHomePageUrl() + "eureka-client/status?status=" + status;
        Request request = new Request.Builder()
                .url(url).post(FormBody.create(MediaType.parse("application/json"), ""))
                .build();
        try {
            Response response = OkHttpManager.INSTANCE.build(null).getClient().newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                if (StringUtils.isNotEmpty(responseBody))
                    System.out.println(responseBody);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return errorResponse(false);
        }
        return successResponse(true);
    }
}
