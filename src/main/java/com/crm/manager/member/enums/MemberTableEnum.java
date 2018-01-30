package com.crm.manager.member.enums;

public enum MemberTableEnum {
	T_A_MEMBER("A", "t_a_member","融侨宝"),
	T_B_MEMBER("B", "t_b_member","金管家"),
	T_C_MEMBER("C", "t_c_member","融侨普惠"),;
	
	private String type;
	private String name;
	private String desc;
	
	private MemberTableEnum(String type, String name, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
	}
	
	public static MemberTableEnum getMemberTableEnumByType(String type) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static MemberTableEnum getMemberTableEnumByName(String name) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getName().equals(name)) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getName(String type) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.name;  
	        }  
	    }  
	    return null;  
	} 
	
	public static String getDescByType(String type) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.desc;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getDescByName(String name) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getName().equals(name)) {  
	            return mte.desc;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getType(String name) {  
	    for (MemberTableEnum mte : MemberTableEnum.values()) {  
	        if (mte.getName().equals(name)) {  
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}