package com.crm.manager.common.enums;

public enum OrderStatusEnum {

	ORDER_STATUS_CONFIRM("apply_ma_to_rf_confirm","购买理财成功"),
	ORDER_STATUS_NO_CONFIRM("apply_ma_to_rf_no_confirm","购买理财失败");
	
	private String code;
	private String value;
	
	private OrderStatusEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public OrderStatusEnum getOrderTypeEnumByCode(String code){
		for (OrderStatusEnum ote : OrderStatusEnum.values()) {  
	        if (ote.getCode() == code) {  
	            return ote;  
	        }  
	    }  
	    return null; 
	}
	
	public String getValue(String code){
		for (OrderStatusEnum ote : OrderStatusEnum.values()) {  
	        if (ote.getCode() == code) {  
	            return ote.value;  
	        }  
	    }  
	    return null; 
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
