package com.example.sqsListener.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.SimpleMessageListenerContainerFactory;
import org.springframework.cloud.aws.messaging.config.annotation.SqsClientConfiguration;
import org.springframework.cloud.aws.messaging.config.annotation.SqsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

@Configuration
@Import({SqsClientConfiguration.class, SqsConfiguration.class})
public class AwsCloudConfig {

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider(@Value("${aws.sqs.accessKey}") String accessKey, @Value("${aws.sqs.secretKey}") String secretKey) {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey));
    }

    @Bean
    @Primary
    @Qualifier("amazonSQSAsync")
    public AmazonSQSAsync amazonSQSAsync(@Value("${aws.sqs.region}") String region, @Value("${aws.sqs.accessKey}") String accessKey, @Value("${aws.sqs.secretKey}") String secretKey) {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonSQSAsyncClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

    @Bean
    @Primary
    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSqs) {
        SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
        factory.setAmazonSqs(amazonSqs);
        factory.setMaxNumberOfMessages(1);
        factory.setWaitTimeOut(10);
        return factory;
    }

}
