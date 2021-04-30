package com.eg.voicerecognize.bean.aliyun;

import lombok.Data;

@Data
public class Sentence {

    private long EndTime;
    private long SilenceDuration;
    private long BeginTime;
    private String Text;
    private int ChannelId;
    private int SpeechRate;
    private double EmotionValue;

}