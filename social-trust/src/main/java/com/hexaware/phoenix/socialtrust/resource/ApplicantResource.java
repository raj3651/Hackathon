package com.hexaware.phoenix.socialtrust.resource;

import com.hexaware.phoenix.socialtrust.dto.ApplicantsDto;
import com.hexaware.phoenix.socialtrust.dto.SocialProfile;
import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8086"})
@RequestMapping(value = "/socialtrust")
public class ApplicantResource {

    @Autowired
    ApplicantService applicantService;

    @RequestMapping(value = "/applicants", method = RequestMethod.GET)
    public ApplicantsDto getApplicants() {
        ApplicantsDto applicantsDto = new ApplicantsDto();
        applicantsDto.setApplicants(applicantService.getApplicants());
        return applicantsDto;
    }

    @RequestMapping(value = "/applicants", method = RequestMethod.POST)
    public Applicant createApplicant(@RequestBody Applicant applicant) {
        return applicantService.createApplicant(applicant);
    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.GET)
    public Applicant retrieveApplicant(@PathVariable(value = "applicantId") Long applicantId) {
        return applicantService.retrieveApplicant(applicantId);
    }

    @RequestMapping(value = "/applicants/{applicantId}", method = RequestMethod.PUT)
    public Applicant addSocialProfile(@PathVariable(value = "applicantId") Long applicantId, @RequestBody SocialProfile socialProfile) {
        return applicantService.addSocialProfile(applicantId, socialProfile.getSocialProfileType(), socialProfile.getSocialProfileId());
    }

    @RequestMapping(value = "/applicants/{applicantId}/generatetrustscore", method = RequestMethod.GET)
    public Applicant generateSocialTrustScore(@PathVariable(value = "applicantId") Long applicantId) {
        return applicantService.generateSocialTrustScore(applicantId);
    }
}
