package com.eg.voicerecognize.profile;

import com.eg.voicerecognize.CloudType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileTencent extends Profile {
    private CloudType cloudType;

    private String secretId;
    private String secretKey;
    private String endpoint;
}
