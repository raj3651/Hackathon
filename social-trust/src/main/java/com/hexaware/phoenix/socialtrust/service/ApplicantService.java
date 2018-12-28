package com.hexaware.phoenix.socialtrust.service;

import com.hexaware.phoenix.socialtrust.constant.SocialProfileType;
import com.hexaware.phoenix.socialtrust.model.Applicant;

import java.util.List;

public interface ApplicantService {
    List<Applicant> getApplicants();
    Applicant createApplicant(Applicant applicant);
    Applicant retrieveApplicant(Long applicantId);
    Applicant addSocialProfile(Long applicantId, SocialProfileType socialProfileType, String socialProfileId);
    Applicant generateSocialTrustScore(Long applicantId);
}
