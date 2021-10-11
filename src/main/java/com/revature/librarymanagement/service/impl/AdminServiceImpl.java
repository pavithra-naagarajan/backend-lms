package com.revature.librarymanagement.service.impl;

import java.util.List;
import static com.revature.librarymanagement.util.LibraryManagementConstants.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.librarymanagement.dao.AdminDAO;
import com.revature.librarymanagement.dto.AdminDto;
import com.revature.librarymanagement.exception.DuplicateIdException;
import com.revature.librarymanagement.exception.IdNotFoundException;
import com.revature.librarymanagement.exception.MethodArgumentNotValidException;
import com.revature.librarymanagement.exception.NullValueException;
import com.revature.librarymanagement.mapper.AdminMapper;
import com.revature.librarymanagement.model.Admin;
import com.revature.librarymanagement.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public Admin getAdminById(Long adminId) {
		logger.info("Entering get Admin By Id Function");

		if (adminDAO.isAdminExists(adminId))
			return adminDAO.getAdminById(adminId);
		else
			throw new IdNotFoundException(ID_NOT_FOUND);

	}

	@Override
	public List<Admin> getAdminByName(String adminName) {
		logger.info("Entering get Admin By name Function");

		List<Admin> admins = adminDAO.getAdminByName(adminName);
		if (CollectionUtils.isEmpty(admins))
			throw new NullValueException(NO_RECORDS);
		return admins;

	}

	@Override
	public List<Admin> getAdminByRole(String adminRole) {
		logger.info("Entering get Admin By role Function");

		List<Admin> admins = adminDAO.getAdminByRole(adminRole);
		if (CollectionUtils.isEmpty(admins))
			throw new NullValueException(NO_RECORDS);
		return admins;

	}

	@Override
	public boolean isAdminExists(Long adminId) {
		logger.info("Entering is admin exists Function");

		return adminDAO.isAdminExists(adminId);
	}

	@Override
	public List<Admin> getAllAdmins() {
		logger.info("Entering get all admins Function");

		List<Admin> admins = adminDAO.getAllAdmins();
		if (CollectionUtils.isEmpty(admins))
			throw new NullValueException(NO_RECORDS);
		return admins;
	}

	@Override
	public String deleteAdminById(Long adminId) {
		logger.info("Entering delete Admin By Id Function");

		if (adminDAO.isAdminExists(adminId)) {
			return adminDAO.deleteAdminById(adminId);
		}
		throw new IdNotFoundException(NOT_FOUND_TODELETE);

	}

	@Override
	public String addAdmin(AdminDto adminDto) throws MethodArgumentNotValidException {
		logger.info("Entering add Admin Function");

		Admin admin = AdminMapper.dtoToEntity(adminDto);
		Long adminId = admin.getAdminId();

		if (adminDAO.isAdminExists(adminId)) {
			throw new DuplicateIdException(ADMIN_EXIST_ALREADY);

		}

		try {
			return adminDAO.addAdmin(admin);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			throw new MethodArgumentNotValidException(VALIDATION_FAIL);
		}
	}

	@Override
	public String updateAdmin(AdminDto adminDto) {
		logger.info("Entering update Admin  Function");

		Admin admin = AdminMapper.dtoToEntity(adminDto);
		Long adminId = admin.getAdminId();
		if (adminDAO.isAdminExists(adminId)) {
			return adminDAO.updateAdmin(admin);
		}
		throw new IdNotFoundException(NOT_FOUND_TOUPDATE);

	}

	@Override
	public Admin adminLogin(Long adminId, String adminPassword) {
		logger.info("Entering admin login Function");
		if (adminDAO.isAdminExists(adminId))
			return adminDAO.adminLogin(adminId, adminPassword);
		else
			throw new IdNotFoundException(ID_NOT_FOUND);

	}

}
