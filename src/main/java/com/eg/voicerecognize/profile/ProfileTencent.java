package com.eg.voicerecognize.profile;

import com.eg.voicerecognize.CloudType;
import lombok.Data;

@Data
public class ProfileTencent extends Profile {
    private CloudType cloudType;

    private String secretId;
    private String secretKey;
    private String endpoint;
}
