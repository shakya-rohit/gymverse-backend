package com.gymverse.backend.dto;

import java.util.List;

public class DashboardStatsResponse {
    private List<String> labels;
    private List<Integer> newMembers;
    private List<Integer> renewals;
	
	public DashboardStatsResponse(List<String> labels, List<Integer> newMembers, List<Integer> renewals) {
		super();
		this.labels = labels;
		this.newMembers = newMembers;
		this.renewals = renewals;
	}
	
	public List<String> getLabels() {
		return labels;
	}
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	public List<Integer> getNewMembers() {
		return newMembers;
	}
	public void setNewMembers(List<Integer> newMembers) {
		this.newMembers = newMembers;
	}
	public List<Integer> getRenewals() {
		return renewals;
	}
	public void setRenewals(List<Integer> renewals) {
		this.renewals = renewals;
	}
}