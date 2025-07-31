package com.gymverse.backend.dto;

public class AuthResponse {
    private String token;
    private String role;
    private String tenantId;

    public AuthResponse(String token, String role, String tenantId) {
        this.token = token;
        this.role = role;
        this.tenantId = tenantId;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
}