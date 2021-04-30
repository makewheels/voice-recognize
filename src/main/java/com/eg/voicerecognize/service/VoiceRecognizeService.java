package com.eg.voicerecognize.service;

import com.eg.voicerecognize.profile.Profile;
import org.springframework.stereotype.Service;

@Service
public interface VoiceRecognizeService {

    void init(Profile profile);
    String submitRequest(String url);
}
