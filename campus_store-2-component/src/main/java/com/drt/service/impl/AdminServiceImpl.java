package com.drt.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drt.entity.Admin;
import com.drt.entity.AdminExample;
import com.drt.mapper.AdminMapper;
import com.drt.service.api.AdminService;
import com.drt.util.CampusUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public List<Admin> getAll() {
		List<Admin> admin = adminMapper.selectByExample(new AdminExample());
		return admin;
	}

	@Override
	public Admin login(String loginAcct, String userPswd) {
		// 根据loginAcct查询数据库
		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);

		// 执行查询
		List<Admin> list = adminMapper.selectByExample(adminExample);

		if (!CampusUtils.collectionEffective(list)) {
			// 如果查询结果集合无效，则直接返回null
			return null;
		}

		// 获取唯一集合元素
		Admin admin = list.get(0);

		// 确认admin不为null
		if (admin == null) {
			return null;
		}

		// 比较密码
		String userPswdDataBase = admin.getUserPswd();
		String userPswdBroswer = CampusUtils.md5(userPswd);

		if (Objects.equals(userPswdBroswer, userPswdDataBase)) {
			// 如果两个密码相等那么说明登录能够成功，返回Admin对象
			return admin;
		}

		return null;

	}

	@Override
	public PageInfo<Admin> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword) {

		// 1.调用PageHelper的工具方法，开启分页功能
		PageHelper.startPage(pageNum, pageSize);

		// 2.执行分页查询
		List<Admin> list = adminMapper.selectAdminListByKeyword(keyword);

		// 3.将list封装到PageInfo对象中
		return new PageInfo<>(list);
	}

	@Override
	public void batchRemove(List<Integer> adminIdList) {

		// QBC：Query By Criteria
		AdminExample adminExample = new AdminExample();

		// 通过使用Criteria对象，可以把Java代码转换成SQL语句中WHERE子句里面的具体查询条件
		adminExample.createCriteria().andIdIn(adminIdList);

		// 执行具体操作时把封装了查询条件的Example对象传入
		adminMapper.deleteByExample(adminExample);
	}

	@Override
	public void saveAdmin(Admin admin) {

		String userPswd = admin.getUserPswd();
		userPswd = CampusUtils.md5(userPswd);
		admin.setUserPswd(userPswd);

		// 执行保存
		adminMapper.insert(admin);

	}

	@Override
	public Admin getAdminById(Integer adminId) {
		Admin admin = adminMapper.selectByPrimaryKey(adminId);
		return admin;
	}

	@Override
	public void updateAdmin(Admin admin) {
		String userPswd = admin.getUserPswd();
		userPswd = CampusUtils.md5(userPswd);
		admin.setUserPswd(userPswd);
		
		// 执行更新
		adminMapper.updateByPrimaryKey(admin);

	}
}
