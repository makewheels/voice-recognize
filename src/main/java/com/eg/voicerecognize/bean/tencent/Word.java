package com.eg.voicerecognize.bean.tencent;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Word {
    private int OffsetStartMs;
    private int OffsetEndMs;
    @JSONField(name = "Word")
    private String word;
}