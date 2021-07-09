package com.drt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortaController {

	@RequestMapping("/index")
	public String showIndex() {
		return "redirect:/goods/goods-get";
	}
//	@RequestMapping("/index")
//	public String showIndex() {
//		return "index-page";
//	}
	
	@RequestMapping("Hello")
	public String toHello() {
		return "Hello";
	}
	
	@RequestMapping("admin-main")
	public String toAdminMain() {
		return "admin-main";
	}
	
	@RequestMapping("user/to/reg")
	public String toReg() {
		return "user-reg";
	}

	@RequestMapping("goods-main")
	public String toGoodsMain() {
		return "goods-page";
	}
	
	@RequestMapping("goods/to/add/page")
	public String toGoodsAdd() {
		return "goods-add";
	}
	
	@RequestMapping("user/login")
	public String toUserLogin() {
		return "user-login";
	}
	
	@RequestMapping("user/to/add/page")
	public String toUserAdd() {
		return "user-add";
	}
	
	@RequestMapping("car/page")
	public String toCarPage() {
		return "car-page";
	}
	
//	@RequestMapping("order/createOrder")
//	public String toOrderPage() {
//		return "order-page";
//	}
	
	
	
	
	
}
