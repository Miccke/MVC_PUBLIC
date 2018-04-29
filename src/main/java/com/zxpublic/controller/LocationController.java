package com.zxpublic.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zxpublic.util.HttpXmlClient;


@Controller  
@RequestMapping("/localtion")

public class LocationController {
	 @RequestMapping("/signature")    
	    public void getIndex(HttpServletRequest request,PrintWriter out){  

	       // ModelAndView mav = new ModelAndView("index");
	        JSONObject object = new  JSONObject();
	      //获取access_token
			Map<String, String> params = new HashMap<String, String>();
			String xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx32c7f58bed37cf1f&secret=52e4def5eb3b8d36defe10af7dad4398");
		    System.out.println(xml);

			JSONObject jsonMap  = JSONObject.fromObject(xml);
			Map<String, String> map = new HashMap<String, String>();
		    Iterator<String> it = jsonMap.keys();  
		    while(it.hasNext()) {  
		        String key = (String) it.next();  
		        String u = jsonMap.get(key).toString();
		        map.put(key, u);  
		    }
		    String access_token = map.get("access_token");
		    System.out.println("access_token=" + access_token);
		    
		    //获取ticket
		    params.put("access_token",access_token);
		    params.put("type","jsapi");
		  //  xml = HttpXmlClient.post("https://api.weixin.qq.com/cgi-bin/ticket/getticket",params); 
		    xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi"); 
		    jsonMap  = JSONObject.fromObject(xml);
			map = new HashMap<String, String>();
		    it = jsonMap.keys();  
		    while(it.hasNext()) {  
		        String key = (String) it.next();  
		        String u = jsonMap.get(key).toString();
		        map.put(key, u);  
		    }
		    String jsapi_ticket = map.get("ticket");
		    
		    
		    //获取签名signature
		    String noncestr = UUID.randomUUID().toString();
		    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		    //获取请求url
		    String path = request.getContextPath();
		    //以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
//	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/public/index.jsp";  
	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/public/clientPage/storelist.jsp";  
		    String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + noncestr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
		    //sha1加密
		    String signature = HttpXmlClient.SHA1(str);
		    
		    object.put("signature", signature);   
		    object.put("timestamp", timestamp);   
		    object.put("nonceStr", noncestr);   
		    object.put("appId", "wx32c7f58bed37cf1f");  
	        System.out.println("jsapi_ticket=" + jsapi_ticket);
	        System.out.println("nonceStr=" + noncestr);
	        System.out.println("timestamp=" + timestamp);
	        System.out.println("url=" + url);
	        System.out.println("str=" + str);
	        System.out.println("signature=" + signature);
	        
	        Gson gson = new Gson();
	        out.println(gson.toJson(object));
			out.flush();
			out.close();
	    }    
}  
