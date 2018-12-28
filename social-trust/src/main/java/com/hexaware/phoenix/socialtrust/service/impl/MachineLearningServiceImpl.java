package com.hexaware.phoenix.socialtrust.service.impl;

import com.hexaware.phoenix.socialtrust.service.MachineLearningService;
import org.springframework.stereotype.Service;

@Service
public class MachineLearningServiceImpl implements MachineLearningService {
    @Override
    public boolean performSentimentAnalysis(String data) {
        // TODO Derive favorable/not favorable using Sentiment analysis
        return false;
    }

    @Override
    public long generateScore() {   // dto needs to be designed
        // TODO Calculate trust score using Linear Regression on target column Favorable
        return getRandomDoubleBetweenRange(0L, 10L);
    }

    private long getRandomDoubleBetweenRange(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

}
