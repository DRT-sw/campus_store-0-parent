package com.drt.service.api;

import java.util.List;

import com.drt.entity.OrderItem;

public interface OrderItemService {

	List<OrderItem> getAll();

	OrderItem findOrdrtById(String orderId);

}
