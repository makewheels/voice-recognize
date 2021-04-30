package com.eg.voicerecognize.service;

import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.profile.Profile;
import org.springframework.stereotype.Service;

@Service
public interface VoiceRecognizeService {

    void init(Profile profile);

    String submitRequest(String fileUrl);

    String submitRequest(String fileUrl, String callbackUrl);

    JSONObject getResult(String taskId);
}
