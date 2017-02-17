package com.gaoyang.bean;

import org.apache.commons.lang3.StringUtils;

/** 
* @ClassName: Response 
* @Desc:返回值
* @author:
* @date 2015年5月29日 下午3:54:01  
*/ 
public class LocalResponse<T> {
	private String resCode;
	private String resStatus; 
	private T data ;
	private String msg;
	private String extram;
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResStatus() {
		return resStatus;
	}
	public void setResStatus(String resStatus) {
		this.resStatus = resStatus;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public String getExtram() {
		return extram;
	}
	public void setExtram(String extram) {
		this.extram = extram;
	}
	
	@Override
	public String toString() {
		return "LocalResponse [resCode=" + resCode + ", resStatus=" + resStatus
				+ ", data=" + data + ", msg=" + msg + "]";
	}
	
	private static String formatMsg(String str){
		
		if(StringUtils.isEmpty(str)){
			return "系统错误";
		}
		
		if(str.contains("Exception")){
			return "系统错误";
		}
		
		if(str.contains("Throwable")){
			return "系统错误";
		}
		
		String result = str;
		return result;
	}
}
