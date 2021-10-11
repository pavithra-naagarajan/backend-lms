package com.revature.librarymanagement.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class AdminDto {
	
	private Long adminId;
	@NotNull
	private String adminName;
	@NotNull
	private String adminRole;
	@NotNull
	private String adminPassword;
	@NotNull
	private String mailId;
	private Date updatedOn;
	private Date createdOn;

	public AdminDto() {

	}

	public AdminDto(Long adminId, String adminName, String adminRole, String adminPassword, String mailId,
			Date updatedOn, Date createdOn) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.adminPassword = adminPassword;
		this.mailId = mailId;
		this.updatedOn = updatedOn;
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminRole() {
		return adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "AdminDto [adminId=" + adminId + ", adminName=" + adminName + ", adminRole=" + adminRole
				+ ", adminPassword=" + adminPassword + ", mailId=" + mailId + ", updatedOn=" + updatedOn
				+ ", createdOn=" + createdOn + "]";
	}

}
