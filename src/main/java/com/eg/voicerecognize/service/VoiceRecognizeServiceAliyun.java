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
    public String submitRequest(String url) {
        CommonRequest postRequest = new CommonRequest();
        postRequest.setSysDomain(domain);
        postRequest.setSysVersion("2018-08-17");
        postRequest.setSysAction("SubmitTask");
        postRequest.setSysMethod(MethodType.POST);
        // 设置录音文件识别请求参数，以JSON字符串的格式设置到请求Body中。
        JSONObject taskObject = new JSONObject();
        taskObject.put("appkey", appkey);    // 项目的Appkey
        taskObject.put("file_link", url);  // 设置录音文件的链接
        taskObject.put("version", "4.0");  // 新接入请使用4.0版本，已接入（默认2.0）如需维持现状，请注释掉该参数设置。
        String task = taskObject.toJSONString();
        postRequest.putBodyParameter("Task", task);  // 设置以上JSON字符串为Body参数。
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

    @Override
    public String getResult(String taskId) {
        CommonRequest getRequest = new CommonRequest();
        getRequest.setSysDomain(domain);
        getRequest.setSysVersion("2018-08-17");
        getRequest.setSysAction("GetTaskResult");
        getRequest.setSysMethod(MethodType.GET);

        getRequest.putQueryParameter("TaskId", taskId);
        String result = null;
        while (true) {
            try {
                CommonResponse getResponse = client.getCommonResponse(getRequest);
                System.err.println("识别查询结果：" + getResponse.getData());
                if (getResponse.getHttpStatus() != 200) {
                    break;
                }
                JSONObject rootObj = JSONObject.parseObject(getResponse.getData());
                String statusText = rootObj.getString("StatusText");
                if (STATUS_RUNNING.equals(statusText) || STATUS_QUEUEING.equals(statusText)) {
                    // 继续轮询，注意设置轮询时间间隔。
                    Thread.sleep(2000);
                } else {
                    // 状态信息为成功，返回识别结果；状态信息为异常，返回空。
                    if (STATUS_SUCCESS.equals(statusText)) {
                        result = rootObj.getString("Result");
                        // 状态信息为成功，但没有识别结果，则可能是由于文件里全是静音、噪音等导致识别为空。
                        if (result == null) {
                            result = "";
                        }
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}
