package com.eg.voicerecognize;

import com.eg.voicerecognize.profile.ProfileAliyun;
import com.eg.voicerecognize.service.VoiceRecognizeServiceAliyun;

public class Test {
    public static void main(String[] args) {
        ProfileAliyun profileAliyun = new ProfileAliyun();
        profileAliyun.setCloudType(CloudType.ALIYUN);
        profileAliyun.setAccessKeyId("");
        profileAliyun.setAccessKeySecret("");
        profileAliyun.setRegionId("cn-shanghai");
        profileAliyun.setEndpoint("cn-shanghai");
        profileAliyun.setProduct("nls-filetrans");
        profileAliyun.setDomain("cn-shanghai");
        profileAliyun.setAppkey("eylS4NWZtBM9rmPd");
        VoiceRecognizeServiceAliyun voiceRecognizeService = new VoiceRecognizeServiceAliyun();
        voiceRecognizeService.init(profileAliyun);
        String taskId = voiceRecognizeService.submitRequest("http://aliyun-nls.oss.aliyuncs.com/asr/fileASR/examples/nls-sample-16k.wav?spm=a2c4g.11186623.2.15.24c2306908lWsc&file=nls-sample-16k.wav");
        System.out.println(taskId);

    }
}
