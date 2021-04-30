package com.eg.voicerecognize.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eg.voicerecognize.bean.aliyun.AliyunCallback;
import com.eg.voicerecognize.bean.tencent.TencentCallback;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String tencent(@RequestBody TencentCallback tencentCallback) {
        System.out.println(JSON.toJSONString(tencentCallback));
        return "{\"code\":0,\"message\":\"success\"}";
    }

}
