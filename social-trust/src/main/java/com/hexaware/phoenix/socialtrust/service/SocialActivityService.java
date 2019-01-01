package com.hexaware.phoenix.socialtrust.service;

import com.hexaware.phoenix.socialtrust.model.TwitterActivity;

import java.util.List;
import java.util.Set;

public interface SocialActivityService<T> {
    List<T> fetchSocialActivityFromSocialService(String SocialId);
    List<T> saveSocialActivity(List<T> activities);
    List<T> retrieveSocialActivity(Long applicantId);
    Boolean isFavorableActivity(T activity);

}
