package com.eg.voicerecognize.test;

import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.profile.CloudType;
import com.eg.voicerecognize.profile.ProfileAliyun;
import com.eg.voicerecognize.service.VoiceRecognizeServiceAliyun;

public class TestAliyun {
    public static void main(String[] args) {
        ProfileAliyun profileAliyun = new ProfileAliyun();
        profileAliyun.setCloudType(CloudType.ALIYUN);
        profileAliyun.setAccessKeyId("LTAI5tLHvFdnm6MXsLm9ubWR");
        profileAliyun.setAccessKeySecret("dP7nFJMKNsJ9vLiwbwj492AXzeRtWe");

        profileAliyun.setRegionId("cn-shanghai");
        profileAliyun.setEndpoint("cn-shanghai");
        profileAliyun.setProduct("nls-filetrans");
        profileAliyun.setDomain("filetrans.cn-shanghai.aliyuncs.com");
        profileAliyun.setAppkey("eylS4NWZtBM9rmPd");

        VoiceRecognizeServiceAliyun voiceRecognizeService = new VoiceRecognizeServiceAliyun();
        voiceRecognizeService.init(profileAliyun);

        String fileUrl = "http://aliyun-nls.oss.aliyuncs.com/asr/fileASR/examples/nls-sample-16k.wav";
        String callbackUrl = "http://c19758058n.imwork.net:29406/voiceRecognize/callback/aliyun";
        String taskId = voiceRecognizeService.submitRequest(fileUrl, callbackUrl);
        System.out.println(taskId);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JSONObject result = voiceRecognizeService.getResult(taskId);
        System.out.println(result);

    }
}
