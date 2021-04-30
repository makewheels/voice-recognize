package com.eg.voicerecognize.bean.tencent;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class TencentCallback {
    private int code;
    private String message;
    private long taskId;
    private int appid;
    private int projectid;
    private String audioUrl;
    private String text;
    @JSONField(name = "resultDetail")
    private List<ResultDetail> resultDetails;
    private double audioTime;
}
