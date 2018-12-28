package com.hexaware.phoenix.socialtrust.dto;

import com.hexaware.phoenix.socialtrust.constant.SocialProfileType;

public class SocialProfile {
    SocialProfileType socialProfileType;
    String socialProfileId;

    public SocialProfileType getSocialProfileType() {
        return socialProfileType;
    }

    public void setSocialProfileType(SocialProfileType socialProfileType) {
        this.socialProfileType = socialProfileType;
    }

    public String getSocialProfileId() {
        return socialProfileId;
    }

    public void setSocialProfileId(String socialProfileId) {
        this.socialProfileId = socialProfileId;
    }
}
