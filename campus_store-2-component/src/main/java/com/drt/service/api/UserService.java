package com.drt.service.api;

import java.util.List;

import com.drt.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

	PageInfo<User> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword);

	void batchRemove(List<Integer> adminIdList);

	void saveUser(User user);

	User getUserById(Integer userId);

	void updateUser(User user);

	User login(String loginAcct, String userPswd);

}
