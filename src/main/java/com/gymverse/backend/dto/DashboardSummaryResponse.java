package com.gymverse.backend.dto;

public class DashboardSummaryResponse {
    private int totalMembers;
    private int activePlans;
    private double totalRevenue;
	
	public DashboardSummaryResponse(int totalMembers, int activePlans, double totalRevenue) {
		super();
		this.totalMembers = totalMembers;
		this.activePlans = activePlans;
		this.totalRevenue = totalRevenue;
	}
	
	public int getTotalMembers() {
		return totalMembers;
	}
	public void setTotalMembers(int totalMembers) {
		this.totalMembers = totalMembers;
	}
	public int getActivePlans() {
		return activePlans;
	}
	public void setActivePlans(int activePlans) {
		this.activePlans = activePlans;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
}