package com.zxpublic.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zxpublic.service.DishInfoService;
import com.zxpublic.vo.DishInfo;


@Controller
@RequestMapping("/dishInfo")
public class DishInfoController {
	@Autowired
	private DishInfoService dishInfoService;
	
	@RequestMapping("/dishlist")
	public void getListALL(PrintWriter out,HttpServletRequest request) {
		Integer storeId = Integer.parseInt(request.getParameter("store_id"));
		Integer dishtypeId = Integer.parseInt(request.getParameter("dishtypeId"));
		List<DishInfo> di = dishInfoService.selectByStoreId(storeId,dishtypeId);
		Gson gson = new Gson();
		out.println(gson.toJson(di));
		out.flush();
		out.close();
	}
	@RequestMapping("/singledish")
	public void getListAct(PrintWriter out,Integer dishId) {
		DishInfo di = dishInfoService.selectByPrimaryKey(dishId);
		Gson gson = new Gson();
		out.println(gson.toJson(di));
		out.flush();
		out.close();
	}
	@RequestMapping("/getAll")
	public void getAll(PrintWriter out,HttpServletRequest req,ModelMap mp, HttpServletResponse res) throws Exception{
	
		String currentPage = req.getParameter("page");
		String pageSize = req.getParameter("rows");
		String storeName = req.getParameter("storeName") == null ? ""	: req.getParameter("storeName");
		String dishName = req.getParameter("dishName") == null ? ""	: req.getParameter("dishName");
		res.setContentType("text/html;charset=UTF-8");
		
		res.setCharacterEncoding("UTF-8");
		DishInfo dish = new DishInfo();
		dish.setCurrentPage(Integer.parseInt(currentPage));
		dish.setPageSize(Integer.parseInt(pageSize));
		dish.setStoreName(storeName);
		dish.setDishName(dishName);
		
		List<DishInfo> dishList = dishInfoService.getAll(dish);
		int total = dishInfoService.countDish(dish);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", dishList);

		Gson gson = new Gson();
		PrintWriter pw = res.getWriter();
		pw.write(gson.toJson(map)); 
		pw.flush();
		pw.close();
	
	}
	
	@RequestMapping("/insertDish")
	public void insertDish(PrintWriter out, HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
			String dishUrl = req.getParameter("dishUrl");
			String dishName = req.getParameter("dishName");
			String dishSpecification = req.getParameter("dishSpecification");
			String dishPrice = req.getParameter("dishPrice");
			Integer dishTypeId = Integer.parseInt(req.getParameter("dishTypeId"));
			Integer storeId = Integer.parseInt(req.getParameter("storeId"));
			DishInfo dishInfo = new DishInfo();

			dishInfo.setDishtypeId(dishTypeId);
			dishInfo.setStoreId(storeId);
			dishInfo.setDishName(dishName);
			dishInfo.setDishSpecification(dishSpecification);
			dishInfo.setDishMonthsales(0);
			dishInfo.setDishPrice(Float.parseFloat(dishPrice));
			dishInfo.setDishStar(5);
			dishInfo.setDishUrl(dishUrl);
			dishInfo.setCodeNum(1);
			
			dishInfoService.insertDish(dishInfo);

			out.println(1);
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/deleteDish")
	public void deleteDish(PrintWriter out,Integer dishId) {
		Gson gson = new Gson();
		out.println(gson.toJson(dishInfoService.deleteDish(dishId)));
		out.flush();
		out.close();
	}
}
