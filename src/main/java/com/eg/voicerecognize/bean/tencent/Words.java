/**
 * Copyright 2021 json.cn
 */
package com.eg.voicerecognize.bean.tencent;

/**
 * Auto-generated: 2021-04-30 19:38:18
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class Words {

    private int OffsetStartMs;
    private int OffsetEndMs;
    private String Word;

    public void setOffsetStartMs(int OffsetStartMs) {
        this.OffsetStartMs = OffsetStartMs;
    }

    public int getOffsetStartMs() {
        return OffsetStartMs;
    }

    public void setOffsetEndMs(int OffsetEndMs) {
        this.OffsetEndMs = OffsetEndMs;
    }

    public int getOffsetEndMs() {
        return OffsetEndMs;
    }

    public void setWord(String Word) {
        this.Word = Word;
    }

    public String getWord() {
        return Word;
    }

}