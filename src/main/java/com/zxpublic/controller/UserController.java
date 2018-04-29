package com.zxpublic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.UserService;
import com.zxpublic.vo.User;
  
/** 
 * ���ܸ�Ҫ��UserController 
 * 
 */  
@Controller  
@RequestMapping("/user")
public class UserController {  
	
	Log logger= LogFactory.getLog(UserController.class);
	
    @Resource  
    private UserService userService;  
      
    /**
	 * ��ѯȫ��
	 * @param model
	 * @return
	 */
//	@SuppressWarnings("rawtypes")
//	@RequestMapping("person/listAll.action")
//	public  String listAll(Model model){
//		logger.debug("123");
//		List personList = userService.listAll();
//		model.addAttribute("personList", personList);
//		
//		return "/person/jPersonList";
//	}
	/**
	 * ��Ӽ�¼
	 * @param model
	 * @return
	 */
//	@RequestMapping("person/addRecord.action")
//	public  String addRecord(Model model){
//		logger.debug("1233");
//		User p=new User();
//		p.setUserName("123");
//		p.setUserPassword("123456");
//		p.setUserEmail("123@123.123");
//		int re = userService.addRecord(p);
//		System.out.println(re);
//		model.addAttribute("message", "you can you up!");
//		
//		return "/person/addResult";
//	}
	
	/**
	 * ��ӻ����
	 * @param model
	 * @return
	 */
//	@RequestMapping("person/saveOrUpdate.action")
//	public  String saveOrUpdate(Model model){
//		logger.debug("1233");
//		User p=new User();
//		p.setUserName("123");
//		p.setUserPassword("123456");
//		p.setUserEmail("123@123.123");
//		int re = userService.saveOrUpdate(p);
//		System.out.println(re);
//		model.addAttribute("message", "you can you up!");
//		
//		return "/person/saveOrUpdte";
//	}
	
	/**
	 * �鿴������Ϣ����
	 * @param id
	 * @return
	 */
//	@RequestMapping(value="person/getDetail")
//	public String details(Model model,@RequestParam("id") String id){
//		User user= userService.getDetail(id);
//		model.addAttribute("user",user);
//		return "/person/personDetail";
//	}
    
	/**
	 * ����ID��ѯ������Ϣ
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping("/getUserMsg")
	public void querySWaiterMsg(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isNullOrEmpty(id)){
				User user = userService.get(Long.parseLong(id));
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user", user);
				
				Gson gson = new Gson();
				PrintWriter pw = response.getWriter();
				pw.write(gson.toJson(map));
				pw.flush();
				pw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	
	/**
	 *	�����û���Ϣ 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping("/updateUserMsg")
	public void updateUserMsg(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "dataObject", required = true)String dataObject) throws IOException{
		StringBuffer result = new StringBuffer();
		try {
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String, String> map = gson.fromJson(dataObject, Map.class);
			System.out.println(map.toString());
			User user = new User();
			if(!StringUtils.isNullOrEmpty(map.get("uid"))){
				user.setUid(Integer.parseInt(map.get("uid")));
				if(!StringUtils.isNullOrEmpty(map.get("imageUrl"))){
					user.setImageUrl(map.get("imageUrl"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("nickName"))){
					user.setNickName(map.get("nickName"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("uAge"))){
					user.setuAge(map.get("uAge"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("uEMail"))){
					user.setuEMail(map.get("uEMail"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("mobilphone"))){
					user.setMobilphone(map.get("mobilphone"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("telephone"))){
					user.setTelephone(map.get("telephone"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("longitudex"))){
					user.setLongitudex(map.get("longitudex"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("latitudey"))){
					user.setLatitudey(map.get("latitudey"));
				}
				if(!StringUtils.isNullOrEmpty(map.get("address"))){
					user.setAddress(map.get("address"));
				}
				userService.updateMsg(user);
				result.append("���³ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.append("�����쳣��");
		}
		PrintWriter pw = response.getWriter();
		pw.write(result.toString());
		pw.flush();
		pw.close();
	}
}  
