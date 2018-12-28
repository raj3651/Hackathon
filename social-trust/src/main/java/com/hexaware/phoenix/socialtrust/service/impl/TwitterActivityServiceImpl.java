package com.hexaware.phoenix.socialtrust.service.impl;

import com.hexaware.phoenix.socialtrust.dao.ApplicantRepository;
import com.hexaware.phoenix.socialtrust.dao.TwitterActivityRepository;
import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.model.TwitterActivity;
import com.hexaware.phoenix.socialtrust.service.MachineLearningService;
import com.hexaware.phoenix.socialtrust.service.SocialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "twitterActivityService")
public class TwitterActivityServiceImpl implements SocialActivityService<TwitterActivity> {

    @Autowired
    TwitterActivityRepository twitterActivityRepository;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    MachineLearningService machineLearningService;

    @Override
    public List<TwitterActivity> fetchSocialActivityFromSocialService(String SocialId) {
        // TODO fetch user public activity
        return new ArrayList<TwitterActivity>();
    }

    @Override
    public List<TwitterActivity> saveSocialActivity(List<TwitterActivity> activities) {
        List<TwitterActivity> twitterActivities = twitterActivityRepository.saveAll(activities);
        return twitterActivities;
    }

    @Override
    public List<TwitterActivity> retrieveSocialActivity(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId).orElse(null);
        if(applicant != null) {
            return twitterActivityRepository.findAllByApplicant(applicant);
        }
        return null;
    }

    @Override
    public boolean isFavorableActivity(TwitterActivity activity) {
        return machineLearningService.performSentimentAnalysis(activity.getActivity());
    }
}
