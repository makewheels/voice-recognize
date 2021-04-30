package com.eg.voicerecognize.test;

import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.profile.CloudType;
import com.eg.voicerecognize.profile.ProfileTencent;
import com.eg.voicerecognize.service.VoiceRecognizeServiceTencent;
import com.tencentcloudapi.mongodb.v20180408.models.SpecItem;

public class TestTencent {
    public static void main(String[] args) {
        ProfileTencent profileTencent = new ProfileTencent();
        profileTencent.setCloudType(CloudType.TENCENT);
        profileTencent.setSecretId("AKIDlJd1C8BRcYLuxInbcYHky0lGVVN1Ibfn");
        profileTencent.setSecretKey("1nMjtpe1YqsfV8XpTMHxSFWifPLtCG5o");

        profileTencent.setEndpoint("asr.tencentcloudapi.com");

        VoiceRecognizeServiceTencent voiceRecognizeService = new VoiceRecognizeServiceTencent();
        voiceRecognizeService.init(profileTencent);
        String fileUrl = "http://aliyun-nls.oss.aliyuncs.com/asr/fileASR/examples/nls-sample-16k.wav";
        String callbackUrl = "http://c19758058n.imwork.net/voiceRecognize/callback/tencent";
        String taskId = voiceRecognizeService.submitRequest(fileUrl, callbackUrl);
        System.out.println(taskId);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject result = voiceRecognizeService.getResult(taskId);
        System.out.println(result);
        JSONObject data = result.getJSONObject("data");
        JSONObject resultDetail = data.getJSONArray("resultDetail").getJSONObject(0);
        System.out.println(resultDetail.getString("finalSentence"));
        System.out.println(data.getLong("taskId"));
    }
}
