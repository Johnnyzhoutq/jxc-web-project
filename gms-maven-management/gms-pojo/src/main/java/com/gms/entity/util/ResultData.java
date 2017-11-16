package com.gms.entity.util;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
	/**
	 * @author zhoutianqi
	 * @className ResultDate.java
	 * @date 2017年11月15日 上午10:40:32
	 * @description Resful 请求应答统一模板
	 */
    private final int code; //状态码
	private final String message;  //状态信息
    private final Map<String, Object> data = new HashMap<>();  
  
    public String getMessage() {  
        return message;  
    }  
  
    public int getCode() {  
        return code;  
    }  
  
    public Map<String, Object> getData() {  
        return data;  
    }  
      
    public ResultData putDataValue(String key, Object value) {  
        data.put(key, value);  
        return this;  
    }  
      
    private ResultData(int code, String message) {  
        this.code = code;  
        this.message = message;  
    }  
      
    public static ResultData ok() {  
        return new ResultData(200, "Ok");  
    }  
      
    public static ResultData notFound() {  
        return new ResultData(404, "Not Found");  
    }  
      
    public static ResultData badRequest() {  
        return new ResultData(400, "Bad Request");  
    }  
      
    public static ResultData forbidden() {  
        return new ResultData(403, "Forbidden");  
    }  
      
    public static ResultData unauthorized() {  
        return new ResultData(401, "Unauthorized");  
    }  
      
    public static ResultData serverInternalError() {  
        return new ResultData(500, "Server Internal Error");  
    }  
      
    public static ResultData customerError() {  
        return new ResultData(1001, "Customer Error");  
    }  
}
