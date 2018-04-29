package com.zxpublic.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zxpublic.service.UserAddressService;
import com.zxpublic.vo.UserAddress;
  
/** 
 * 功能概要：UserAddressController 
 * 
 */  
@Controller  
@RequestMapping("/userAddress")
public class UserAddressController {
	@Autowired
	private UserAddressService userAddressService;
	
	@RequestMapping("/insertAddress")
	public void insert(HttpServletRequest req,PrintWriter out){

		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String address = req.getParameter("address");
		String openid = req.getParameter("openid");
		String addId = "";
		if(req.getParameter("addId").equals("") == false){
			addId = req.getParameter("addId");
			
			UserAddress userAddress = new UserAddress();
			userAddress.setAddId(Long.parseLong(addId));
			userAddress.setOpenId(openid);
			userAddress.setUserAddress(address);
			userAddress.setUserName(name);
			userAddress.setUserPhone(Long.parseLong(phone));
			
			try {
				userAddressService.update(userAddress);
				out.print(addId);
			} catch (Exception e) {
				out.print(0);
				e.printStackTrace();
			}
		}else{
			System.out.println(name+phone+address+openid+addId);
			UserAddress userAddress = new  UserAddress();
			userAddress.setUserName(name);
			userAddress.setUserPhone(Long.parseLong(phone));
			userAddress.setUserAddress(address);
			userAddress.setOpenId(openid);
			try {
				userAddressService.insert(userAddress);
				out.print(userAddress.getAddId());
			} catch (Exception e) {
				out.print(0);
				e.printStackTrace();
			}
		}
		
		out.flush();
		out.close();
		
//		return "../public/clientPage/submitOrder";
	}
	/**
	 * 用户提交订单地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:26:55
	 * @param req
	 * @param out
	 */
	@RequestMapping("/getAddress")
	public void getAddress(HttpServletRequest req,PrintWriter out){
		String openId = req.getParameter("openId");
		String addId = req.getParameter("addId");
		System.out.println(openId+addId);
		UserAddress userAddress = new UserAddress();
		if(req.getParameter("addId").equals("null")){
			userAddress.setAddId(null);
		}else{
			userAddress.setAddId(Long.parseLong(addId));
		}	
		userAddress.setOpenId(openId);
		UserAddress ua = userAddressService.getUserAddress(userAddress);
		Gson gson = new Gson();
		out.print(gson.toJson(ua));
		out.flush();
		out.close();
		
	}
	/**
	 * 用户所有地址
	 * @author Miccke  DateTime 2017年9月1日 上午9:27:24
	 * @param req
	 * @param out
	 */
	@RequestMapping("/listAll")
	public void listAll(HttpServletRequest req,PrintWriter out){
		String openId = req.getParameter("openid");
		System.out.println(openId);	
		List<UserAddress> userAddress = userAddressService.listAll(openId);
		Gson gson = new Gson();
		out.print(gson.toJson(userAddress));
		out.flush();
		out.close();	
	}
}  
