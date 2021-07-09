package com.drt.service.api;

import java.util.List;

import com.drt.entity.Admin;
import com.github.pagehelper.PageInfo;

public interface AdminService {

	List<Admin> getAll();

	Admin login(String loginAcct, String userPswd);
	
	PageInfo<Admin> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword);

	void batchRemove(List<Integer> adminIdList);

	void saveAdmin(Admin admin);

	Admin getAdminById(Integer adminId);

	void updateAdmin(Admin admin);

}
