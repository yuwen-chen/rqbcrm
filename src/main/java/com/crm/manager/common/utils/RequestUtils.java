package com.crm.manager.common.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.crm.manager.common.JsonResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestUtils { 
	
	/**
     * 判断是否为Ajav请求
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request){
        if (!"XMLHttpRequest" .equalsIgnoreCase(request.getHeader("X-Requested-With")))
            return false;
        return true;
    }
    
    public static HttpServletRequest getHttpServletRequest(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    /**
     * post请求
     * @return
     */
    public static  boolean isPost() {
        HttpServletRequest request = getHttpServletRequest();
        String requersMethod = request.getMethod();
        if (requersMethod.equals("POST") || "POST".equals(requersMethod)) {
            return true;
        }
        return false;
    }

    /**
     * get请求
     *
     * @return
     */
    public static  boolean isGet() {
        HttpServletRequest request = getHttpServletRequest();
        String requersMethod = request.getMethod();
        if (requersMethod.equals("GET") || "GET".equals(requersMethod)) {
            return true;
        }
        return false;
    }

    /**
     * 获取请求域名，域名不包括http请求协议头
     *
     * @return 返回域名地址
     *
     */
    public static  String getDomain() {
        HttpServletRequest request = getHttpServletRequest();
        String path =  request.getContextPath();
        String domain = request.getServerName();
        if (request.getServerPort() == 80) {
            domain += path;
        } else {
            domain += ":" + request.getServerPort() + path;
        }
        return domain;
    }

    /**
     * 读取服务器主机ip信息
     *
     * @return 返回主机IP，异常将会获取不到ip
     */
    public static  String getHostIp() {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
            return addr.getHostAddress().toString();// 获得本机IP
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * 获取请求客户端ip
     *
     * @param request
     *
     * @return ip地址
     *
     */
    public static  String getRemoteAddress(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

	/**
	 * 是否为form提交
	 * @param request
	 * @return
	 */
	public static boolean isMultipart(HttpServletRequest request) {
		return (request != null && ServletFileUpload.isMultipartContent(request));
	}
	
	/**
	 * 写入异常
	 * @param response
	 * @param code
	 * @param message
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void writeExcepetion(HttpServletResponse response, int code, String message) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(new ObjectMapper().writeValueAsString(JsonResult.failure(code, message)));
		pw.flush();
		pw.close();
	}
	
	public static void renderErrorJson(HttpServletResponse response,String msg)throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.print(new ObjectMapper().writeValueAsString(JsonResult.failure(msg)));
		pw.flush();
		pw.close();
	}
	
	/**    
     * 描述:获取 post 请求内容 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException     
     */  
    public static String getRequestPostStr(HttpServletRequest request)  
            throws IOException {  
        byte buffer[] = getRequestPostBytes(request);  
        String charEncoding = request.getCharacterEncoding();  
        if (charEncoding == null) {  
            charEncoding = "UTF-8";  
        }  
        return new String(buffer, charEncoding);  
    }
    
    /**    
     * 描述:获取 post 请求的 byte[] 数组 
     * <pre> 
     * 举例： 
     * </pre> 
     * @param request 
     * @return 
     * @throws IOException     
     */  
    public static byte[] getRequestPostBytes(HttpServletRequest request)  
            throws IOException {  
        int contentLength = request.getContentLength();  
        if(contentLength<0){  
            return null;  
        }  
        byte buffer[] = new byte[contentLength];  
        for (int i = 0; i < contentLength;) {  
  
            int readlen = request.getInputStream().read(buffer, i,  
                    contentLength - i);  
            if (readlen == -1) {  
                break;  
            }  
            i += readlen;  
        }  
        return buffer;  
    } 
    
    /**
	 * 将新值重新set到request里面
	 * @author chen
	 *
	 */
	public static class HttpPutFormContentRequestWrapper extends HttpServletRequestWrapper {

		private MultiValueMap<String, String> formParameters;

		public HttpPutFormContentRequestWrapper(HttpServletRequest request, MultiValueMap<String, String> parameters) {
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
	}
}  
