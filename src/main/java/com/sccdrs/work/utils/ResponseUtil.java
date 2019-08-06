package com.sccdrs.work.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.xml.ws.Response;

/**
 * @author wcy
 * @date 2019/8/6 10:31
 * @Description:
 */
public class ResponseUtil {
    private ResponseUtil(){
    }

    public static JSONObject success(String message){
        JSONObject json = new JSONObject();
        json.put("code","0");
        json.put("message",message);
        return json;
    }

    public static JSONObject fail(String message){
        JSONObject json = new JSONObject();
        json.put("code","1");
        json.put("message",message);
        return json;
    }


}
