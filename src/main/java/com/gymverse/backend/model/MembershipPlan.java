package com.gymverse.backend.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class MembershipPlan {

    private String tenantId;
    private String planId;
    private String name;
    private String features;
    private double price;
    private int durationInMonths;
    
    @DynamoDbPartitionKey
    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @DynamoDbSortKey
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        this.durationInMonths = durationInMonths;
    }
}
