package com.eg.voicerecognize.profile;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileTencent extends Profile {
    private String secretId;
    private String secretKey;
    private String endpoint;
}
