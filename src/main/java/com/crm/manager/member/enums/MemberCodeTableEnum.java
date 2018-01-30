package com.crm.manager.member.enums;

public enum MemberCodeTableEnum {
	T_A_MEMBER_CODE("A", "t_a_member_code","融侨宝"),
	T_B_MEMBER_CODE("B", "t_b_member_code","金管家"),
	T_C_MEMBER_CODE("C", "t_c_member_code","融侨普惠"),;
	
	private String type;
	private String name;
	private String desc;
	
	private MemberCodeTableEnum(String type, String name, String desc) {
		this.type = type;
		this.name = name;
		this.desc = desc;
	}
	
	public static MemberCodeTableEnum getMemberTableCodeEnumByType(String type) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static MemberCodeTableEnum getMemberTableCodeEnumByName(String name) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
	        if (mte.getName().equals(name)) {  
	            return mte;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getName(String type) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.name;  
	        }  
	    }  
	    return null;  
	} 
	
	public static String getDescByType(String type) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
	        if (mte.getType().equals(type)) {  
	            return mte.desc;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getDescByName(String name) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
	        if (mte.getName().equals(name)) {  
	            return mte.desc;  
	        }  
	    }  
	    return null;  
	}
	
	public static String getType(String name) {  
	    for (MemberCodeTableEnum mte : MemberCodeTableEnum.values()) {  
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
