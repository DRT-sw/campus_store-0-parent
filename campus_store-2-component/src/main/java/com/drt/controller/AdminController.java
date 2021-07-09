package com.drt.controller;

import java.util.List;

import javax.management.RuntimeErrorException;
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
import com.drt.service.api.AdminService;
import com.drt.util.CampusConstant;
import com.drt.util.CampusUtils;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	/**
	 * 获取所有用户
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("getAll")
	public String getAll(Model model) {
		List<Admin> adminList = adminService.getAll();
		for (Admin admin : adminList) {
			System.out.println(admin);
		}
		model.addAttribute("adminList", adminList);
		return "admin-target";
	}

	/**
	 * 登陆操作
	 * 
	 * @param loginAcct
	 * @param userPswd
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("login")
	public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd,
			Model model, HttpSession session) {

		if (CampusUtils.stringEffective(loginAcct) && CampusUtils.stringEffective(userPswd)) {
			Admin admin = adminService.login(loginAcct, userPswd);
			if (admin != null) {
				session.setAttribute(CampusConstant.ATTR_NAME_LOGIN_ADMIN, admin);
				return "redirect:/admin/to/main/page.html";
			}
		}

		model.addAttribute(CampusConstant.ATTR_NAME_MESSAGE, CampusConstant.MESSAGE_LOGIN_FAILED);
		return "admin-login";

	}


	@RequestMapping("logout")
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/index.html";
	}

	@RequestMapping("query/for/search")
	public String queryForSearch(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {

		PageInfo<Admin> pageInfo = adminService.queryForKeywordSearch(pageNum, pageSize, keyword);

		model.addAttribute(CampusConstant.ATTR_NAME_PAGE_INFO, pageInfo);

		return "admin-page";
	}

	// 将当前handler方法的返回值作为响应体返回，不经过视图解析器
	@ResponseBody
	@RequestMapping("batch/remove")
	public ResultEntity<String> batchRemove(@RequestBody List<Integer> adminIdList) {

		try {

			adminService.batchRemove(adminIdList);

			return ResultEntity.successWithoutData();
		} catch (Exception e) {

			return ResultEntity.failed(null, e.getMessage());
		}

	}

	@RequestMapping("save")
	public String doSave(Admin admin) {

		try {
			adminService.saveAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}

		return "redirect:/admin/query/for/search.html";
	}

	@RequestMapping("to/edit/page")
	public String toEdit(@RequestParam("adminId") Integer adminId, Model model) {
		Admin admin = adminService.getAdminById(adminId);
		model.addAttribute("admin", admin);
		return "admin-edit";
	}

	@RequestMapping("update")
	public String doUpdate(Admin admin, @RequestParam("pageNum") Integer pageNum) {
		try {
			adminService.updateAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				throw new RuntimeException(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
		}
		return "redirect:/admin/query/for/search.html?pageNum=" + pageNum;

	}


	@RequestMapping("reg")
	public String doReg(Admin admin, @RequestParam(value = "SecondPswd") String SecondPswd,Model model) {
//		System.out.println(admin.toString());
		
		try {
			adminService.saveAdmin(admin);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DuplicateKeyException) {
				model.addAttribute("msg", "用户注册失败");
				model.addAttribute("admin", admin);
				System.err.println(CampusConstant.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
			}
			return "admin-reg";
			
		}
		
		return "reg-success";

	}

}
