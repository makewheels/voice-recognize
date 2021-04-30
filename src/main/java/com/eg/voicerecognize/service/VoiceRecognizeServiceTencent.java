package com.eg.voicerecognize.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.profile.Profile;
import com.eg.voicerecognize.profile.ProfileTencent;
import com.tencentcloudapi.asr.v20190614.AsrClient;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskRequest;
import com.tencentcloudapi.asr.v20190614.models.CreateRecTaskResponse;
import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusRequest;
import com.tencentcloudapi.asr.v20190614.models.DescribeTaskStatusResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

public class VoiceRecognizeServiceTencent implements VoiceRecognizeService {
    private AsrClient client;

    @Override
    public void init(Profile profile) {
        ProfileTencent profileTencent = (ProfileTencent) profile;
        Credential credential = new Credential(profileTencent.getSecretId(), profileTencent.getSecretKey());
        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint(profileTencent.getEndpoint());
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        client = new AsrClient(credential, "", clientProfile);
    }

    @Override
    public String submitRequest(String fileUrl) {
        return submitRequest(fileUrl, null);
    }

    @Override
    public String submitRequest(String fileUrl, String callbackUrl) {
        CreateRecTaskRequest request = new CreateRecTaskRequest();
        //  https://cloud.tencent.com/document/product/1093/37823

        //EngineModelType 引擎模型类型
        //电话场景
        // 8k_en：电话 8k 英语
        // 8k_zh：电话 8k 中文普通话通用
        //非电话场景
        // 16k_zh：16k 中文普通话通用
        // 16k_zh_video：16k 音视频领域
        // 16k_en：16k 英语
        // 16k_ca：16k 粤语
        // 16k_ja：16k 日语
        // 16k_zh_edu 中文教育
        // 16k_en_edu 英文教育
        // 16k_zh_medical 医疗
        // 16k_th 泰语
        request.setEngineModelType("16k_zh");
        request.setChannelNum(1L);

        //识别结果返回形式
        // 0：识别结果文本(含分段时间戳)
        // 1：词级别粒度的详细识别结果(不含标点，含语速值)
        // 2：词级别粒度的详细识别结果（包含标点、语速值）
        request.setResTextFormat(2L);
        request.setSourceType(0L);
        request.setUrl(fileUrl);

        CreateRecTaskResponse response = null;
        try {
            response = client.CreateRecTask(request);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        if (response == null)
            return null;
        String json = CreateRecTaskResponse.toJsonString(response);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getObject("Data", JSONObject.class).getLong("TaskId") + "";
    }

    @Override
    public JSONObject getResult(String taskId) {
        DescribeTaskStatusRequest req = new DescribeTaskStatusRequest();
        req.setTaskId(Long.parseLong(taskId));

        DescribeTaskStatusResponse response = null;
        try {
            response = client.DescribeTaskStatus(req);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        if (response == null)
            return null;
        return JSONObject.parseObject(JSON.toJSONString(response));
    }
}
