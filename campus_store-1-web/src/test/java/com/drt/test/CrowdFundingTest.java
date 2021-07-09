package com.drt.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drt.entity.Admin;
import com.drt.entity.Goods;
import com.drt.mapper.AdminMapper;
import com.drt.mapper.GoodsMapper;
import com.drt.service.api.AdminService;
import com.drt.service.api.GoodsService;
import com.drt.util.GoodsCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml" })
public class CrowdFundingTest {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private AdminMapper adminMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();

		System.out.println(connection);
	}

	@Autowired
	private AdminService adminService;

	@Test
	public void testMybatis() {
		Goods goods = goodsMapper.selectByPrimaryKey(1);
		System.out.println(goods.toString());
	}

	@Test
	public void testMybatis2() {
		List<Admin> list = adminService.getAll();
		for (Admin admin : list) {
			System.out.println(admin);
		}
	}

	@Test
	public void batchSaveAdmin() {
		for (int i = 0; i < 20; i++) {
			adminMapper.insert(new Admin(null, "aaa" + i, "1111111", "aaa" + i, "aaa" + i + "@qq.com", null));
		}
//		for (int i = 0; i < 500; i++) {
//			adminMapper.insert(new Admin(null, "loginAcct" + i, "1111111", "userName" + i, "email" + i + "@qq.com", null));
//		}
	}

	@Test
	public void batchSaveGoods() {
		for (int i = 0; i < 50; i++) {
			goodsMapper.insert(
					new Goods(null, "java从入门到放弃"+i, BigDecimal.valueOf(i), 9999 + i, 9 + i, "static/img/default.jpg",GoodsCategory.OTHER));
		}
	}

}
