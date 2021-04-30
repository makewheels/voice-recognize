package com.eg.voicerecognize.profile;

import com.eg.voicerecognize.CloudType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileAliyun extends Profile {
    private CloudType cloudType;

    private String accessKeyId;
    private String accessKeySecret;
    private String regionId;
    private String endpoint;
    private String product;
    private String domain;
    private String appkey;
}
