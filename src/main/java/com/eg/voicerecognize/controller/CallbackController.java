package com.eg.voicerecognize.controller;

import com.alibaba.fastjson.JSON;
import com.eg.voicerecognize.bean.aliyun.AliyunCallback;
import com.eg.voicerecognize.bean.tencent.TencentCallback;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@RequestMapping("/voiceRecognize/callback")
@RestController
public class CallbackController {

    // 以4开头的状态码是客户端错误。
    private static final Pattern PATTERN_CLIENT_ERR = Pattern.compile("4105[0-9]*");
    // 以5开头的状态码是服务端错误。
    private static final Pattern PATTERN_SERVER_ERR = Pattern.compile("5105[0-9]*");

    @PostMapping("aliyun")
    public void aliyun(@RequestBody AliyunCallback aliyunCallback) {
        System.out.println(JSON.toJSONString(aliyunCallback));
    }

    @PostMapping("tencent")
    public String tencent(@RequestBody TencentCallback tencentCallback) {
        System.out.println(JSON.toJSONString(tencentCallback));
        return "{\"code\":0,\"message\":\"success\"}";
    }

}
