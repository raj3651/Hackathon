package com.hexaware.phoenix.socialtrust.dto;

import com.hexaware.phoenix.socialtrust.constant.SocialProfileType;
import lombok.Data;

public @Data class SocialProfile {
    SocialProfileType socialProfileType;
    String socialProfileId;
}
