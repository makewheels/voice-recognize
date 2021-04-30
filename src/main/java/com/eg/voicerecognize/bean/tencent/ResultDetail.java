/**
 * Copyright 2021 json.cn
 */
package com.eg.voicerecognize.bean.tencent;

import java.util.List;

/**
 * Auto-generated: 2021-04-30 19:38:18
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class ResultDetail {

    private String FinalSentence;
    private String SliceSentence;
    private int StartMs;
    private int EndMs;
    private double SpeechSpeed;
    private int WordsNum;
    private List<Words> Words;

    public void setFinalSentence(String FinalSentence) {
        this.FinalSentence = FinalSentence;
    }

    public String getFinalSentence() {
        return FinalSentence;
    }

    public void setSliceSentence(String SliceSentence) {
        this.SliceSentence = SliceSentence;
    }

    public String getSliceSentence() {
        return SliceSentence;
    }

    public void setStartMs(int StartMs) {
        this.StartMs = StartMs;
    }

    public int getStartMs() {
        return StartMs;
    }

    public void setEndMs(int EndMs) {
        this.EndMs = EndMs;
    }

    public int getEndMs() {
        return EndMs;
    }

    public void setSpeechSpeed(double SpeechSpeed) {
        this.SpeechSpeed = SpeechSpeed;
    }

    public double getSpeechSpeed() {
        return SpeechSpeed;
    }

    public void setWordsNum(int WordsNum) {
        this.WordsNum = WordsNum;
    }

    public int getWordsNum() {
        return WordsNum;
    }

    public void setWords(List<Words> Words) {
        this.Words = Words;
    }

    public List<Words> getWords() {
        return Words;
    }

}