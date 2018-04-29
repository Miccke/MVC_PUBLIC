package com.zxpublic.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.zxpublic.service.SysMenuService;
import com.zxpublic.service.UserService;
import com.zxpublic.vo.Page;
import com.zxpublic.vo.SysMenu;
import com.zxpublic.vo.User;

/**
 * 
 * @author penggl  2017��7��17��11:06:30
 */
@Controller  
public class MainController {

	Log logger= LogFactory.getLog(MainController.class);
	
    @Resource  
    private UserService userService;  
    @Resource
    private SysMenuService menuService;
	
    @RequestMapping("/")    
    public String getIndex(){
        return "index";    
    }   
    
    /*
	 * ��¼��֤
	 */
    @RequestMapping("user/login")
    @ResponseBody
	public ModelAndView login(@ModelAttribute(value = "user") User user,ModelAndView mv,HttpSession session) {
    	String password = null;
    	if(user.getLoginName() != null){
    		if(user.getPassWord() != null){
    			try {
					DesUtils des = new DesUtils("leemenz");
					password = des.encrypt(user.getPassWord());
				} catch (Exception e) {
					e.printStackTrace();
				}
    			
    		}
    		User u = userService.login(user);
    		//���ݲ�Ϊ�գ������벻Ϊ��
    		if(u != null && password != null && password.equals(u.getPassWord())){
    			//��ѯ��Ա��Ӧ�Ĳ˵�����
    			List<SysMenu> menuList = menuService.getMunuByUserId(u.getUid().toString());
    			if(menuList.size() > 0){
    				u.setMenus(menuList);
    			}
    			
    			//GoEasy goEasy = new GoEasy("BC-09d2856605de43419df8c01f1bfd590e");
    			//goEasy.publish("zx_public_zhangs", "�����µĶ�������ע��鿴!");
    			
    			//��������������ݿ��е��������
    			session.setAttribute("user", u);//ת����main����
    			session.setAttribute("loginName", u.getLoginName());
    			mv.addObject("user",u);
                mv.setView(new RedirectView("../public/main.jsp"));
                return mv;
    		}
    	}
        mv.addObject("message","��¼���������������������");
        mv.setViewName("../public/login");
    	return mv;
	}
    /**
     * �˳�ϵͳ
     * @param user
     * @param mv
     * @param session
     * @return
     */
    @RequestMapping("public/logout")
    @ResponseBody
	public String logout(ModelAndView mv,HttpSession session) {
    	session.setAttribute("user", null);
    	session.setAttribute("loginName", null);
//    	session.getAttribute("user");''
    	return "public/login.jsp";
	}
    
    /**
     * ��������
     * @param request
     * @param response
     * @param session
     * @throws Exception
     */
    @RequestMapping("user/updatepwd")
   	public void updatepwd(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Gson gson = new Gson();
		
    	String passwd = request.getParameter("passwd");
    	String newpasswd = request.getParameter("newpasswd");
    	//String newpasswd2 = request.getParameter("newpasswd2");
    	//System.out.println(passwd +"--"+newpasswd+"--"+newpasswd2);

		DesUtils des = new DesUtils("leemenz");
		passwd = des.encrypt(passwd);

    	PrintWriter pw = null;
		if(!StringUtils.isNullOrEmpty(passwd) && session.getAttribute("user") != null){
			User user = (User)session.getAttribute("user");
			if(!passwd.equals(user.getPassWord())){
				//result.append("SUCCESS:false,msg:\"ԭʼ�����������\"");
				map.put("SUCCESS", false);
				map.put("msg", "ԭʼ�����������");
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				pw.write(gson.toJson(map));
				pw.flush();
				pw.close();
				
			}else{
				//��ȡ����
				String newPwd = des.encrypt(newpasswd);
				user.setPassWord(newPwd);
				userService.updatepwd(user);
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
				}
				map.put("SUCCESS", true);
				map.put("msg", "�����޸ĳɹ����������������µ�¼��");
				
				pw.write(gson.toJson(map));
				pw.flush();
				pw.close();
			}
		}
    }
    @RequestMapping("/public/page/user/list")
	public void queryUser(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap, Page page) {
		try {
			String currentPage = request.getParameter("page");
			String pageSize = request.getParameter("rows");

			String userName = request.getParameter("userName") == null ? ""	: request.getParameter("userName");
			String age = request.getParameter("age") == null ? "" : request.getParameter("age");
			Map<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.put("userName", userName);
			queryMap.put("age", age);

			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			response.setContentType("text/html;charset=UTF-8");
			// Thread.sleep(500);
//			List<User> uList = userService.queryUser(page, queryMap);
			User user = new User();
			user.setCurrentPage(Integer.parseInt(currentPage));
			user.setPageSize(Integer.parseInt(pageSize));
			user.setLoginName(userName);
			List<User> uList = userService.queryUser(user);
			int total = userService.queryUserCount(user);
			
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
		}
	}
}
