package com.drt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drt.entity.OrderItem;
import com.drt.entity.OrderItemExample;
import com.drt.mapper.OrderItemMapper;
import com.drt.service.api.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Override
	public List<OrderItem> getAll() {
		return orderItemMapper.selectByExample(new OrderItemExample());
	}

	@Override
	public OrderItem findOrdrtById(String orderId) {
		return orderItemMapper.selectByOrderId(orderId);
	}

}
