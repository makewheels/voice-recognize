package com.eg.voicerecognize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.bean.aliyun.AliyunCallback;
import com.eg.voicerecognize.bean.tencent.ResultDetail;
import com.eg.voicerecognize.bean.tencent.TencentCallback;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/voiceRecognize/callback")
@RestController
public class CallbackController {

    @PostMapping("aliyun")
    public void aliyun(@RequestBody JSONObject jsonObject) {
        AliyunCallback aliyunCallback = JSON.parseObject(
                jsonObject.toJSONString(), AliyunCallback.class);
        System.out.println(JSON.toJSONString(aliyunCallback));
    }

    @PostMapping("tencent")
    public String tencent(
            @RequestParam int code, @RequestParam String message,
            @RequestParam("requestId") long taskId, @RequestParam int appid,
            @RequestParam int projectid, @RequestParam String audioUrl,
            @RequestParam String text, @RequestParam String resultDetail,
            @RequestParam double audioTime) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        jsonObject.put("taskId", taskId);
        jsonObject.put("appid", appid);
        jsonObject.put("projectid", projectid);
        jsonObject.put("audioUrl", audioUrl);
        jsonObject.put("text", text);
        jsonObject.put("resultDetail", JSON.parseArray(resultDetail, ResultDetail.class));
        jsonObject.put("audioTime", audioTime);
        TencentCallback tencentCallback = JSON.parseObject(jsonObject.toJSONString(), TencentCallback.class);
        System.out.println(JSON.toJSONString(tencentCallback));
        return "{\"code\":0,\"message\":\"success\"}";
    }

}
