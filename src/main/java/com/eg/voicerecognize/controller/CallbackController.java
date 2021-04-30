package com.eg.voicerecognize.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/filetrans/callback")
@RestController
public class CallbackController {

    // 以4开头的状态码是客户端错误。
    private static final Pattern PATTERN_CLIENT_ERR = Pattern.compile("4105[0-9]*");
    // 以5开头的状态码是服务端错误。
    private static final Pattern PATTERN_SERVER_ERR = Pattern.compile("5105[0-9]*");

    // 必须是post方式
    @PostMapping(value = "result")
    public void GetResult(HttpServletRequest request) {
        byte[] buffer = new byte[request.getContentLength()];
        ServletInputStream in;
        try {
            in = request.getInputStream();
            in.read(buffer, 0, request.getContentLength());
            in.close();
            // 获取JSON格式的文件识别结果。
            String result = new String(buffer);
            JSONObject jsonResult = JSONObject.parseObject(result);
            // 解析并输出相关结果内容。
            System.out.println("获取文件中转写回调结果:" + result);
            System.out.println("TaskId: " + jsonResult.getString("TaskId"));
            System.out.println("StatusCode: " + jsonResult.getString("StatusCode"));
            System.out.println("StatusText: " + jsonResult.getString("StatusText"));
            Matcher matcherClient = PATTERN_CLIENT_ERR.matcher(jsonResult.getString("StatusCode"));
            Matcher matcherServer = PATTERN_SERVER_ERR.matcher(jsonResult.getString("StatusCode"));
            // 以2开头状态码为正常状态码，回调方式正常状态只返回“21050000”。
            if ("21050000".equals(jsonResult.getString("StatusCode"))) {
                System.out.println("RequestTime: " + jsonResult.getString("RequestTime"));
                System.out.println("SolveTime: " + jsonResult.getString("SolveTime"));
                System.out.println("BizDuration: " + jsonResult.getString("BizDuration"));
                System.out.println("Result.Sentences.size: " +
                        jsonResult.getJSONObject("Result").getJSONArray("Sentences").size());
                for (int i = 0; i < jsonResult.getJSONObject("Result").getJSONArray("Sentences").size(); i++) {
                    System.out.println("Result.Sentences[" + i + "].BeginTime: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("BeginTime"));
                    System.out.println("Result.Sentences[" + i + "].EndTime: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("EndTime"));
                    System.out.println("Result.Sentences[" + i + "].SilenceDuration: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("SilenceDuration"));
                    System.out.println("Result.Sentences[" + i + "].Text: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("Text"));
                    System.out.println("Result.Sentences[" + i + "].ChannelId: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("ChannelId"));
                    System.out.println("Result.Sentences[" + i + "].SpeechRate: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("SpeechRate"));
                    System.out.println("Result.Sentences[" + i + "].EmotionValue: " +
                            jsonResult.getJSONObject("Result").getJSONArray("Sentences").getJSONObject(i).getString("EmotionValue"));
                }
            } else if (matcherClient.matches()) {
                System.out.println("状态码以4开头表示客户端错误......");
            } else if (matcherServer.matches()) {
                System.out.println("状态码以5开头表示服务端错误......");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
