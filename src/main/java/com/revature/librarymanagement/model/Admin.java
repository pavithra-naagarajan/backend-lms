package com.revature.librarymanagement.model;

import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admins_table")

public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adminId;

	@NotNull
	@Column(nullable = false)
	private String adminName;

	@NotNull
	@Column(nullable = false)
	private String adminRole;

	@NotNull
	@Column(nullable = false, unique = true)
	private String mailId;

	@NotNull
	@Column(nullable = false)
	private String adminPassword;
	
	@Temporal(TemporalType.DATE)
	private Date createdOn;
	@Temporal(TemporalType.DATE)
	private Date updatedOn;

	public Admin() {

	}

	public Admin(Long adminId, String adminName, String adminRole, String mailId, String adminPassword, Date createdOn,
			Date updatedOn) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.mailId = mailId;
		this.adminPassword = adminPassword;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}

	public Admin(Long adminId, String adminName, String adminRole, String mailId, String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminRole = adminRole;
		this.mailId = mailId;
		this.adminPassword = adminPassword;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminRole=" + adminRole + ", mailId="
				+ mailId + ", adminPassword=" + adminPassword + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn
				+ "]";
	}

}
