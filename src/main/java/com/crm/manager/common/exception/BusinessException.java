package com.crm.manager.common.exception;

/**
 * 业务异常
 * @author chen
 * @create 2018-02-06
 **/
public class BusinessException extends RuntimeException{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5080322628167524660L;
	
	//成功code
	public static final int CODE_SUCCESS = 200;
	//失败code
	public static final int CODE_FAILURED = 500;
	
	private int code; // 异常对应的返回码
	private String message; // 异常对应的描述信息
	
	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super(message);
		this.message = message;
	}
	
	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BusinessException(String message, Exception ex) {
		super(message, ex);
	}
	
	public BusinessException(int code, String message, Exception ex) {
		super(message, ex);
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
