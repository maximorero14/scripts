package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;

@Configuration
public class SqsConfig {

    @Value("${aws.sqs.endpoint}")
    private String sqsEnpoint;

    @Bean
    public SqsClient sqsClient() {
        return SqsClient.builder()
                .endpointOverride(URI.create(sqsEnpoint))
                .region(Region.US_EAST_1)
                .build();
    }
}