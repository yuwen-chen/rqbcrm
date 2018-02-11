package com.crm.manager.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    /*private Map<String , String[]> params = new HashMap<String, String[]>();

    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
    }
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        this(request);
        addAllParameters(extendParams);//这里将扩展参数写入参数表
    }

    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    public String[] getParameterValues(String name) {//同上
         return params.get(name);
    }

   public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }

    public void addParameter(String name , Object value) {//增加参数
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }*/
	
	private MultiValueMap<String, String> formParameters;

	public ParameterRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
		super(request);
		this.formParameters = (parameters != null) ? parameters : new LinkedMultiValueMap<String, String>();
	}

	@Override
	public String getParameter(String name) {
		String queryStringValue = super.getParameter(name);
		String formValue = this.formParameters.getFirst(name);
		return (queryStringValue != null) ? queryStringValue : formValue;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> result = new LinkedHashMap<String, String[]>();
		Enumeration<String> names = this.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			result.put(name, this.getParameterValues(name));
		}
		return result;
	}

	@Override
	public Enumeration<String> getParameterNames() {
		Set<String> names = new LinkedHashSet<String>();
		names.addAll(Collections.list(super.getParameterNames()));
		names.addAll(this.formParameters.keySet());
		return Collections.enumeration(names);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] queryStringValues = super.getParameterValues(name);
		List<String> formValues = this.formParameters.get(name);
		if (formValues == null) {
			return queryStringValues;
		} else if (queryStringValues == null) {
			return formValues.toArray(new String[formValues.size()]);
		} else {
			List<String> result = new ArrayList<String>();
			result.addAll(Arrays.asList(queryStringValues));
			result.addAll(formValues);
			return result.toArray(new String[result.size()]);
		}
	}
	
	/*private Map<String , String[]> params = new HashMap<String, String[]>();
	 
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        this.params.putAll(request.getParameterMap());
    }
    //重载一个构造方法
    public ParameterRequestWrapper(HttpServletRequest request , Map<String , Object> extendParams) {
        this(request);
        addAllParameters(extendParams);//这里将扩展参数写入参数表
    }
 
    @Override
    public String getParameter(String name) {//重写getParameter，代表参数从当前类中的map获取
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }
 
    public String[] getParameterValues(String name) {//同上
         return params.get(name);
    }
 
   public void addAllParameters(Map<String , Object>otherParams) {//增加多个参数
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }
 
    public void addParameter(String name , Object value) {//增加参数
        if(value != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }*/
}