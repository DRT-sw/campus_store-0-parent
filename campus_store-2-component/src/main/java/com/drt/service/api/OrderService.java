package com.drt.service.api;

import java.util.List;

import com.drt.entity.Cart;
import com.drt.entity.Order;

public interface OrderService {

	String saveOrder(Cart cart, int userId);

	List<Order> findAll();

}
