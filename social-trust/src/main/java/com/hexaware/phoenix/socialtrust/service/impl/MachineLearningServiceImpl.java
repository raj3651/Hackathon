package com.hexaware.phoenix.socialtrust.service.impl;

import com.amazonaws.services.machinelearning.AmazonMachineLearning;
import com.amazonaws.services.machinelearning.AmazonMachineLearningClientBuilder;
import com.amazonaws.services.machinelearning.model.GetMLModelRequest;
import com.amazonaws.services.machinelearning.model.GetMLModelResult;
import com.amazonaws.services.machinelearning.model.PredictRequest;
import com.amazonaws.services.machinelearning.model.PredictResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hexaware.phoenix.socialtrust.model.Applicant;
import com.hexaware.phoenix.socialtrust.service.MachineLearningService;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Service
public class MachineLearningServiceImpl implements MachineLearningService {

    public static Logger logger = LoggerFactory.getLogger(MachineLearningServiceImpl.class);

    private static AmazonMachineLearning client;

    @Inject
    private Environment environment;

    @Override
    public Boolean performSentimentAnalysis(String data) {

        data = cleanTweet(data);

        MonkeyLearn ml = new MonkeyLearn(environment.getProperty("monkeyLearn.token"));
        String modelId = environment.getProperty("monkeyLearn.modelId");
        MonkeyLearnResponse res = null;
        try {
            res = ml.classifiers.classify(modelId, new String[]{data}, false);
            String string = res.arrayResult.toString();
            logger.info("Monkey Learn API Result : " + string +  " for following tweet : " + data);
            if (string.contains("Negative")) {
                return Boolean.FALSE;
            } else if (string.contains("Positive")) {
                return Boolean.TRUE;
            }
        }catch (MonkeyLearnException e){
            logger.info("Caught MonkeyLearnException in performing sentiment analysis......." + e.getMessage());
            e.printStackTrace();
            return Boolean.FALSE;
        }catch (Exception e){
            logger.info("Caught Exception in performing sentiment analysis ......." + e.getMessage());
            e.printStackTrace();
            return Boolean.FALSE;
        }

        return Boolean.FALSE;
    }


    private String cleanTweet(String tweetData) {
        // make tweet lower case
        tweetData = tweetData.toLowerCase();
        // clean the tweets data
        tweetData = tweetData.replaceAll("(@[A-Za-z0-9_]+)|([^0-9A-Za-z \\t])|(\\w+:\\/\\/\\S+)", " ");
        return tweetData;
    }

    @Override
    public Double generateScore(Applicant applicant) {

        Double predictedTrustScore= new Double(0);

        if(applicant.getNumOfDevices()!=null
           || applicant.getNumOfLockouts()!=null
            || applicant.getNumOfFailLogins()!=null
             || applicant.getNumOfMFAAuths()!=null
              || applicant.getAcctAge()!=null){
            predictedTrustScore = new Double(getPrediction(applicant));
        }

        return predictedTrustScore;
    }

    private long getRandomDoubleBetweenRange(long leftLimit, long rightLimit) {
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

    public Float getPrediction(Applicant applicant) {
        client = AmazonMachineLearningClientBuilder.defaultClient();
        PredictResult result = client.predict(buildPredictionRequest(applicant));
        logger.info("Predicted Result Object from Amazon : " + result.toString());
        Float predictedValue = result.getPrediction().getPredictedValue();
        logger.info("Predicted Score from Amazon Machine Learning Model : " + predictedValue);
        return predictedValue;
    }

    private  Map<String, String> buildCustomerMap(Applicant applicant) {
        Map<String, String> applicantMap = new ObjectMapper().convertValue(applicant, Map.class);
        Map<String, String> applicantStringMap = new HashMap<>();
        for (Map.Entry entry : applicantMap.entrySet()) {
             if( entry.getKey().toString().equals("numOfDevices")
                     || entry.getKey().toString().equals("numOfLockouts")
                     || entry.getKey().toString().equals("numOfFailLogins")
                     || entry.getKey().toString().equals("numOfMFAAuths")
                     || entry.getKey().toString().equals("acctAge")) {

                 applicantStringMap.put(entry.getKey().toString(), entry.getValue().toString());
             }
        }
        logger.info("applicantStringMap =" + applicantStringMap);
        return applicantStringMap;
    }

    private PredictRequest buildPredictionRequest(Applicant applicant) {
        Map<String, String> applicantMap = buildCustomerMap(applicant);
        PredictRequest request = new PredictRequest()
                .withMLModelId(environment.getProperty("amazon.modelId"))
                .withPredictEndpoint(getModelEndpoint())
                .withRecord(applicantMap);
        return request;
    }

    private String getModelEndpoint() {
        GetMLModelRequest request = new GetMLModelRequest().withMLModelId(environment.getProperty("amazon.modelId"));
        GetMLModelResult model = client.getMLModel(request);
        return model.getEndpointInfo().getEndpointUrl();
    }

}
