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

public class VoiceRecognizeServiceAliyun implements VoiceRecognizeService {
    // 地域ID，常量，固定值。
    public static final String REGIONID = "cn-shanghai";
    public static final String ENDPOINTNAME = "cn-shanghai";
    public static final String PRODUCT = "nls-filetrans";
    public static final String DOMAIN = "filetrans.cn-shanghai.aliyuncs.com";
    public static final String API_VERSION = "2018-08-17";
    public static final String POST_REQUEST_ACTION = "SubmitTask";
    public static final String GET_REQUEST_ACTION = "GetTaskResult";
    // 请求参数
    public static final String KEY_APP_KEY = "appkey";
    public static final String KEY_FILE_LINK = "file_link";
    public static final String KEY_VERSION = "version";
    public static final String KEY_ENABLE_WORDS = "enable_words";
    // 响应参数
    public static final String KEY_TASK = "Task";
    public static final String KEY_TASK_ID = "TaskId";
    public static final String KEY_STATUS_TEXT = "StatusText";
    public static final String KEY_RESULT = "Result";
    // 状态值
    public static final String STATUS_SUCCESS = "SUCCESS";
    private static final String STATUS_RUNNING = "RUNNING";
    private static final String STATUS_QUEUEING = "QUEUEING";

    private String domain;
    private String appkey;
    private IAcsClient client;

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
    public String submitRequest(String url) {
        CommonRequest postRequest = new CommonRequest();
        postRequest.setDomain("filetrans.cn-shanghai.aliyuncs.com"); // 设置域名，固定值。
        postRequest.setVersion("2018-08-17");         // 设置API的版本号，固定值。
        postRequest.setAction("SubmitTask");          // 设置action，固定值。
        postRequest.setProduct("nls-filetrans");      // 设置产品名称，固定值。
        // 设置录音文件识别请求参数，以JSON字符串的格式设置到请求Body中。
        JSONObject taskObject = new JSONObject();
        taskObject.put("appkey", appkey);    // 项目的Appkey
        taskObject.put("file_link", url);  // 设置录音文件的链接
        taskObject.put("version", "4.0");  // 新接入请使用4.0版本，已接入（默认2.0）如需维持现状，请注释掉该参数设置。
        String task = taskObject.toJSONString();
        postRequest.putBodyParameter("Task", task);  // 设置以上JSON字符串为Body参数。
        postRequest.setMethod(MethodType.POST);      // 设置为POST方式请求。
        String taskId = "";   // 获取录音文件识别请求任务的ID，以供识别结果查询使用。
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
                System.out.println("录音文件识别请求成功响应： " + result.toJSONString());
                taskId = result.getString("TaskId");
            } else {
                System.out.println("录音文件识别请求失败： " + result.toJSONString());
                return null;
            }
        } else {
            System.err.println("录音文件识别请求失败，Http错误码：" + postResponse.getHttpStatus());
            System.err.println("录音文件识别请求失败响应：" + JSONObject.toJSONString(postResponse));
            return null;
        }
        return taskId;
    }


}
