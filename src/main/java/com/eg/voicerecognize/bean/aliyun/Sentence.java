/**
 * Copyright 2021 json.cn
 */
package com.eg.voicerecognize.bean.aliyun;

/**
 * Auto-generated: 2021-04-30 19:27:40
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Sentence {

    private int EndTime;
    private int SilenceDuration;
    private int BeginTime;
    private String Text;
    private int ChannelId;
    private int SpeechRate;
    private double EmotionValue;

    public void setEndTime(int EndTime) {
        this.EndTime = EndTime;
    }

    public int getEndTime() {
        return EndTime;
    }

    public void setSilenceDuration(int SilenceDuration) {
        this.SilenceDuration = SilenceDuration;
    }

    public int getSilenceDuration() {
        return SilenceDuration;
    }

    public void setBeginTime(int BeginTime) {
        this.BeginTime = BeginTime;
    }

    public int getBeginTime() {
        return BeginTime;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public String getText() {
        return Text;
    }

    public void setChannelId(int ChannelId) {
        this.ChannelId = ChannelId;
    }

    public int getChannelId() {
        return ChannelId;
    }

    public void setSpeechRate(int SpeechRate) {
        this.SpeechRate = SpeechRate;
    }

    public int getSpeechRate() {
        return SpeechRate;
    }

    public void setEmotionValue(double EmotionValue) {
        this.EmotionValue = EmotionValue;
    }

    public double getEmotionValue() {
        return EmotionValue;
    }

}