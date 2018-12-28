package com.hexaware.phoenix.socialtrust.constant;

public enum SocialProfileType {
    TWITTER("twitter"),
    FACEBOOK("facebook"),
    LINKEDIN("linkedin"),
    GOOGLE_MAPS("goolgeMaps");

    private String profileName;

    SocialProfileType(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }
}
