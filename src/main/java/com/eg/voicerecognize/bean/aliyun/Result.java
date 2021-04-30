package com.eg.voicerecognize.bean.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Result {
    @JSONField(name = "Words")
    private List<Word> words;
    @JSONField(name = "Sentences")
    private List<Sentence> sentences;

}