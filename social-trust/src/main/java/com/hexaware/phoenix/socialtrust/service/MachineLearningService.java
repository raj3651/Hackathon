package com.hexaware.phoenix.socialtrust.service;

import com.hexaware.phoenix.socialtrust.model.Applicant;

public interface MachineLearningService {
    Boolean performSentimentAnalysis(String data);
    Double generateScore(Applicant applicant);
}
