package com.hexaware.phoenix.socialtrust.service;

public interface MachineLearningService {
    boolean performSentimentAnalysis(String data);
    long generateScore();
}
