package com.hexaware.phoenix.socialtrust.dto;

import com.hexaware.phoenix.socialtrust.model.Applicant;
import lombok.Data;

import java.util.List;

public @Data class ApplicantsDto {
    List<Applicant> applicants;
}
