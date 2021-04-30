package com.eg.voicerecognize.test;

import com.alibaba.fastjson.JSON;
import com.eg.voicerecognize.bean.tencent.ResultDetail;

import java.util.List;

public class TestJSON {
    public static void main(String[] args) {
        String json = "[{\"FinalSentence\":\"北京的天气。\",\"SliceSentence\":\"北京 的 天气 。\",\"StartMs\":900,\"EndMs\":3101,\"SpeechSpeed\":3.6,\"WordsNum\":4,\"Words\":[{\"OffsetStartMs\":180,\"OffsetEndMs\":660,\"Word\":\"北京\"},{\"OffsetStartMs\":660,\"OffsetEndMs\":780,\"Word\":\"的\"},{\"OffsetStartMs\":780,\"OffsetEndMs\":1440,\"Word\":\"天气\"},{\"OffsetStartMs\":780,\"OffsetEndMs\":1440,\"Word\":\"。\"}]}]\n";
        List<ResultDetail> resultDetails = JSON.parseArray(json, ResultDetail.class);
        System.out.println(resultDetails);
    }
}
