package com.zxpublic.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.zxpublic.service.SWaiterCertificatesService;
import com.zxpublic.service.SWaiterMessageService;
import com.zxpublic.service.SWaiterRelationService;
import com.zxpublic.vo.Page;
import com.zxpublic.vo.SWaiterCertificates;
import com.zxpublic.vo.SWaiterMessage;
import com.zxpublic.vo.SWaiterRelation;

/**
 * 
 * @ClassName: SWaiterController 
 * @Description: TODO(服务人员心里控制器) 
 * @author CW5128 Miccke 
 * @date 2017年8月13日 上午10:44:27 
 * @version V1.0
 */

@Controller
@RequestMapping("/swaiter")

public class SWaiterMessageController {
	@Autowired
	private SWaiterMessageService sWaiterMessageService;
	@Autowired
	private SWaiterCertificatesService sWaiterCertificatesService;
	@Autowired
	private SWaiterRelationService sWaiterRelationService;
	
	Log logger= LogFactory.getLog(OrderInfoController.class);

	/**
	 * 插入、更新、删除 服务人员信息
	 * @param dataObject
	 * @param out
	 */
	@RequestMapping(value = "/CRUDMsg", method = RequestMethod.POST)
	@ResponseBody
	public void insertOrder(@RequestParam(value = "dataObject", required = true) String dataObject,	PrintWriter out) {
		try {
			int flag = 0;
			SWaiterMessage sm = new SWaiterMessage(); 
			Gson gson = new Gson();
			@SuppressWarnings("unchecked")
			Map<String, String> map = gson.fromJson(dataObject, Map.class);
			System.out.println(map.toString());
			String id = map.get("id");
			//List<SWaiterRelation> srList = new ArrayList<SWaiterRelation>();
			String attachId = map.get("attachId");
			//判断ID是否为空
			if(StringUtils.isNullOrEmpty(id)){
				//为空，则插入数据
				sm.setWaiterNo(createWaiterNo());//编号
				sm.setWaiterName(map.get("waiterName"));//姓名
				sm.setWaiterIdcard(map.get("waiterIdcard"));//身份证
				sm.setWaiterPrice(map.get("waiterPrice")==null ? 0 : Long.parseLong(map.get("waiterPrice")));//加个
				sm.setWaiterAge(map.get("waiterAge")==null ? 0 : Integer.parseInt(map.get("waiterAge")));//年龄 
				sm.setWaiterProvince(map.get("waiterProvince"));//籍贯省
				sm.setWaiterCity(map.get("waiterCity"));//籍贯市
				sm.setWaiterBabycount(map.get("waiterBabycount")==(null) ? 0 : Integer.parseInt(map.get("waiterBabycount")));//历史带宝宝数量
				sm.setWaiterCharacteristics(map.get("waiterCharacteristics"));//特征（aaa,bbb,ccc）
				sm.setWaiterPlatformMsg(map.get("waiterPlatformMsg"));//平台认证信息
				sm.setWaiterExperience(map.get("waiterExperience"));//经验
				sm.setWaiterImageurl(map.get("waiterImageurl"));//头像
				sm.setWaiterStarlevel(Integer.parseInt(map.get("waiterStarlevel")));//星级
				sm.setWaiterConstellation(map.get("waiterConstellation"));// 星座
				sm.setWaiterEducation(Integer.parseInt(map.get("waiterEducation")));//学历
				sm.setJobTitle(map.get("jobTitle"));//职称
				sm.setWaiterType(map.get("waiterType")==null ? 0 : Integer.parseInt(map.get("waiterType")+""));
				sm.setRemark(map.get("remark"));//备注
				//sm.setAttachId(map.get("attachId"));
				sWaiterMessageService.insertWaiter(sm);
				flag = 1;
				//图片附件操作
				if(!StringUtils.isNullOrEmpty(attachId)){
					//查询已有的附件ID
					String[] ids = attachId.split(",");
					/*
						SWaiterRelation srd = new SWaiterRelation();
						srd.setType(2);
						srd.setWaiterId(sm.getId());
						//删除数据
						sWaiterRelationService.deleteByWaiter(srd);
					*/
					for (int i = 0; i < ids.length; i++) {
						SWaiterRelation sr = new SWaiterRelation();
						sr.setType(2);
						sr.setWaiterId(sm.getId());
						String sid = ids[i];
						sr.setRelationId(Long.parseLong(sid));
						sWaiterRelationService.insert(sr);
					}
				}
			}else{
				//不为空，则更新数据
				sm.setId(Long.parseLong(id));
				//sm.setWaiterNo("");//编号    编号不能修改
				sm.setWaiterName(map.get("waiterName"));//姓名
				sm.setWaiterIdcard(map.get("waiterIdcard"));//身份证
				sm.setWaiterPrice(map.get("waiterPrice")==null ? 0 : Long.parseLong(map.get("waiterPrice")));//加个
				sm.setWaiterAge(map.get("waiterAge")==null ? 0 : Integer.parseInt(map.get("waiterAge")));//年龄 
				sm.setWaiterProvince(map.get("waiterProvince"));//籍贯省
				sm.setWaiterCity(map.get("waiterCity"));
				sm.setWaiterStarlevel(Integer.parseInt(map.get("waiterStarlevel")));//星级
				sm.setWaiterConstellation(map.get("waiterConstellation"));// 星座
				sm.setWaiterEducation(Integer.parseInt(map.get("waiterEducation")));//学历
				sm.setJobTitle(map.get("jobTitle"));//职称
				sm.setWaiterBabycount(map.get("waiterBabycount")==(null) ? 0 : Integer.parseInt(map.get("waiterBabycount")));//历史带宝宝数量
				sm.setWaiterCharacteristics(map.get("waiterCharacteristics"));//特征（aaa,bbb,ccc）
				sm.setWaiterPlatformMsg(map.get("waiterPlatformMsg"));//平台认证信息
				sm.setWaiterExperience(map.get("waiterExperience"));//经验
				sm.setWaiterImageurl(map.get("waiterImageurl"));//头像
				sm.setRemark(map.get("remark"));//备注
				sm.setWaiterType(map.get("waiterType")==null ? 0 : Integer.parseInt(map.get("waiterType")+""));
				//sm.setAttachId(map.get("attachId"));
				sWaiterMessageService.updateWaiter(sm);
				flag = 2;
				//图片附件操作
				if(!StringUtils.isNullOrEmpty(attachId)){
					//查询已有的附件ID
					SWaiterRelation swr = new SWaiterRelation();
					swr.setWaiterId(Long.parseLong(id));
					swr.setType(2);
					List<SWaiterRelation> rlist = sWaiterRelationService.getListBySwaiter(swr);
					
					String[] ids = attachId.split(",");
					//定义一个删除的ID数组（长度必定不会大于查询出来的数据长度）
					Long[] deleteids = new Long[rlist.size()];
					//定义一个新增的id数组，（长度必定不会大于传入的数组长度）
					String[] insertids = new String[ids.length];
					int x = 0;
					for (int i = 0; i < ids.length; i++) {
						String sid = ids[i];
						boolean flagtemp = false;
						for (int j = 0; j < rlist.size(); j++) {
							Long rid = rlist.get(j).getRelationId();
							if(rid.equals(Long.parseLong(sid))){
								//相等，说明数据库中存在
								flagtemp = true;
							}
						}
						if(!flagtemp){
							//flagtemp == false，说明是新增数据
							insertids[x] = sid;
							x++;
						}
					}
					
					int y = 0;
					for (int i = 0; i < rlist.size(); i++) {
						Long rid = rlist.get(i).getRelationId();
						Long rid_ = rlist.get(i).getId();
						boolean flagtemp = false;
						for (int j = 0; j < ids.length; j++) {
							String sid = ids[j];
 							if(rid.equals(Long.parseLong(sid))){
								//相等，说明传入数组中存在
								flagtemp = true;
							}
						}
						if(!flagtemp){
							//flagtemp == false，说明是删除数据（传入数组中不存在）
							deleteids[y] = rid_;
							y++;
						}
					}
					
					//删除数据
					for (int i = 0; i < deleteids.length; i++) {
						if(deleteids[i] != null){
							sWaiterRelationService.deleteByPrimaryKey(deleteids[i]);
						}
					}
					//新增数据
					for (int i = 0; i < insertids.length; i++) {
						if(insertids[i] != null){
							SWaiterRelation sr = new SWaiterRelation();
							sr.setType(2);
							sr.setWaiterId(sm.getId());
							String sid = insertids[i];
							sr.setRelationId(Long.parseLong(sid));
							sWaiterRelationService.insert(sr);
						}
					}
				}
			}
			
			if(flag == 1){
				out.write("添加成功!");
			}else if(flag ==2){
				out.write("更新成功!");
			}else{
				out.write("操作异常!");
			}
			out.println();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/deleteMsgById")
	public void deleteMsgById(HttpServletRequest request,PrintWriter out){
		try {
			Long id = Long.parseLong(request.getParameter("ids"));
			SWaiterMessage sw = new SWaiterMessage();
			sw.setId(id);
			sWaiterMessageService.deleteMsgById(sw);
			out.print(1);
			out.flush();
			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print(0);
			out.flush();
			out.close();
		}
	
	}
	
	/**
	 * 获取分页数据
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param page
	 */
	@RequestMapping("/list.action")
	public void querySWaiterMsgList(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, Page page) {
		try {
			String currentPage = request.getParameter("page");
			String pageSize = request.getParameter("rows");

			String username = request.getParameter("userName") == null ? ""
					: request.getParameter("userName");
			String age = request.getParameter("age") == null ? ""
					: request.getParameter("age");

			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			response.setContentType("text/html;charset=UTF-8");
			SWaiterMessage sm = new SWaiterMessage();
			sm.setCurrentPage(Integer.parseInt(currentPage));
			sm.setPageSize(Integer.parseInt(pageSize));

			if (!StringUtils.isNullOrEmpty(username)) {
				sm.setWaiterName(username);
			}
			if (!StringUtils.isNullOrEmpty(age)) {
				sm.setWaiterAge(Integer.parseInt(age));
			}
			List<SWaiterMessage> uList = sWaiterMessageService.getMsgList(sm);
			int total = sWaiterMessageService.getMsgListCount(sm);

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
	 * 依据ID查询对象信息
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping("/getSWaiterMsg")
	public void querySWaiterMsg(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) {
		try {
			String id = request.getParameter("id");
			if(!StringUtils.isNullOrEmpty(id)){
				SWaiterMessage smsg = sWaiterMessageService.get(Long.parseLong(id));
				
				SWaiterRelation sw = new SWaiterRelation();
				sw.setWaiterId(Long.parseLong(id));
				sw.setType(2);
				//查询附件ID和名称的集合
				List<SWaiterRelation> list = sWaiterRelationService.getListBySwaiter(sw);
				smsg.setRlist(list);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SWaiterMsg", smsg);
				
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
	 * 
	 * @Description: TODO(根据waiterNo返回服务人员的信息) 
	 * @param request
	 * @param response
	 * @param map
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping("/swaiterMessage")
	public String getSWaiterByNo(HttpServletRequest request,HttpServletResponse response,ModelMap map) {

			String waiterNo = (String) request.getParameter("waiterNo");
			System.out.println(waiterNo);
			response.setContentType("text/html;charset=UTF-8");
			SWaiterMessage sWaiterMessage1 = sWaiterMessageService.getSWaiterByNo1(waiterNo);
			SWaiterMessage sWaiterMessage2 = sWaiterMessageService.getSWaiterByNo2(waiterNo);
			SWaiterCertificates sWaiterCertificates = sWaiterCertificatesService.getCertificatesByWaiterId(waiterNo);

			map.put("sWaiterMessage", sWaiterMessage1);	
			map.put("sWaiterMessage2", sWaiterMessage2);	
			map.put("sWaiterCertificates", sWaiterCertificates);
			System.out.println(sWaiterMessage1);
			System.out.println(sWaiterMessage2);
			return "../public/clientPage/servePerson";
	}
	/**
	 * 生产员工编号
	 * @return
	 */
	public String createWaiterNo(){
		//No.0000000001
		//定义编号长度为13，
		int lenth = 13;
		StringBuffer sb = new StringBuffer();
		sb.append("No.");
		int total = sWaiterMessageService.getMsgListCount(null);
		String numStr = total+1 + "";
		for (int i = 0; i < lenth-numStr.length()-3; i++) {
			sb.append("0");
		}
		sb.append(numStr);
		return sb.toString();
	}
	
	/**
	 * 
	 * @Description: TODO(根据type返回相应的waiterlist) 
	 * @param request
	 * @param response    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	@RequestMapping("/swaiterlist")
	public void getSWaiterByType(HttpServletRequest request,HttpServletResponse response) {
		try {
			SWaiterMessage sw = new SWaiterMessage();	
			if(request.getParameter("waiterAge").equals("不限")){
				sw.setWaiterAge(null);
			}else{
				String age = request.getParameter("waiterAge");
				if(age.equals("其它")){
					sw.setWaiterAge(51);
				}else{
					String ageValue = org.apache.commons.lang3.StringUtils.substringBefore(age, "-"); 
					sw.setWaiterAge(Integer.parseInt(ageValue));
				}	
			}
			if(request.getParameter("waiterProvince").equals("请选择省")){
				sw.setWaiterProvince("");	
			}else{				
				sw.setWaiterProvince(request.getParameter("waiterProvince"));	
			}
			if(request.getParameter("waiterCity") != null){
				if(request.getParameter("waiterCity").equals("不限")){
					sw.setWaiterCity(null);
				}else{
					sw.setWaiterCity(request.getParameter("waiterCity"));	
				}
			}else{
				sw.setWaiterCity(null);
			}
			
			if(request.getParameter("waiterConstellation").equals("不限")){
				sw.setWaiterConstellation(null);
			}else{
				sw.setWaiterConstellation(request.getParameter("waiterConstellation"));
			}
			if(request.getParameter("waiterStarlevel").equals("0")){
				sw.setWaiterStarlevel(null);
			}else{
				sw.setWaiterStarlevel(Integer.parseInt(request.getParameter("waiterStarlevel")));
			}
			if(request.getParameter("waiterEducation").equals("0")){
				sw.setWaiterEducation(null);
			}else{
				sw.setWaiterEducation(Integer.parseInt(request.getParameter("waiterEducation")));
			}
			sw.setWaiterType(Integer.parseInt(request.getParameter("waiterType"))); 
			response.setContentType("text/html;charset=UTF-8");
			List<SWaiterMessage> swlist = sWaiterMessageService.getSWaiterByType1(sw);	
			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.println(gson.toJson(swlist));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}
	
	@RequestMapping("/getLikeList")
	public void getLikeList(PrintWriter out,HttpServletRequest request){
		SWaiterMessage sw = new  SWaiterMessage();
		String inputValue = request.getParameter("inputValue");
		if(inputValue.equals("月嫂")){
			sw.setWaiterType(1);
		}else if(inputValue.equals("催乳师")){
			sw.setWaiterType(2);
		}else if(inputValue.equals("陪护师")){
			sw.setWaiterType(3);
		}else{
			sw.setWaiterName(inputValue);
		}
		
		List<SWaiterMessage> swlist = sWaiterMessageService.getLikeList(sw);
		System.out.println(swlist);
		Gson gson = new  Gson();
		out.print(gson.toJson(swlist));
		out.flush();
		out.close();
	}
}
