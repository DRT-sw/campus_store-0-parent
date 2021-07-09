package com.drt.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drt.entity.OrderItem;
import com.drt.entity.OrderItemExample;
import com.drt.entity.User;
import com.drt.mapper.OrderItemMapper;
import com.drt.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml" })
public class UserTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Test
	public void testMybatis6() {
		OrderItem item = orderItemMapper.selectByOrderId("162090429293342");
		System.out.println(item.toString());
		
	}
	
	@Test
	public void testMybatis5() {
		List<OrderItem> selectByExample = orderItemMapper.selectByExample(new OrderItemExample());
		for (OrderItem orderItem : selectByExample) {
			System.out.println(orderItem);
		}
		
	}
	
	
//	@Test
//	public void testMybatis() {
//		User user = userMapper.selectByPrimaryKey(1);
//		System.out.println(user.toString());
//
//	}
//
//	@Test
//	public void testMybatis2() {
//		for (int i = 0; i < 40; i++) {
//			userMapper.insert(new User(null, "aaa" + i, "1111111", "aaa" + i, "aaa" + i + "@qq.com", null));
//		}
//
//	}

}
