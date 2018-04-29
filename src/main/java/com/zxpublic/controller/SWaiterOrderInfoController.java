package com.zxpublic.controller;

import io.goeasy.GoEasy;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.SWaiterOrderInfoService;
import com.zxpublic.service.UserService;
import com.zxpublic.util.OrderNumUtil;
import com.zxpublic.util.ShortMessageUtil;
import com.zxpublic.util.UrlUtil;
import com.zxpublic.vo.SWaiterOrderInfo;
import com.zxpublic.vo.User;

@Controller
@RequestMapping("/sWaiterOrder")
public class SWaiterOrderInfoController {

	@Autowired
	private SWaiterOrderInfoService sWaiterOrderInfoService;
	@Autowired
	private UserService userService;
	
	Log logger= LogFactory.getLog(SWaiterOrderInfoController.class);
	
	@RequestMapping("/insert")
	public void insert(HttpServletRequest request, PrintWriter out) {
		String dataObject = request.getParameter("dataObject");
		try {
			
			SWaiterOrderInfo sWaiterOrderInfo = new SWaiterOrderInfo();
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String, String> map = gson.fromJson(dataObject, Map.class);

			Integer waiterType = Integer.parseInt(map.get("waiterType"));
			String userName = map.get("userName");
			Integer userSex = Integer.parseInt(map.get("userSex"));
			String userPhone = map.get("userPhone");
			String openid = map.get("openid");
			String waiterno = map.get("waiterNo");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date startTime = df.parse(map.get("startTime").toString());
			Integer serveState = 0;
			String remark = map.get("remark");

			OrderNumUtil orderNumUtil = OrderNumUtil.getInstance();
			String orderNum = "SW"+orderNumUtil.generaterNextNumber(sWaiterOrderInfoService.selectMaxNum());
			
			sWaiterOrderInfo.setWaiterType(waiterType);
			sWaiterOrderInfo.setOrderNum(orderNum);
			sWaiterOrderInfo.setUserName(userName);
			sWaiterOrderInfo.setUserSex(userSex);
			sWaiterOrderInfo.setUserPhone(userPhone);
			sWaiterOrderInfo.setOpenid(openid);
			sWaiterOrderInfo.setWaiterno(waiterno);
			sWaiterOrderInfo.setDutyTime(startTime);
			sWaiterOrderInfo.setServeState(serveState);
			sWaiterOrderInfo.setRemark(remark);
			sWaiterOrderInfo.setStartTime(new Date());
			sWaiterOrderInfoService.insert(sWaiterOrderInfo);
			System.out.println(sWaiterOrderInfo);
			List<User> userList = userService.selectByUtype1();
			String loginname = "";
			String mobilphone = "";
			for (User user : userList) {
				loginname = user.getLoginName();
				mobilphone = user.getMobilphone();
			}
			GoEasy goEasy = new GoEasy("BC-09d2856605de43419df8c01f1bfd590e");
			goEasy.publish("zx_public_" + loginname, "你有新的订单，请注意查看!");

			StringBuffer url = new StringBuffer();
			url.append("http://green.58yhkj.com/sms.aspx?action=send&userid=393&account=cdyhtz34&password=cdyhtz34888&mobile=");
			url.append(mobilphone);
			url.append("&content=");
			url.append(UrlUtil.getURLEncoderString("您有新的服务订单，请注意查收，订单流水号为："));
			url.append(UrlUtil.getURLEncoderString(orderNum+";用户姓名为："+userName+";用户联系方式为："+userPhone+";请尽快联系用户，并询问相关服务信息。谢谢！"));
			url.append(UrlUtil.getURLEncoderString("【信伊健康服务】"));
			url.append("&sendTime=&extno=");		
			ShortMessageUtil.httpRequest(url.toString());
			
			out.println();
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/serverNoEndlist")
	public void getListNoEnd(HttpServletRequest request, PrintWriter out,
			String openId) {

		openId = request.getParameter("openid");
		if (!StringUtils.isNullOrEmpty(openId)) {
			System.out.println(openId);
			List<SWaiterOrderInfo> silist = sWaiterOrderInfoService.selectByStateNoEnd(openId);
			List<SWaiterOrderInfo> servelist = new ArrayList<SWaiterOrderInfo>();
			for (SWaiterOrderInfo si : silist) {
				if (si.getDutyTime() != null) {
					si.setDutyTime(si.getDutyTime());
					servelist.add(si);
				} else {
					si.setDutyTime(null);
					servelist.add(si);
				}
			}
			Gson gson = new Gson();
			out.println(gson.toJson(servelist));
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/serverlist")
	public void getListAct(HttpServletRequest request,PrintWriter out, String openId) {
		openId = request.getParameter("openid");
		if (!StringUtils.isNullOrEmpty(openId)) {
		List<SWaiterOrderInfo> silist = sWaiterOrderInfoService.selectByState(openId);
		List<SWaiterOrderInfo> servelist = new ArrayList<SWaiterOrderInfo>();
		for (SWaiterOrderInfo si : silist) {
			System.out.println(si);
			if (si.getDutyTime() != null) {
				si.setDutyTime(si.getDutyTime());
				servelist.add(si);
			} else {
				si.setDutyTime(null);
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
