package com.gymverse.backend.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Member {
    private String memberId;
    private String name;
    private int age;
    private String membership;
    private String status;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("member_id")
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getMembership() { return membership; }
    public void setMembership(String membership) { this.membership = membership; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}