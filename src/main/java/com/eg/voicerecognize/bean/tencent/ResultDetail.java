package com.eg.voicerecognize.bean.tencent;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class ResultDetail {
    private String FinalSentence;
    private String SliceSentence;
    private int StartMs;
    private int EndMs;
    private double SpeechSpeed;
    private int WordsNum;
    @JSONField(name = "Words")
    private List<Word> words;
}