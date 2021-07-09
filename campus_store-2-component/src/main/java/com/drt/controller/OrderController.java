package com.drt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.drt.entity.Cart;
import com.drt.entity.Order;
import com.drt.entity.OrderItem;
import com.drt.entity.User;
import com.drt.service.api.OrderItemService;
import com.drt.service.api.OrderService;
import com.drt.util.CampusConstant;

@Controller
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;

	@RequestMapping("createOrder")
	protected String createOrder(HttpSession session) {
		User user = (User) session.getAttribute(CampusConstant.ATTR_NAME_LOGIN_ADMIN);
		if (user == null) {
			return "user-login";
		} else {
			Cart cart = (Cart) session.getAttribute("cart");
			int userId = user.getId();
			String orderId = orderService.saveOrder(cart, userId);
			session.setAttribute("orderId", orderId);
			return "checkout";
		}
	}
	
	@RequestMapping("all")
	public String findAll(Model model) {
		List<Order> list = orderService.findAll();
		model.addAttribute("myOrders", list);
		return "order-page";
	}

	@RequestMapping("details")
	public String details(@RequestParam(value = "orderId") String orderId,Model model) {
		OrderItem orderItem = orderItemService.findOrdrtById(orderId);
		model.addAttribute("items", orderItem);
		return "order-msg";
	}
}
