package com.zxpublic.controller;

import io.goeasy.GoEasy;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.DishInfoService;
import com.zxpublic.service.OrderInfoService;
import com.zxpublic.service.SWaiterOrderInfoService;
import com.zxpublic.service.StoreInfoService;
import com.zxpublic.service.UserService;
import com.zxpublic.util.GetDateUtil;
import com.zxpublic.util.OrderNumUtil;
import com.zxpublic.util.ShortMessageUtil;
import com.zxpublic.util.UrlUtil;
import com.zxpublic.vo.DishInfo;
import com.zxpublic.vo.OrderInfo;
import com.zxpublic.vo.Page;
import com.zxpublic.vo.StoreInfo;
import com.zxpublic.vo.User;
 
/**
 * ������������� 
 * @author CW5128
 *
 */
@Controller  
@RequestMapping("/orderInfo")
public class OrderInfoController {  
	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private StoreInfoService storeInfoService;
	@Autowired
	private SWaiterOrderInfoService sWaiterOrderInfoService;
	@Autowired
	private DishInfoService dishInfoService;
	
	Log logger= LogFactory.getLog(OrderInfoController.class);
	//ÿҳ��������
	private static final Double pageSize = 4.0;
	
	@RequestMapping(value = "/test")
	public void test(PrintWriter out) throws IOException{
		OrderNumUtil orderNumUtil = OrderNumUtil.getInstance();
		String orderNum = orderNumUtil.generaterNextNumber(sWaiterOrderInfoService.selectMaxNum());
		System.out.println(orderNum);
		out.println(orderNum);
		out.flush();
		out.close();
	}
	
	
	@RequestMapping(value = "/insertOrder", method = RequestMethod.POST)
	@ResponseBody
	public void insertOrder(
			@RequestParam(value = "dataObject", required = true) String dataObject,
			PrintWriter out) {
		try {
			final OrderInfo of = new OrderInfo(); 
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String, String> map = gson.fromJson(dataObject, Map.class);
			OrderNumUtil orderNumUtil = OrderNumUtil.getInstance();
			String orderNum = "XY"+ orderNumUtil.generaterNextNumber(orderInfoService.selectMaxNum());//"XY" + System.currentTimeMillis();
			of.setOrderNum(orderNum);
			of.setAddress(map.get("address"));
			of.setAmount(Double.parseDouble(map.get("amount").toString()));
			of.setOrderstate(Integer.parseInt(map.get("orderstate").toString()));
			Integer uid = Integer.parseInt(map.get("uid").toString());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			of.setOrdertime((Date) dateFormat.parse(map.get("ordertime")));
			of.setPhonenum(Long.parseLong(map.get("phonenum").toString()));
			of.setRealname(map.get("realname"));
			of.setStoreId(Integer.parseInt(map.get("storeId").toString()));
			of.setStoreName(map.get("storeName"));
			of.setOpenid(map.get("openid"));
			String contentString = map.get("content").replace("\\", "");
			JSONArray json = JSONArray.fromObject(contentString);
			
			StringBuffer orderContent = new  StringBuffer();
			for (int i = 0; i < json.size(); i++) {
				JSONObject job = json.getJSONObject(i); // ���� jsonarray
														// ���飬��ÿһ������ת�� json ����
				of.setContent((String)job.get("dish_id").toString());
				of.setNum((String) job.get("num"));
				of.setAmount(job.getDouble("price"));
				orderInfoService.insertOrder(of);
				
				DishInfo dishInfo = dishInfoService.selectByPrimaryKey(job.getInt("dish_id"));
				if((i+1)==json.size()){
					orderContent.append(dishInfo.getDishName()+(String) job.get("num")+"�ݣ�");
				}else{
					orderContent.append(dishInfo.getDishName()+(String) job.get("num")+"�ݡ�");
				}
			}
			
			String address = map.get("address"); // �Ͳ͵�ַ
			
			User user = userService.selectByUid(uid);
			//��̨��������
			GoEasy goEasy = new GoEasy("BC-09d2856605de43419df8c01f1bfd590e");
			goEasy.publish("zx_public_" + user.getLoginName(), "�����µĶ�������ע��鿴!");
			//��������
			StringBuffer url = new StringBuffer();
			url.append("http://green.58yhkj.com/sms.aspx?action=send&userid=393&account=cdyhtz34&password=cdyhtz34888&mobile=");
			url.append(user.getMobilphone());
			url.append("&content=");
			url.append(UrlUtil.getURLEncoderString("�����µĲ�����������ע����ա�������ˮ��Ϊ��"));
			url.append(UrlUtil.getURLEncoderString(orderNum+"�������У�"+orderContent+"�Ͳ͵�ַΪ��"+address+"���µ�ʱ��Ϊ��"+map.get("ordertime")+"���ͻ���ϵ��ʽΪ��"+map.get("phonenum")+"���ͻ��ȵĺ��ż����뾡����ϵ���ʹлл��"));
			url.append(UrlUtil.getURLEncoderString("��������������"));
			url.append("&sendTime=&extno=");		
			ShortMessageUtil.httpRequest(url.toString());
			
			System.out.println("-------------------------------------------------");
			System.out.println("���ͳɹ�");
			out.println();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/list.action")
	public void queryOrder(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, Page page) {
		try {
			String currentPage = request.getParameter("page");
			String pageSize = request.getParameter("rows");

			String uid = request.getParameter("uid") == null ? ""	: request.getParameter("uid");
			String orderstatus = request.getParameter("orderstatus") == null ? ""	: request.getParameter("orderstatus");
			String content = request.getParameter("content") == null ? ""	: request.getParameter("content");

			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			response.setContentType("text/html;charset=UTF-8");
			OrderInfo order = new OrderInfo();
			order.setCurrentPage(Integer.parseInt(currentPage));
			order.setPageSize(Integer.parseInt(pageSize));
			order.setStoreId(Integer.parseInt(uid));
			if(!StringUtils.isNullOrEmpty(orderstatus)){
				order.setOrderstate(Integer.parseInt(orderstatus));
			}
			order.setContent(content);
			List<OrderInfo> uList = orderInfoService.queryOrder(order);
			int total = orderInfoService.queryOrderCount(order);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", uList);

			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(map)); 
			logger.info(gson.toJson(map));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * ��ѯͼ������
	 * @param request
	 * @param response
	 */
	@RequestMapping("/statistics.action")
	public void queryStatisticsDate(HttpServletRequest request,HttpServletResponse response) {
		try {
			String uid = request.getParameter("uid");
	        Map<String, Object> map = new HashMap<String, Object>();
			
	        /*����*/  
	        Integer[] salesVolume = new Integer[31];//{10,100,20,56,35,80,1,1,1,0,0,0};  
	        /*����, �·�����*/  
	        String[] months = new String[31];//{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};  
	        
	        ArrayList<String> dayList = GetDateUtil.getOneMonthDays(new Date());

	        StoreInfo store = new StoreInfo();
	        OrderInfo tempInfo = new OrderInfo();
	        if(!StringUtils.isNullOrEmpty(uid)){
	        	//��ȡstoreId
	        	store = storeInfoService.selectByUid(Integer.parseInt(uid));
	        	if(store != null){
	        		tempInfo.setStoreId(store.getStoreId());
	        	}
	        }
	        
	        
	        for(int i=0;i<dayList.size();i++){
	        	String days = (dayList.get(i)).substring(5, dayList.get(i).length());
	        	months[i] = days;
	        	tempInfo.setStoreName(dayList.get(i));
	        	salesVolume[i] = orderInfoService.getDayNum(tempInfo);
	        }
	        //map.put("xAxis", salesVolume);  
	        map.put("series",salesVolume);
	        map.put("xAxis", months);
	        Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(map)); 
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}	
	@RequestMapping("/statistics2.action")
	public void queryStatisticsDate2(HttpServletRequest request,HttpServletResponse response) {
		try {
			String uid = request.getParameter("uid");
	        Map<String, Object> map = new HashMap<String, Object>();
	        /*����*/  
	        Integer[] salesVolume = new Integer[7];//{10,100,20,56,35,80,1};  
	        /*����, �·�����*/  
	        String[] months = new String[7];
	        ArrayList<String> list = new ArrayList<String>();
	        //��ȡ��ȥ���������
	        list = GetDateUtil.getPastDateByDay(7);
	        
	        StoreInfo store = new StoreInfo();
	        OrderInfo tempInfo = new OrderInfo();
	        if(!StringUtils.isNullOrEmpty(uid)){
	        	//��ȡstoreId
	        	store = storeInfoService.selectByUid(Integer.parseInt(uid));
	        	if(store != null){
	        		tempInfo.setStoreId(store.getStoreId());
	        	}
	        }
	        
	        for(int i=0;i<list.size();i++){
	        	String days = (list.get(i)).substring(5, list.get(i).length());
	        	months[i] = days;
	        	tempInfo.setStoreName(list.get(i));
	        	salesVolume[i] = orderInfoService.getDayNum(tempInfo);
	        }
	        //��ȡ����
	        Date startTimes = GetDateUtil.sdf.parse(list.get(0));
	        Date endTimes = GetDateUtil.sdf.parse(list.get(list.size()-1));
	        OrderInfo info = new OrderInfo();
	        info.setStartTimes(startTimes);
	        info.setEndTimes(endTimes);
	        if(store != null){
	        	info.setStoreId(store.getStoreId());
	        }
	        
	        String itype5 = "0";//1:�����ʶ���ж���
	        String itype6 = "0";//2:�ܶ���
	        String atype5 = "0";//1:�����ʶ�쳣����
	        String atype6 = "0";//2:�쳣�ܶ���
	        
	        OrderInfo allOrder = orderInfoService.getAllOrderNum(info);//���ж�������
	        OrderInfo orderAbnormal	= orderInfoService.getAbnormalOrderNum(info);//�쳣��������
	        if(allOrder != null){
	        	itype5 = allOrder.getIntervalNumber().toString();
	        	atype5 = allOrder.getAllNumber().toString();
	        }
	        if(orderAbnormal != null){
	        	itype6 = orderAbnormal.getIntervalNumber().toString();
	        	atype6 = orderAbnormal.getAllNumber().toString();
	        }
	        
	        itype5 = "("+itype5+"/"+atype5+")";
	        itype6 = "("+itype6+"/"+atype6+")";
	        map.put("type5", itype5);
	        map.put("type6", itype6);
	        
	        map.put("series",salesVolume);
	        map.put("xAxis", months);
	        Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(map)); 
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * ȷ�϶�������
	 * @param request 
	 * @param response
	 */
	@RequestMapping("/confirmOrder.action")
	public void confirmOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			
			String orderId = request.getParameter("orderId");	
			if(!StringUtils.isNullOrEmpty(orderId)){
				OrderInfo order = new OrderInfo();
				order.setOrderId(Integer.parseInt(orderId));
				
				//��ѯ������Ϣ
				order = orderInfoService.getOrder(order);
				order.setOrderstate(1);
				//ִ��ȷ�϶�������
				int i = orderInfoService.updateOrder(order);
				if(i > 0){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			}else{
				logger.debug("����IDΪ��");
				map.put("success", false);
				map.put("msg", "����IDΪ��!");
			}
			pw.write(gson.toJson(map));
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * ���Ͷ�������
	 * @param request
	 * @param response
	 */
	@RequestMapping("/sendOrder.action")
	public void sendOrder(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			
			String orderId = request.getParameter("orderId");	
			if(!StringUtils.isNullOrEmpty(orderId)){
				OrderInfo order = new OrderInfo();
				order.setOrderId(Integer.parseInt(orderId));
				//��ѯ������Ϣ
				order = orderInfoService.getOrder(order);
				order.setOrderstate(2);
				order.setSendtime(new Date());
				//ִ�����Ͷ�������
				int i = orderInfoService.updateOrder(order);
				if(i > 0){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			}else{
				logger.debug("����IDΪ��");
				map.put("success", false);
				map.put("msg", "����IDΪ��!");
			}
			pw.write(gson.toJson(map));
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * �ܽӶ�������
	 * @param request
	 * @param response
	 */
	@RequestMapping("/refusedOrderBtn.action")
	public void refusedOrderBtn(HttpServletRequest request,HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			
			String orderId = request.getParameter("orderId");	
			if(!StringUtils.isNullOrEmpty(orderId)){
				OrderInfo order = new OrderInfo();
				order.setOrderId(Integer.parseInt(orderId));
				//��ѯ������Ϣ
				order = orderInfoService.getOrder(order);
				order.setOrderstate(4);
				//ִ�����Ͷ�������
				int i = orderInfoService.updateOrder(order);
				if(i > 0){
					map.put("success", true);
				}else{
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			}else{
				logger.debug("����IDΪ��");
				map.put("success", false);
				map.put("msg", "����IDΪ��!");
			}
			pw.write(gson.toJson(map));
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	
	//refusedOrderBtn
	@RequestMapping("/getOrderStatus.action")
	public void getOrderStatus(HttpServletRequest request,HttpServletResponse response) {
		try {
			PrintWriter pw = response.getWriter();
			String str = "[{\"id\":0,\"text\":\"������ȷ��\" ,\"selected\":true},{\"id\":1,\"text\":\"������\" },{\"id\":2,\"text\":\"������\"},{\"id\":3,\"text\":\"���\" },{\"id\":4,\"text\":\"�ܽ�\" }]";
			
			pw.write(str);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	/**
	 * ����һ��
	 * @author Miccke  DateTime 2017��9��15�� ����5:21:00
	 * @param dataObject
	 * @param out
	 */
	@RequestMapping(value = "/insertAgainOrder", method = RequestMethod.POST)
	@ResponseBody
	public void insertAgainrder(@RequestParam(value = "dataObject", required = true) String dataObject,PrintWriter out) {
		try {
			System.out.println(dataObject);
			Gson gson = new Gson();
			Map<String, String> map = gson.fromJson(dataObject, Map.class);
			Integer stroeId = Integer.parseInt(map.get("strorId").toString());
			StoreInfo  store = storeInfoService.selectByStoreId(stroeId); 
			List<OrderInfo> oilist = orderInfoService.selectByOrderNum(map.get("orderNum").toString());
			OrderNumUtil orderNumUtil = OrderNumUtil.getInstance();
			String orderNum = "XY"+ orderNumUtil.generaterNextNumber(orderInfoService.selectMaxNum());//"XY" + System.currentTimeMillis();
			String address = "";
			StringBuffer orderContent = new StringBuffer();
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Long phoneNum = (long)0;
			for(OrderInfo oi:oilist){
				oi.setOrderNum(orderNum);
				oi.setSendtime(null);
				oi.setFinishtime(null);
				oi.setOrderstate(0);
				orderInfoService.insertOrder(oi);
				System.out.println(oi.toString());
				address = oi.getAddress();
				phoneNum = oi.getPhonenum();
				oi.setOrdertime(date);
				
				DishInfo dishInfo = dishInfoService.selectByPrimaryKey(Integer.parseInt(oi.getContent()));
				orderContent.append(dishInfo.getDishName()+oi.getNum()+"�ݡ�");
			}
			User user = userService.selectByUid(store.getUid());
			GoEasy goEasy = new GoEasy("BC-09d2856605de43419df8c01f1bfd590e");
			goEasy.publish("zx_public_" + user.getLoginName(), "�����µĶ�������ע��鿴!");
			
			//��������
			StringBuffer url = new StringBuffer();
			url.append("http://green.58yhkj.com/sms.aspx?action=send&userid=393&account=cdyhtz34&password=cdyhtz34888&mobile=");
			url.append(user.getMobilphone());
			url.append("&content=");
			url.append(UrlUtil.getURLEncoderString("�����µĲ�����������ע����ա�������ˮ��Ϊ��"));
			url.append(UrlUtil.getURLEncoderString(orderNum+"�������У�"+orderContent+"�Ͳ͵�ַΪ��"+address+"���µ�ʱ��Ϊ��"+dateFormat.format(date)+"���ͻ���ϵ��ʽΪ��"+phoneNum+"���ͻ��ȵĺ��ż����뾡����ϵ���ʹлл��"));
			url.append(UrlUtil.getURLEncoderString("��������������"));
			url.append("&sendTime=&extno=");		
			ShortMessageUtil.httpRequest(url.toString());
			
			System.out.println("-------------------------------------------------");
			System.out.println("���ͳɹ�");
			out.println();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ҳ��������
	 * @author Miccke  DateTime 2017��9��13�� ����7:32:21
	 * @param out
	 * @param orderInfo
	 */
	@RequestMapping("/statelist")
	public void selectByState(PrintWriter out,OrderInfo orderInfo,HttpServletRequest request) {
		List<OrderInfo> oflistPage = orderInfoService.selectByState(orderInfo);
		double mathPage = oflistPage.size();
		
		double math = oflistPage.size()/pageSize;
		double pageNo = Math.ceil(math);
		if(pageNo == 1.0 || pageNo == 0.0){
			orderInfo.setPageSize(null);
		}	
		else{
			Integer currentPage = Integer.parseInt(request.getParameter("currentPage"));
			orderInfo.setCurrentPage(currentPage);
			orderInfo.setPageSize(4);
		}
		
		
		List<OrderInfo> oflist = orderInfoService.selectByState(orderInfo);
		System.out.println(oflist);
		Gson gson = new Gson();
		out.println(gson.toJson(oflist));
		out.flush();
		out.close();		
	}
	
	/**
	 * ��ҳ��������
	 * @author Miccke  DateTime 2017��9��13�� ����7:32:21
	 * @param out
	 * @param orderInfo
	 */
	@RequestMapping("/getPageNo")
	public void getPageNoByOpenId(PrintWriter out,OrderInfo orderInfo) {
		List<OrderInfo> oflist = orderInfoService.selectByState(orderInfo);
		int pageNo = (int) Math.ceil(oflist.size()/pageSize);
		out.println(pageNo);
		out.flush();
		out.close();		
	}
	
	
	
	@RequestMapping("/hotelList.action")
	public void hotelQueryOrder(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, Page page) {
		try {
			String currentPage = request.getParameter("page");
			String pageSize = request.getParameter("rows");

			//String uid = request.getParameter("uid") == null ? ""	: request.getParameter("uid");
			String orderstatus = request.getParameter("orderstatus") == null ? ""	: request.getParameter("orderstatus");
			String content = request.getParameter("content") == null ? ""	: request.getParameter("content");

			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			response.setContentType("text/html;charset=UTF-8");
			OrderInfo order = new OrderInfo();
			order.setCurrentPage(Integer.parseInt(currentPage));
			order.setPageSize(Integer.parseInt(pageSize));
			//order.setStoreId(Integer.parseInt(uid));
			if(!StringUtils.isNullOrEmpty(orderstatus)){
				order.setOrderstate(Integer.parseInt(orderstatus));
			}
			order.setContent(content);
			List<OrderInfo> uList = orderInfoService.queryOrder(order);
			int total = orderInfoService.queryOrderCount(order);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", total);
			map.put("rows", uList);

			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			pw.write(gson.toJson(map)); 
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}
	
	/*
	 * ��ʱ��ת��Ϊʱ���
	 */
	public static long dateToStamp(Date ordertime) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate=sdf.format(ordertime);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(sDate);
		long ts = date.getTime();
		return ts;
	}
	/**
	 * ���������
	 * @param out
	 */
	@RequestMapping("/stateEndlist")
	public void selectByState3(HttpServletRequest request,PrintWriter out) {
		String openid = request.getParameter("openid");
		List<OrderInfo> oflist = orderInfoService.selectByState3(openid);
		Gson gson = new Gson();
		out.println(gson.toJson(oflist));
		out.flush();
		out.close();
	}

	/**
	 *  д�붩������
	 * @param out
	 */
	@RequestMapping("/orderScore")
	public void selectByState3(PrintWriter out,@RequestParam(value = "dataObject", required = true) String orderNum) {
		System.out.println(orderNum);
		Gson gson = new Gson();
		Map<String, String> map = gson.fromJson(orderNum, Map.class);

		List<OrderInfo> orderNumList = orderInfoService.selectByOrderNum(map.get("orderNum").toString());
		for(OrderInfo oi:orderNumList){
			oi.setOrderScore(Integer.parseInt(map.get("starNum").toString()));
			
			orderInfoService.updateByOrderNum(oi);
			System.out.println(oi);
		}
				
	}

	@RequestMapping("setSession")
	public void setSessinon(HttpServletRequest request,HttpServletResponse response, PrintWriter out) throws IOException {
		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(request.getParameter("dataObject").toString());
		// ʹ��request�����getSession()��ȡsession�����session�������򴴽�һ��
		HttpSession session = request.getSession();
		// ��ȡ����װ�õĶ�����Ϣ
		Gson gson = new Gson();

		@SuppressWarnings("unchecked")
		Map<String, String> map = gson.fromJson(request.getParameter("dataObject"), Map.class);
		// �����ݴ洢��session��
		session.setAttribute("storeId", map.get("storeId"));
		session.setAttribute("storeName", map.get("storeName"));
		session.setAttribute("deliveryCost", map.get("deliveryCost"));
		session.setAttribute("content", map.get("content"));
		session.setAttribute("amount", map.get("amount"));
		session.setAttribute("uid", map.get("uid"));
		
	}
}  
