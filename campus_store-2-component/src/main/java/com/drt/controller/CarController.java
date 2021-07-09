package com.drt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drt.entity.Cart;
import com.drt.entity.CartItem;
import com.drt.entity.Goods;
import com.drt.service.api.GoodsService;

@Controller
@RequestMapping("car")
public class CarController {

	@Autowired
	private GoodsService goodsService;
	
	@ResponseBody
	@RequestMapping("add")
	protected void addGoodsToCar(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Integer id) throws Exception {
		// 根据id查找商品
		Goods goods = goodsService.getGoodsById(id);
		
		// 把goods变成CartItem
		CartItem cartItem = new CartItem(goods.getId(), goods.getGoodsName(),
				Double.parseDouble(goods.getGoodsPrice() + ""), Double.parseDouble(goods.getGoodsPrice() + ""), 1);

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		request.getSession().setAttribute("last_name", cartItem.getName());

		// 调用cart.addItem添加商品项
		cart.addItem(cartItem);
		System.out.println(cart);

		// 跳回请求发起时的页面
		response.sendRedirect(request.getHeader("Referer"));
	}

	@RequestMapping("delete")
	protected void deleteItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Integer id) throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.deleteItem(id);
		response.sendRedirect(request.getHeader("Referer"));
	}

	@RequestMapping("update")
	protected void updateItem(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id") Integer id, @RequestParam(value = "count") Integer count)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.updateItem(id, count);
		response.sendRedirect(request.getHeader("Referer"));
	}

	@RequestMapping("clear")
	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clear();
		response.sendRedirect(request.getHeader("Referer"));
	}

}
