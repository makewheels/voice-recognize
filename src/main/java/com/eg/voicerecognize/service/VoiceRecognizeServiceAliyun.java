package com.eg.voicerecognize.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.eg.voicerecognize.profile.Profile;
import com.eg.voicerecognize.profile.ProfileAliyun;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VoiceRecognizeServiceAliyun implements VoiceRecognizeService {
    private String domain;
    private String appkey;
    private IAcsClient client;
    // 状态值
    public static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_RUNNING = "RUNNING";
    private static final String STATUS_QUEUEING = "QUEUEING";

    @Override
    public void init(Profile profile) {
        ProfileAliyun profileAliyun = (ProfileAliyun) profile;
        String regionId = profileAliyun.getRegionId();
        String product = profileAliyun.getProduct();
        String endpoint = profileAliyun.getEndpoint();
        String accessKeyId = profileAliyun.getAccessKeyId();
        String accessKeySecret = profileAliyun.getAccessKeySecret();
        domain = profileAliyun.getDomain();
        appkey = profileAliyun.getAppkey();

        DefaultProfile.addEndpoint(regionId, product, endpoint);
        DefaultProfile defaultProfile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        client = new DefaultAcsClient(defaultProfile);
    }

    @Override
    public String submitRequest(String fileUrl) {
        return submitRequest(fileUrl, null);
    }

    @Override
    public String submitRequest(String fileUrl, String callbackUrl) {
        CommonRequest postRequest = new CommonRequest();
        postRequest.setSysDomain(domain);
        postRequest.setSysVersion("2018-08-17");
        postRequest.setSysAction("SubmitTask");
        postRequest.setSysMethod(MethodType.POST);
        // 设置录音文件识别请求参数，以JSON字符串的格式设置到请求Body中。
        JSONObject taskObject = new JSONObject();
        taskObject.put("appkey", appkey);    // 项目的Appkey
        taskObject.put("file_link", fileUrl);  // 设置录音文件的链接
        taskObject.put("version", "4.0");  // 新接入请使用4.0版本，已接入（默认2.0）如需维持现状，请注释掉该参数设置。
        taskObject.put("enable_words", true); // 是否开启返回词信息，默认为false，开启时需要设置version为“4.0”。
        taskObject.put("enable_inverse_text_normalization", true); // 是否打开ITN，中文数字将转为阿拉伯数字输出，默认值为false，开启时需要设置version为“4.0”。
        if (callbackUrl != null) {
            taskObject.put("enable_callback", true);
            taskObject.put("callback_url", callbackUrl);
        }
        String task = taskObject.toJSONString();
        postRequest.putBodyParameter("Task", task);  // 设置以上JSON字符串为Body参数。
        String taskId;   // 获取录音文件识别请求任务的ID，以供识别结果查询使用。
        CommonResponse postResponse = null;
        try {
            postResponse = client.getCommonResponse(postRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (postResponse == null)
            return null;
        if (postResponse.getHttpStatus() == 200) {
            JSONObject result = JSONObject.parseObject(postResponse.getData());
            String statusText = result.getString("StatusText");
            if ("SUCCESS".equals(statusText)) {
                log.info("录音文件识别请求成功响应： " + result.toJSONString());
                taskId = result.getString("TaskId");
            } else {
                log.error("录音文件识别请求失败： " + result.toJSONString());
                return null;
            }
        } else {
            log.error("录音文件识别请求失败，Http错误码：" + postResponse.getHttpStatus());
            log.error("录音文件识别请求失败响应：" + JSONObject.toJSONString(postResponse));
            return null;
        }
        return taskId;
    }

    @Override
    public JSONObject getResult(String taskId) {
        CommonRequest getRequest = new CommonRequest();
        getRequest.setSysDomain(domain);
        getRequest.setSysVersion("2018-08-17");
        getRequest.setSysAction("GetTaskResult");
        getRequest.setSysMethod(MethodType.GET);

        getRequest.putQueryParameter("TaskId", taskId);
        CommonResponse getResponse = null;
        try {
            getResponse = client.getCommonResponse(getRequest);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (getResponse == null)
            return null;
        log.info("识别查询结果：" + getResponse.getData());
        if (getResponse.getHttpStatus() != 200) {
            return null;
        }
        return JSONObject.parseObject(getResponse.getData());
    }


}
