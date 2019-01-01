package com.hexaware.phoenix.socialtrust.constant;

import lombok.Getter;

public enum SocialProfileType {
    TWITTER("twitter"),
    FACEBOOK("facebook"),
    LINKEDIN("linkedin"),
    GOOGLE_MAPS("goolgeMaps");

    private @Getter String profileName;

    SocialProfileType(String profileName) {
        this.profileName = profileName;
    }
}
