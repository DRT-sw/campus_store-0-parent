package com.drt.entity;

/**
 * 统一整个项目中所有Ajax请求的响应格式，作为项目的一个开发规范
 * @author Lenovo
 *
 */
public class ResultEntity<T> {
	
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String NO_MESSAGE = "NO_MESSAGE";
	public static final String NO_DATA = "NO_DATA";
	
	// 方便返回成功结果（不携带查询结果情况）
	public static ResultEntity<String> successWithoutData() {
		return new ResultEntity<String>(SUCCESS, NO_MESSAGE, NO_DATA);
	}
	
	// 方便返回成功结果（携带查询结果情况）
	public static <E> ResultEntity<E> successWithData(E data) {
		return new ResultEntity<E>(SUCCESS, NO_MESSAGE, data);
	}
	
	// 方便返回失败结果
	public static <E> ResultEntity<E> failed(E data, String message) {
		return new ResultEntity<E>(FAILED, message, data);
	}
	
	private String result;
	private String message;
	private T data;
	
	public ResultEntity() {
		
	}

	public ResultEntity(String result, String message, T data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultEntity [result=" + result + ", message=" + message + ", data=" + data + "]";
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
