package com.gymverse.backend.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Gym {

    private String gymId;
    private String gymName;
    private String ownerUsername;
    private String contactEmail;

    @DynamoDbPartitionKey
    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}