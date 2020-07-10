package com.bibleit.bibleitmono.connection;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.bibleit.bibleitmono.pojo.QuestionAnswerImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Component
public class S3RemoteDataConnection implements RemoteDataConnection{
    private AWSCredentials credentials = null;
    private AmazonS3 s3client = null;
    private Map<String, String> env = System.getenv();
    private QuestionAnswerImpl[] data = null;

    @Override
    public QuestionAnswerImpl[] getAllResponse() {
        return data;
    }
    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public boolean setConnection(){

        credentials = new BasicAWSCredentials(env.get("S3_ACCESS_ID"),env.get("S3_ACCESS_KEY"));
        s3client= AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_WEST_2)
                .build();

        S3Object s3object = s3client.getObject(env.get("S3_BUCKET_NAME"), "mydata.json");
        S3ObjectInputStream inputStream = s3object.getObjectContent();


        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(inputStream, QuestionAnswerImpl[].class);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
