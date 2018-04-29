package com.zxpublic.controller;

import io.goeasy.GoEasy;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.OrderInfoService;
import com.zxpublic.service.SWaiterOrderInfoService;
import com.zxpublic.service.ServeInfoService;
import com.zxpublic.service.UserService;
import com.zxpublic.util.GetDateUtil;
import com.zxpublic.vo.OrderInfo;
import com.zxpublic.vo.Page;
import com.zxpublic.vo.SWaiterOrderInfo;
import com.zxpublic.vo.ServeInfo;
import com.zxpublic.vo.User;

/**
 * ���񶩵����������
 * 
 * @author CW5128
 *
 */
@Controller
@RequestMapping("/serveInfo")
public class ServeInfoController {
	@Autowired
	private ServeInfoService serveInfoService;
	@Autowired
	private SWaiterOrderInfoService sWaiterOrderInfoService;

	@Autowired
	private OrderInfoService orderInfoService;
	@Autowired
	private UserService userService;

	Log logger = LogFactory.getLog(ServeInfoController.class);

	@RequestMapping(value = "/insertServe", method = RequestMethod.POST)
	@ResponseBody
	public void insertServe(
			@RequestParam(value = "dataObject", required = true) String dataObject,
			PrintWriter out) {
		try {
			ServeInfo si = new ServeInfo();
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String, String> map = gson.fromJson(dataObject, Map.class);
			int serveType = Integer.parseInt(map.get("serveType").toString());

			si.setServetype(Integer.parseInt(map.get("serveType").toString()));
			si.setPersonalneed(map.get("personalNeed"));
			si.setAgebracket(map.get("ageBracket"));
			if (serveType == 1) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(map.get("dueDate").toString());
				si.setDuedate(date);
			} else if (serveType == 2) {
				si.setLactationdemand(map.get("lactationdemand"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(map.get("dutytime").toString());
				si.setDutytime(date);
			} else if (serveType == 3) {
				si.setEscortperson(map.get("escortperson"));
				si.setSelfcare(map.get("selfcare"));
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date date = df.parse(map.get("dutytime").toString());
				si.setDutytime(date);
			}
			si.setUsername(map.get("userName"));
			si.setPhonenum(map.get("phoneNum"));
			si.setSubtime(new Date());
			si.setRemark(map.get("remark"));
			try {
				si.setOpenid(map.get("openid"));
				serveInfoService.insertServe(si);
				List<User> userList = userService.selectByUtype1();
				String loginname = "";
				for (User user : userList) {
					loginname = user.getLoginName();
				}
				GoEasy goEasy = new GoEasy(
						"BC-09d2856605de43419df8c01f1bfd590e");
				goEasy.publish("zx_public_" + loginname, "�����µĶ�������ע��鿴!");
				out.println();
				out.flush();
				out.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/servicList.action")
	public void queryOrder(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, Page page) {
		try {
			String currentPage = request.getParameter("page");
			String pageSize = request.getParameter("rows");

			// String uid = request.getParameter("uid") == null ? "" :
			// request.getParameter("uid");
			String username = request.getParameter("username") == null ? ""
					: request.getParameter("username");
			String servestate = request.getParameter("servestate") == null ? ""
					: request.getParameter("servestate");
			String servetype = request.getParameter("servetype") == null ? ""
					: request.getParameter("servetype");
			// String content = request.getParameter("content") == null ? "" :
			// request.getParameter("content");

			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			response.setContentType("text/html;charset=UTF-8");
			
			SWaiterOrderInfo serve = new SWaiterOrderInfo();
//			ServeInfo serve = new ServeInfo();
			serve.setCurrentPage(Integer.parseInt(currentPage));
			serve.setPageSize(Integer.parseInt(pageSize));

			if (!StringUtils.isNullOrEmpty(username)) {
				serve.setUserName(username);
			}
			if (!StringUtils.isNullOrEmpty(servetype)) {
				serve.setWaiterType(Integer.parseInt(servetype));
			}
			if (!StringUtils.isNullOrEmpty(servestate)) {
				serve.setServeState(Integer.parseInt(servestate));
			}
			List<SWaiterOrderInfo> uList = sWaiterOrderInfoService.queryOrderList(serve);
			int total = sWaiterOrderInfoService.queryOrderCount(serve);
//			List<ServeInfo> uList = serveInfoService.queryOrderList(serve);
//			int total = serveInfoService.queryOrderCount(serve);

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

	/**
	 * ��ȡ����״̬
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getServeStatus.action")
	public void getOrderStatus(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 0����δȷ�ϣ�1����ȷ�ϣ�2������Ա���ţ�3�������,4�ܾ�����
			PrintWriter pw = response.getWriter();
			String str = "[{\"id\":0,\"text\":\"����δȷ��\"},{\"id\":1,\"text\":\"����ȷ��\" },{\"id\":2,\"text\":\"������Ա����\"},{\"id\":3,\"text\":\"�������\" },{\"id\":4,\"text\":\"�ܾ�����\" }]";

			pw.write(str);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}

	/**
	 * ��ȡ��������
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getServeType.action")
	public void getServeType(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// //1��ɩ��2����ʦ��3�㻤ʦ
			PrintWriter pw = response.getWriter();
			String str = "[{\"id\":1,\"text\":\"��ɩ\"},{\"id\":2,\"text\":\"����ʦ\" },{\"id\":3,\"text\":\"�㻤ʦ\"}]";

			pw.write(str);
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
	}

	/**
	 * ȷ�Ϸ��񶩵�
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/confirmServe.action")
	public void confirmServe(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();

			String serveId = request.getParameter("serveId");
			if (!StringUtils.isNullOrEmpty(serveId)) {
				//sWaiterOrderInfoService
				//ServeInfo serve = new ServeInfo();
				SWaiterOrderInfo info = new SWaiterOrderInfo();
				//serve.setServeId(Integer.parseInt(serveId));
				info.setId(Long.parseLong(serveId));
				// ��ѯ������Ϣ
				// order = orderInfoService.getOrder(order);
				//serve.setServestate(1);
				info.setServeState(1);
				// ִ��ȷ�϶�������
				int i = sWaiterOrderInfoService.updateServe(info);
				if (i > 0) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			} else {
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
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/sendServe.action")
	public void sendServe(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();

			String serveId = request.getParameter("serveId");
			if (!StringUtils.isNullOrEmpty(serveId)) {
//				ServeInfo serve = new ServeInfo();
//				serve.setServeId(Integer.parseInt(serveId));
//				// ��ѯ������Ϣ
//				serve.setServestate(2);
//				// ִ�����Ͷ�������
//				int i = serveInfoService.updateServe(serve);

				SWaiterOrderInfo info = new SWaiterOrderInfo();
				info.setId(Long.parseLong(serveId));
				info.setServeState(2);
				// ִ��ȷ�϶�������
				int i = sWaiterOrderInfoService.updateServe(info);
				
				if (i > 0) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			} else {
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
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/refusedServeBtn.action")
	public void refusedServeBtn(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();

			String serveId = request.getParameter("serveId");
			if (!StringUtils.isNullOrEmpty(serveId)) {
//				ServeInfo serve = new ServeInfo();
//				serve.setServeId(Integer.parseInt(serveId));
//				// ��ѯ������Ϣ
//				serve.setServestate(4);
//				// ִ�����Ͷ�������
//				int i = serveInfoService.updateServe(serve);
				SWaiterOrderInfo info = new SWaiterOrderInfo();
				info.setId(Long.parseLong(serveId));
				info.setServeState(4);
				// ִ��ȷ�϶�������
				int i = sWaiterOrderInfoService.updateServe(info);
				
				if (i > 0) {
					map.put("success", true);
				} else {
					map.put("success", false);
					map.put("msg", "δ�޸��κ�����!");
				}
			} else {
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
	 * ����ͳ������
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/statistics.action")
	public void queryStatisticsDate(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();

			/* ���� */
			Integer[] salesVolume = new Integer[31];// {10,100,20,56,35,80,1,1,1,0,0,0};
			Integer[] serveVolume = new Integer[31];// {10,20,50,60,80,20,70,0,100,120,20,50,10,10,10};
			/* ����, �·����� */
			String[] months = new String[31];// {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};

			ArrayList<String> dayList = GetDateUtil.getOneMonthDays(new Date());
			OrderInfo tempInfo = new OrderInfo();
			for (int i = 0; i < dayList.size(); i++) {
				String days = (dayList.get(i)).substring(5, dayList.get(i)
						.length());
				months[i] = days;
				tempInfo.setStoreName(dayList.get(i));
				salesVolume[i] = orderInfoService.getDayNum(tempInfo);
				serveVolume[i] = sWaiterOrderInfoService.getDayNum(dayList.get(i));
			}
			// map.put("xAxis", salesVolume);
			map.put("serve", serveVolume);
			map.put("series", salesVolume);
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
	public void queryStatisticsDate2(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			/* ���� */
			// Integer[] salesVolume = {10,100,20,56,35,80,1};
			// Integer[] serveVolume = {10,20,50,60,80,20,70};
			Integer[] serveVolume = new Integer[7];
			/* ����, �·����� */
			String[] months = new String[7];
			ArrayList<String> list = new ArrayList<String>();
			// ��ȡ��ȥ���������
			list = GetDateUtil.getPastDateByDay(7);

			for (int i = 0; i < list.size(); i++) {
				String days = (list.get(i)).substring(5, list.get(i).length());
				months[i] = days;
				serveVolume[i] = sWaiterOrderInfoService.getDayNum(list.get(i));
			}

			// ��ȡ����
			Date startTimes = GetDateUtil.sdf.parse(list.get(0));
			Date endTimes = GetDateUtil.sdf.parse(list.get(list.size() - 1));
			SWaiterOrderInfo sinfo = new SWaiterOrderInfo();
			sinfo.setsTime(startTimes);
			sinfo.seteTime(endTimes);
			
			List<SWaiterOrderInfo> listIn = sWaiterOrderInfoService.getIntervalNum(sinfo);
			List<SWaiterOrderInfo> listAll = sWaiterOrderInfoService.getAllOrderNum(sinfo);
			SWaiterOrderInfo infoAbnormal = sWaiterOrderInfoService.getAbnormalOrderNum(sinfo);
//			ServeInfo info = new ServeInfo();
//			info.setStartTime(startTimes);
//			info.setEndTime(endTimes);
//			//sWaiterOrderInfoService
//			List<ServeInfo> listIn = serveInfoService.getIntervalNum(info);
//			List<ServeInfo> listAll = serveInfoService.getAllOrderNum(null);
//			ServeInfo serveAbnormal = serveInfoService.getAbnormalOrderNum(info);
			String itype1 = "0";// 1:�����ʶ��ɩ
			String itype2 = "0";// 2:�����ʶ����ʦ
			String itype3 = "0";// 3:�����㻤
			String itype4 = "0";// 4:�����쳣����
			String atype1 = "0";// 1:��ʶ��ɩ
			String atype2 = "0";// 2:��ʶ����ʦ
			String atype3 = "0";// 3���㻤
			String atype4 = "0";// 4���쳣����

			itype4 = infoAbnormal.getIntervalNumber().toString();
			atype4 = infoAbnormal.getAllNumber().toString();

			for (int i = 0; i < listIn.size(); i++) {
				SWaiterOrderInfo serve = new SWaiterOrderInfo();
				serve = listIn.get(i);
				if (serve != null && serve.getWaiterType() == 1) {
					itype1 = serve.getIntervalNumber().toString();
				}
				if (serve != null && serve.getWaiterType() == 2) {
					itype2 = serve.getIntervalNumber().toString();
				}
				if (serve != null && serve.getWaiterType() == 3) {
					itype3 = serve.getIntervalNumber().toString();
				}
			}
			for (int i = 0; i < listAll.size(); i++) {
				SWaiterOrderInfo serve = new SWaiterOrderInfo();
				serve = listAll.get(i);
				if (serve != null && serve.getWaiterType() == 1) {
					atype1 = serve.getAllNumber().toString();
				}
				if (serve != null && serve.getWaiterType() == 2) {
					atype2 = serve.getAllNumber().toString();
				}
				if (serve != null && serve.getWaiterType() == 3) {
					atype3 = serve.getAllNumber().toString();
				}
			}

			itype1 = "(" + itype1 + "/" + atype1 + ")";
			itype2 = "(" + itype2 + "/" + atype2 + ")";
			itype3 = "(" + itype3 + "/" + atype3 + ")";
			itype4 = "(" + itype4 + "/" + atype4 + ")";
			map.put("type1", itype1);
			map.put("type2", itype2);
			map.put("type3", itype3);
			map.put("type4", itype4);
			map.put("serve", serveVolume);
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

	@RequestMapping("/serverlist")
	public void getListAct(HttpServletRequest request,PrintWriter out, String openId) {
		openId = request.getParameter("openid");
		if (!StringUtils.isNullOrEmpty(openId)) {
		List<ServeInfo> silist = serveInfoService.selectByState(openId);
		List<ServeInfo> servelist = new ArrayList<ServeInfo>();
		for (ServeInfo si : silist) {
			if (si.getDutytime() != null) {
				si.setDutytime(si.getDutytime());
				servelist.add(si);
			} else {
				si.setDutytime(null);
				servelist.add(si);
			}
		}
		Gson gson = new Gson();
		out.println(gson.toJson(servelist));
		out.flush();
		out.close();
		}
	}

	@RequestMapping("/serverNoEndlist")
	public void getListNoEnd(HttpServletRequest request, PrintWriter out,
			String openId) {

		openId = request.getParameter("openid");
		if (!StringUtils.isNullOrEmpty(openId)) {
			System.out.println(openId);
			List<ServeInfo> silist = serveInfoService
					.selectByStateNoEnd(openId);
			List<ServeInfo> servelist = new ArrayList<ServeInfo>();
			for (ServeInfo si : silist) {
				if (si.getDutytime() != null) {
					si.setDutytime(si.getDutytime());
					servelist.add(si);
				} else {
					si.setDutytime(null);
					servelist.add(si);
				}
			}
			Gson gson = new Gson();
			out.println(gson.toJson(servelist));
			out.flush();
			out.close();
		}
	}
}
