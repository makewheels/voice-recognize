package com.eg.voicerecognize.bean.tencent;

import lombok.Data;

@Data
public class TencentCallback {
    private int code;
    private String message;
    private long taskId;
    private int appid;
    private int projectid;
    private String audioUrl;
    private String text;
    private ResultDetail resultDetail;
    private double audioTime;
}
