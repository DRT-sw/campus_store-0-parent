package com.drt.service.impl;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drt.entity.Cart;
import com.drt.entity.CartItem;
import com.drt.entity.Goods;
import com.drt.entity.Order;
import com.drt.entity.OrderExample;
import com.drt.entity.OrderItem;
import com.drt.mapper.GoodsMapper;
import com.drt.mapper.OrderItemMapper;
import com.drt.mapper.OrderMapper;
import com.drt.service.api.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private OrderItemMapper orderItemMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public String saveOrder(Cart cart, int userId) {
		// 创建订单对象
		Order order = new Order();
		order.setCreateTime(new Date());
		order.setTotalMoney(cart.getTotalMoney());
		order.setUserId(userId);
		order.setStatus(0);
		// 生成订单号
		String orderId = System.currentTimeMillis() + "" + userId;
		order.setOrderId(orderId);
		// 保存订单
		orderMapper.insert(order);

		// 遍历购物车中有每一个商品
//		List<OrderItem> items = new ArrayList<OrderItem>();
		for (CartItem cartItem : cart.getItems().values()) {
			// 生成订单项
			OrderItem orderItem = new OrderItem(0, cartItem.getName(), BigDecimal.valueOf(cartItem.getPrice()),
					BigDecimal.valueOf(cartItem.getTotalMoney()), cartItem.getCount(), orderId);
			// 保存订单项
			orderItemMapper.insert(orderItem);

//			items.add(orderItem);
			//修改商品的库存和销量
			Goods goods = goodsMapper.selectByPrimaryKey(cartItem.getId());
			goods.setGoodsStock(goods.getGoodsStock() - cartItem.getCount());
			goods.setGoodsSales(goods.getGoodsSales() + cartItem.getCount());
			goodsMapper.updateByPrimaryKey(goods);
		}
		//清空购物车
		cart.clear();

		return orderId;
	}

	@Override
	public List<Order> findAll() {
		List<Order> list = orderMapper.selectByExample(new OrderExample());
		return list;
	}

}
