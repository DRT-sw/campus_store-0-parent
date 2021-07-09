package com.drt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drt.entity.Admin;
import com.drt.entity.ResultEntity;
import com.drt.entity.User;
import com.drt.service.api.UserService;
import com.drt.util.CampusConstant;
import com.drt.util.CampusUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("query/for/search")
	public String queryForSearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

		PageInfo<User> pageInfo = userService.queryForKeywordSearch(pageNum, pageSize, keyword);

		model.addAttribute(CampusConstant.ATTR_NAME_PAGE_INFO, pageInfo);

		return "user-page";
	}

	@ResponseBody
	@RequestMapping("batch/remove")
	public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList) {

		try {

			userService.batchRemove(adminIdList);

			return ResultEntity.successWithoutData();
		} catch (Exception e) {

			return ResultEntity.failed(null, e.getMessage());
		}

	}

	@RequestMapping("save")
	public String doSave(User user ) {

		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}

		return "redirect:/user/query/for/search.html";
	}
	
	@RequestMapping("to/edit/page")
	public String toEdit(@RequestParam("userId") Integer userId, Model model) {
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		return "user-edit";
	}
	
	@RequestMapping("update")
	public String doUpdate(User user, @RequestParam("pageNum") Integer pageNum) {
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return "redirect:/user/query/for/search.html?pageNum=" + pageNum;

	}
	
	
	@RequestMapping("dologin")
	public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd,
			Model model, HttpSession session) {

		if (CampusUtils.stringEffective(loginAcct) && CampusUtils.stringEffective(userPswd)) {
			User user = userService.login(loginAcct, userPswd);
			if (user != null) {
				session.setAttribute(CampusConstant.ATTR_NAME_LOGIN_ADMIN, user);
//				return "redirect:/goods/goods-get";
				return "user-success";
				
			}
		}

		model.addAttribute(CampusConstant.ATTR_NAME_MESSAGE, CampusConstant.MESSAGE_LOGIN_FAILED);
		return "user-login";

	}
	
	@RequestMapping("reg")
	public String doReg(User user, @RequestParam(value = "SecondPswd") String SecondPswd,Model model) {
		
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				model.addAttribute("msg", "用户注册失败");
				model.addAttribute("user", user);
				System.err.println(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
			return "user-reg";
			
		}
		
		return "reg-success";

	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/index.html";
	}
	

}
