package com.drt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drt.entity.Admin;
import com.drt.entity.Goods;
import com.drt.entity.ResultEntity;
import com.drt.service.api.GoodsService;
import com.drt.util.CampusConstant;
import com.drt.util.GoodsCategory;
import com.github.pagehelper.PageInfo;

import com.drt.entity.Page;

@Controller
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 查询商品数据
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param keyword
	 * @param model
	 * @return
	 */
	@RequestMapping("query/for/search")
	public String queryForSearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

		PageInfo<Goods> pageInfo = goodsService.queryForKeywordSearch(pageNum, pageSize, keyword);
//		List<Goods> list = pageInfo.getList();
//		for (Goods goods : list) {
//			System.out.println(goods.toString()+"\t\t\t");
//		}

		model.addAttribute(CampusConstant.ATTR_NAME_PAGE_INFO, pageInfo);

		return "goods-page";
	}

	/**
	 * 批量删除
	 * 
	 * @param adminIdList
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batch/remove")
	public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList) {

		try {
			goodsService.batchRemove(adminIdList);
			return ResultEntity.successWithoutData();
		} catch (Exception e) {
			return ResultEntity.failed(null, e.getMessage());
		}

	}

	@RequestMapping("save")
	public String doSave(Goods goods) {

		try {
			goodsService.saveGoods(goods);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}

		return "redirect:/goods/query/for/search.html";
	}

	@RequestMapping("to/edit/page")
	public String toEdit(@RequestParam("goodsId") Integer goodsId, @RequestParam("pageNum") Integer pageNum,
			Model model) {
		Goods goods = goodsService.getGoodsById(goodsId);
		model.addAttribute("goods", goods);
		model.addAttribute("pageNum", pageNum);
		return "goods-edit";
	}

	@RequestMapping("update")
	public String doUpdate(Goods goods, @RequestParam("pageNum") Integer pageNum,
			@RequestParam("goodsID") Integer goodsID) {
		try {
//			System.out.println(goodsID);
			goodsService.update(goods, goodsID);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return "redirect:/goods/query/for/search.html?pageNum=" + pageNum;

	}

	@RequestMapping("goods-get")
	public String pageByPrice(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "8") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

		PageInfo<Goods> pageInfo = goodsService.queryForKeywordSearch(pageNum, pageSize, keyword);

		model.addAttribute(CampusConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "index-page";

	}

}
