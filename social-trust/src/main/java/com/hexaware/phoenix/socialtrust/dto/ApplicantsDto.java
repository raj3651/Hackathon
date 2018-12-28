package com.hexaware.phoenix.socialtrust.dto;

import com.hexaware.phoenix.socialtrust.model.Applicant;

import java.util.List;

public class ApplicantsDto {
    List<Applicant> applicants;

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(List<Applicant> applicants) {
        this.applicants = applicants;
    }
}
