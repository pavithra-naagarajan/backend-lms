package com.revature.librarymanagement.mapper;

import com.revature.librarymanagement.dto.AdminDto;
import com.revature.librarymanagement.model.Admin;

public class AdminMapper {

	// dto to entity mapping
	public static Admin dtoToEntity(AdminDto adminDto) {
		Admin admin = new Admin();
		admin.setAdminId(adminDto.getAdminId());
		admin.setAdminName(adminDto.getAdminName());
		admin.setAdminRole(adminDto.getAdminRole());
		admin.setAdminPassword(adminDto.getAdminPassword());
		admin.setMailId(adminDto.getMailId());
		admin.setCreatedOn(adminDto.getCreatedOn());
		admin.setUpdatedOn(adminDto.getUpdatedOn());
		return admin;

	}
}
