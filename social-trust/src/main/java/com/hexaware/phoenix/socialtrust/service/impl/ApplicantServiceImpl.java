package com.hexaware.phoenix.socialtrust.service.impl;

import com.hexaware.phoenix.socialtrust.constant.SocialProfileType;
import com.hexaware.phoenix.socialtrust.dao.ApplicantRepository;
import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.model.TwitterActivity;
import com.hexaware.phoenix.socialtrust.service.ApplicantService;
import com.hexaware.phoenix.socialtrust.service.MachineLearningService;
import com.hexaware.phoenix.socialtrust.service.SocialActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {

    @Autowired
    ApplicantRepository applicantRepository;

    @Autowired
    @Qualifier(value = "twitterActivityService")
    SocialActivityService twitterActivityService;

    @Autowired
    MachineLearningService machineLearningService;

    @Override
    public List<Applicant> getApplicants() {
        return applicantRepository.findAll();
    }

    @Override
    public Applicant createApplicant(Applicant applicant) {

        applicant.setApplicantTrustScore(new Double(-1L));
        applicant.setNumOfMFAAuths(new Long(2));
        applicant.setNumOfFailLogins(new Long(2));
        applicant.setNumOfDevices(new Long(2));
        applicant.setNumOfLockouts(new Long(3));
        applicant.setAcctAge(new Double(0.6));
        applicant.setFavorableCount(new Double(0));
        applicant.setUnFavorableCount(new Double(0));
        applicantRepository.save(applicant);
        return applicant;
    }

    @Override
    public Applicant retrieveApplicant(Long applicantId) {
        Applicant applicant = applicantRepository.findById(applicantId).orElse(null);
        return applicant;
    }

    @Override
    public Applicant addSocialProfile(Long applicantId, SocialProfileType socialProfileType, String socialProfileId) {
        Applicant applicant = retrieveApplicant(applicantId);
        switch (socialProfileType) {
            case TWITTER:
                applicant.setTwitterHandle(socialProfileId);
                break;
        }

        applicantRepository.save(applicant);

        return applicant;
    }

    @Override
    public Applicant generateSocialTrustScore(Long applicantId) {

        Applicant applicant = retrieveApplicant(applicantId);

        List<TwitterActivity> twitterActivities = twitterActivityService.fetchSocialActivityFromSocialService(applicant.getTwitterHandle());

        Iterator<TwitterActivity> iterator = twitterActivities.iterator();
        while (iterator.hasNext()) {
            TwitterActivity twitterActivity = iterator.next();
            twitterActivity.setApplicant(applicant);
            Boolean favorable = twitterActivityService.isFavorableActivity(twitterActivity);
            twitterActivity.setFavorable(favorable);
        }

        twitterActivityService.saveSocialActivity(twitterActivities);

        applicant.setFavorableCount(twitterActivityService.getActivivitySentiment(applicantId, true));
        applicant.setUnFavorableCount(twitterActivityService.getActivivitySentiment(applicantId, false));

        applicant.setApplicantTrustScore(machineLearningService.generateScore(applicant));

        applicantRepository.save(applicant);

        return applicant;
    }

}
