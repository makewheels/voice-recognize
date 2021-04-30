package com.eg.voicerecognize.bean.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class AliyunCallback {

    private String TaskId;
    private String StatusText;
    private long BizDuration;
    private long RequestTime;
    private long SolveTime;
    private long StatusCode;

    @JSONField(name = "Result")
    private Result result;

}