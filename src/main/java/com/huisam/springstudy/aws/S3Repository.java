package com.huisam.springstudy.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.nio.netty.NettyNioAsyncHttpClient;
import software.amazon.awssdk.metrics.publishers.cloudwatch.CloudWatchMetricPublisher;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class S3Repository {
    private final String bucketName = "dev-huisam";
    private S3AsyncClient s3Client;

    public void init() {
        final SdkAsyncHttpClient sdkAsyncHttpClient = NettyNioAsyncHttpClient.builder()
                .maxConcurrency(100)
                .maxPendingConnectionAcquires(10_000)
                .build();

        final ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        final Region region = Region.AP_NORTHEAST_2;

        final CloudWatchAsyncClient cloudWatchAsyncClient = CloudWatchAsyncClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        CloudWatchMetricPublisher cloudWatchMetricPublisher = CloudWatchMetricPublisher.builder()
                .detailedMetrics(List.of(new S3SdkMetric()))
                .cloudWatchClient(cloudWatchAsyncClient)
                .build();

        s3Client = S3AsyncClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .httpClient(sdkAsyncHttpClient)
                .overrideConfiguration(o -> o.addMetricPublisher(cloudWatchMetricPublisher))
                .build();
    }

    public void save(String name, String id) {
        final CompletableFuture<PutObjectResponse> completableFuture = s3Client.putObject(
                PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(id)
                        .build(),
                AsyncRequestBody.fromString(name)
        );

        completableFuture.join();
    }

    public String findById(String id) {
        final CompletableFuture<String> completableFuture = s3Client.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(id)
                        .build(),
                AsyncResponseTransformer.toBytes()
        ).thenApply(getObjectResponseResponseBytes -> {
            final Map<String, String> metadata = getObjectResponseResponseBytes.response().metadata();
            System.out.println(metadata);
            return new String(getObjectResponseResponseBytes.asByteArray());
        });

        return completableFuture.join();
    }

    public void deleteAll() {
        s3Client.deleteObjects(
                DeleteObjectsRequest.builder()
                        .bucket(bucketName)
                        .delete(
                                Delete.builder()
                                        .objects(
                                                ObjectIdentifier
                                                        .builder()
                                                        .key("*")
                                                        .build()
                                        ).build()
                        ).build()
        ).join();
    }
}
