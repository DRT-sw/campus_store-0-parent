package com.drt.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drt.entity.Admin;
import com.drt.entity.AdminExample;
import com.drt.entity.User;
import com.drt.entity.UserExample;
import com.drt.mapper.UserMapper;
import com.drt.service.api.UserService;
import com.drt.util.CampusUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public PageInfo<User> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword) {
		// 1.调用PageHelper的工具方法，开启分页功能
		PageHelper.startPage(pageNum, pageSize);

		// 2.执行分页查询
		List<User> list = userMapper.selectUserListByKeyword(keyword);
//		for (User user : list) {
//			System.out.println(user.toString());
//		}

		// 3.将list封装到PageInfo对象中
		return new PageInfo<>(list);
	}

	@Override
	public void batchRemove(List<Integer> adminIdList) {

		UserExample userExample = new UserExample();

		userExample.createCriteria().andIdIn(adminIdList);

		userMapper.deleteByExample(userExample);
	}

	@Override
	public void saveUser(User user) {
		String userPswd = user.getUserPswd();
		userPswd = CampusUtils.md5(userPswd);
		user.setUserPswd(userPswd);

		userMapper.insert(user);
	}

	@Override
	public User getUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public void updateUser(User user) {
		String userPswd = user.getUserPswd();
		userPswd = CampusUtils.md5(userPswd);
		user.setUserPswd(userPswd);

		// 执行更新
		userMapper.updateByPrimaryKey(user);

	}

	@Override
	public User login(String loginAcct, String userPswd) {
		// 根据loginAcct查询数据库
		UserExample userExample = new UserExample();

		userExample.createCriteria().andLoginAcctEqualTo(loginAcct);

		// 执行查询
		List<User> list = userMapper.selectByExample(userExample);

		if (!CampusUtils.collectionEffective(list)) {
			// 如果查询结果集合无效，则直接返回null
			return null;
		}

		// 获取唯一集合元素
		User user = list.get(0);

		// 确认admin不为null
		if (user == null) {
			return null;
		}

		// 比较密码
		String userPswdDataBase = user.getUserPswd();
		String userPswdBroswer = CampusUtils.md5(userPswd);

		if (Objects.equals(userPswdBroswer, userPswdDataBase)) {
			// 如果两个密码相等那么说明登录能够成功，返回Admin对象
			return user;
		}

		return null;

	}
}
