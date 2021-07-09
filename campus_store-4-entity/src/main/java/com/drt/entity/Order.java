package com.drt.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
	private String orderId;

	private Date createTime;

	private BigDecimal totalMoney;

	// 0-未发货、1-等待用户签收、2-用户已签收
	private Integer status;

	private Integer userId;

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", createTime=" + createTime + ", totalMoney=" + totalMoney + ", status="
				+ status + ", userId=" + userId + "]";
	}

	public Order() {
		super();
	}

	public Order(String orderId, Date createTime, BigDecimal totalMoney, Integer status, Integer userId) {
		super();
		this.orderId = orderId;
		this.createTime = createTime;
		this.totalMoney = totalMoney;
		this.status = status;
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}