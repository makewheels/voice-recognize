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
public class AliyunCallback {

    private Result Result;
    private String TaskId;
    private long StatusCode;
    private String StatusText;
    private long RequestTime;
    private long SolveTime;
    private int BizDuration;

    public void setResult(Result Result) {
        this.Result = Result;
    }

    public Result getResult() {
        return Result;
    }

    public void setTaskId(String TaskId) {
        this.TaskId = TaskId;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setStatusCode(long StatusCode) {
        this.StatusCode = StatusCode;
    }

    public long getStatusCode() {
        return StatusCode;
    }

    public void setStatusText(String StatusText) {
        this.StatusText = StatusText;
    }

    public String getStatusText() {
        return StatusText;
    }

    public void setRequestTime(long RequestTime) {
        this.RequestTime = RequestTime;
    }

    public long getRequestTime() {
        return RequestTime;
    }

    public void setSolveTime(long SolveTime) {
        this.SolveTime = SolveTime;
    }

    public long getSolveTime() {
        return SolveTime;
    }

    public void setBizDuration(int BizDuration) {
        this.BizDuration = BizDuration;
    }

    public int getBizDuration() {
        return BizDuration;
    }

}