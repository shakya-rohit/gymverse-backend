package com.gymverse.backend.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class User {

    private String username;
    private String password;
    private String role;
    private String emailId;
    private String name;
    private String gymName;
    private String tenantId;

    public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@DynamoDbPartitionKey
    @DynamoDbAttribute("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @DynamoDbAttribute("password")
    public String getPassword() {
        return password;
    }

    public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGymName() {
		return gymName;
	}

	public void setGymName(String gymName) {
		this.gymName = gymName;
	}

	public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDbAttribute("role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}