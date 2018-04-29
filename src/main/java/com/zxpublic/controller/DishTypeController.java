package com.zxpublic.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.zxpublic.service.DishTypeService;
import com.zxpublic.vo.DishType;


@Controller
@RequestMapping("/dishType")
public class DishTypeController {
	@Autowired
	private DishTypeService dishTypeService;
	
	private Logger log = Logger.getAnonymousLogger();

	
	@RequestMapping("/menulist")
	public void getListAct(PrintWriter out,HttpServletRequest req) {
		
		Integer storeId = Integer.parseInt(req.getParameter("storeId"));
		List<DishType> activeannounced = dishTypeService.selectByStoreId(storeId);
		Gson gson = new Gson();
		out.println(gson.toJson(activeannounced));
		out.flush();
		out.close();
	}
	
	@RequestMapping("/getAll")
	public void getAll(PrintWriter out, Integer storeId) {
		List<DishType> dishType = dishTypeService.getAll();
		Gson gson = new Gson();
		out.println(gson.toJson(dishType));
		out.flush();
		out.close();
	}
	@RequestMapping("/getAllByPage")
	public void getAllByPage(HttpServletRequest request,HttpServletResponse response,ModelMap mp,PrintWriter out) throws Exception{
		DishType dishType = new  DishType();
		String currentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		dishType.setCurrentPage(Integer.parseInt(currentPage));
		dishType.setPageSize(Integer.parseInt(pageSize));
		List<DishType> dishTypeList = dishTypeService.getAllByPage(dishType);
		
		int total = dishTypeService.countDishType();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", dishTypeList);

		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		pw.write(gson.toJson(map));
		out.flush();
		out.close();
	}
	@RequestMapping("/insertDishType")
	public void insertDishType(PrintWriter out, HttpServletRequest req) {
		try {
			req.setCharacterEncoding("UTF-8");
			String dishtype = req.getParameter("dishtype");
			Integer storeId = Integer.parseInt(req.getParameter("storeId"));
			DishType dishType = new DishType();
			dishType.setDishtype(dishtype);
			dishType.setStoreId(storeId);
			dishType.setCodeNum(1);

			dishTypeService.insertDishType(dishType);

			out.println(1);
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 删除菜品类型
	 * @author Miccke  DateTime 2017年8月25日 下午4:28:25
	 * @param out
	 * @param dishId
	 */
	@RequestMapping("/deleteDishType")
	public void deleteDish(PrintWriter out,Integer dishtypeId) {
		Gson gson = new Gson();
		out.println(gson.toJson(dishTypeService.deleteDishType(dishtypeId)));
		out.flush();
		out.close();
	}

}
