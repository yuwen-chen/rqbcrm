package com.crm.manager.member.enums;

public enum MemberTableEnum {
	T_A_MEMBER("A", "t_a_member"),
	T_B_MEMBER("B", "t_b_member"),
	T_C_MEMBER("C", "t_c_member"),;
	
	private String type;
	private String name;
	
	private MemberTableEnum(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public static MemberTableEnum getMemberTableEnumByType(String type) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getType() == type) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static MemberTableEnum getMemberTableEnumByName(String name) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getName() == name) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getName(String type) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getType() == type) {  
	            return mte.name;  
	        }  
	    }  
	    return null;  
	} 
	
	public static String getType(String name) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getName() == name) {  
	            return mte.type;  
	        }  
	    }  
	    return null;  
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
