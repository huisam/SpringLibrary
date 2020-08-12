package com.huisam.springstudy.aws;

import software.amazon.awssdk.metrics.MetricCategory;
import software.amazon.awssdk.metrics.MetricLevel;
import software.amazon.awssdk.metrics.SdkMetric;

import java.util.Set;

public class S3SdkMetric implements SdkMetric<String> {
    @Override
    public String name() {
        return "apiCallDuration";
    }

    @Override
    public Set<MetricCategory> categories() {
        return Set.of(MetricCategory.HTTP_CLIENT);
    }

    @Override
    public MetricLevel level() {
        return MetricLevel.INFO;
    }

    @Override
    public Class<String> valueClass() {
        return String.class;
    }
}
