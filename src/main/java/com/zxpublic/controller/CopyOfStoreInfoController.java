package com.zxpublic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.zxpublic.service.StoreInfoService;
import com.zxpublic.service.UserService;
import com.zxpublic.util.HttpXmlClient;
import com.zxpublic.util.ToolUtils;
import com.zxpublic.vo.StoreInfo;
import com.zxpublic.vo.User;


@Controller
@RequestMapping("/WstoreInfo")
public class CopyOfStoreInfoController {
	@Autowired
	private StoreInfoService is;
	@Autowired
	private UserService userService;
	private Logger log = Logger.getAnonymousLogger();

	
	@RequestMapping("/storlist")
	public void getListAct(PrintWriter out,HttpServletRequest request) throws IOException {
		Double lat1 =  Double.parseDouble(request.getParameter("latitude"));
		Double lng1 =  Double.parseDouble(request.getParameter("longitude"));;
		List<StoreInfo> silist = is.listAll();
		List<StoreInfo> storeInfoList = new ArrayList<StoreInfo>();
		for(StoreInfo si:silist){
			System.out.println(si);
			Double lat = Double.parseDouble(si.getLatitudey());
			Double lng = Double.parseDouble(si.getLongitudex());		
		/*	InputStream in = this.getClass().getResourceAsStream("jdbc.properties");
			Properties p = new Properties();
			p.load(in);*/
			
					
			si.setDistance(Math.floor(ToolUtils.getDistance(lat, lng,lat1, lng1)));
			storeInfoList.add(si);
		}
		Gson gson = new Gson();
		out.println(gson.toJson(storeInfoList));
		out.flush();
		out.close();
	}
	

	@RequestMapping("/singleStore")
	public void getListAct(PrintWriter out,Integer storeId) {
		StoreInfo si = is.selectByStoreId(storeId);
		Gson gson = new Gson();
		out.println(gson.toJson(si));
		out.flush();
		out.close();
	}
	

	 @RequestMapping("/signature")    
	 public ModelAndView getIndex(HttpServletRequest request){  

	        ModelAndView mav = new ModelAndView("index");  
			//��ȡaccess_token
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
		    
		    //��ȡticket
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
		    
		    
		    //��ȡǩ��signature
		    String noncestr = UUID.randomUUID().toString();
		    String timestamp = Long.toString(System.currentTimeMillis() / 1000);
		    //��ȡ����url
		    String path = request.getContextPath();
		    //��Ϊ�����õĲ˵���http://yo.bbdfun.com/first_maven_project/���������"/"�ģ�����urlҲ������"/"
	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/";  
		    String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + noncestr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
		    //sha1����
		    String signature = HttpXmlClient.SHA1(str);
	        mav.addObject("signature", signature);   
	        mav.addObject("timestamp", timestamp);   
	        mav.addObject("noncestr", noncestr);   
	        mav.addObject("appId", "wx7099477f2de8aded"); 
	        System.out.println("jsapi_ticket=" + jsapi_ticket);
	        System.out.println("noncestr=" + noncestr);
	        System.out.println("timestamp=" + timestamp);
	        System.out.println("url=" + url);
	        System.out.println("str=" + str);
	        System.out.println("signature=" + signature);
	        return mav;    
	        
	    }    

	@RequestMapping("/insertStore")
	public void insertStore(@RequestParam(value = "dataObject", required = true) String dataObject,PrintWriter out) throws Exception {
		User user = new User();
		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(dataObject, Map.class);
		DesUtils des = new DesUtils("leemenz");
		
		user.setLoginName(map.get("loginName"));
		user.setPassWord(des.encrypt("123456"));
		user.setNickName(map.get("storeName"));
		user.setuAge(map.get("uAge"));
		user.setuSex(map.get("uSex"));
//		user.setuEMail(map.get("uEmail"));
		user.setuType(Integer.parseInt(map.get("uType")));
		user.setImageUrl(map.get("storeUrl"));
		user.setMobilphone(map.get("mobilphone"));
		user.setTelephone(map.get("telephone"));
		user.setAddress(map.get("address"));
		user.setLongitudex(map.get("longitudex"));
		user.setLatitudey(map.get("latitudey"));
		
		userService.insertUser(user);
		
		StoreInfo storeInfo = new StoreInfo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		
		storeInfo.setStoreName(map.get("storeName"));
		storeInfo.setStoreUrl(map.get("storeUrl"));
		storeInfo.setStorePhone(Long.parseLong(map.get("mobilphone")));
		storeInfo.setStoreAddress(map.get("address"));
		storeInfo.setReviewStar(5);
		storeInfo.setSalesVolume(0);
		storeInfo.setMinimum(Integer.parseInt(map.get("minimum")));
		storeInfo.setDeliveryCost(Integer.parseInt(map.get("deliveryCost")));
		storeInfo.setLegalPerson(map.get("nickName"));
		storeInfo.setUid(user.getUid());
		storeInfo.setDistance(Double.parseDouble("0"));
		storeInfo.setLongitudex(map.get("longitudex"));
		storeInfo.setLatitudey(map.get("latitudey"));	
		storeInfo.setOpeningStart((Date) dateFormat.parse(map.get("openingStart")));
		storeInfo.setOpeningEnd((Date) dateFormat.parse(map.get("openingEnd")));
		storeInfo.setCodeNum(1);
		
		is.insertStore(storeInfo);
	}
	
	/**
	 * ɾ���̼ң��û���
	 * @author Miccke  DateTime 2017��8��25�� ����4:35:05
	 * @param out
	 * @param dishId
	 */
	@RequestMapping("/deleteStore")
	public void deleteDish(PrintWriter out,Integer storeId) {
		System.out.println(storeId);
		Gson gson = new Gson();
		out.println(gson.toJson(is.deleteStore(storeId)));
		out.flush();
		out.close();
	}
}
