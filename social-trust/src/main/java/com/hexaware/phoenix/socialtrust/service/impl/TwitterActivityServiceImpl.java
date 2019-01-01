package com.hexaware.phoenix.socialtrust.service.impl;

import com.hexaware.phoenix.socialtrust.dao.ApplicantRepository;
import com.hexaware.phoenix.socialtrust.dao.TwitterActivityRepository;
import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.model.TwitterActivity;
import com.hexaware.phoenix.socialtrust.service.MachineLearningService;
import com.hexaware.phoenix.socialtrust.service.SocialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service(value = "twitterActivityService")
public class TwitterActivityServiceImpl implements SocialActivityService<TwitterActivity> {

    @Autowired
    TwitterActivityRepository twitterActivityRepository;

    @Inject
    private Environment environment;

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    MachineLearningService machineLearningService;

    @Override
    public List<TwitterActivity> fetchSocialActivityFromSocialService(String socialId) {

        Twitter twitter = new TwitterTemplate(
                environment.getProperty("twitter.consumerKey"),
                environment.getProperty("twitter.consumerSecret"),
                environment.getProperty("twitter.accessToken"),
                environment.getProperty("twitter.accessTokenSecret"));

        SearchResults searchResults = twitter.searchOperations().search(socialId);
        List<TwitterActivity> results = new ArrayList<>();

        for (Tweet t : searchResults.getTweets()) {
            TwitterActivity twitterActivity = new TwitterActivity();
            twitterActivity.setActivity(t.getText());
            twitterActivity.setActivityDateTime(t.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            results.add(twitterActivity);
        }
        return results;
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
    public Boolean isFavorableActivity(TwitterActivity activity) {
        return machineLearningService.performSentimentAnalysis(activity.getActivity());
    }

}
