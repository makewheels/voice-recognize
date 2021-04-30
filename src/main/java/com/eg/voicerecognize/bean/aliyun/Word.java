package com.eg.voicerecognize.bean.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Word {
    @JSONField(name = "Word")
    private String word;
    private int EndTime;
    private int BeginTime;
    private int ChannelId;

}