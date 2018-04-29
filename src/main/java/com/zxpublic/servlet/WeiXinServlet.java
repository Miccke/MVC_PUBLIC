package com.zxpublic.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxpublic.util.Decript;

/**
 * Servlet implementation class WeiXinServlet
 */
public class WeiXinServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//token
	private final String token = "xyjkfwzzzhongtong";  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException { 
		System.out.println("��ʼǩ��У��"); 
		String signature = request.getParameter("signature"); 
		String timestamp = request.getParameter("timestamp"); 
		String nonce = request.getParameter("nonce"); 
		String echostr = request.getParameter("echostr");   
		ArrayList<String> array = new ArrayList<String>(); 
		array.add(signature); array.add(timestamp); array.add(nonce);   
		//���� 
		String sortString = sort(token, timestamp, nonce); 
		//���� 
		String mytoken = Decript.SHA1(sortString); 
		//У��ǩ�� 
		if (mytoken != null && mytoken != "" && mytoken.equals(signature)) { 
			System.out.println("ǩ��У��ͨ����"); response.getWriter().println(echostr); //�������ɹ����echostr��΢�ŷ��������յ���������Ż�ȷ�ϼ�����ɡ� 
			} else { 
				System.out.println("ǩ��У��ʧ�ܡ�"); 
				}}      
	/** * ���򷽷� 
	 * * @param token
	 *  * @param timestamp 
	 *  * @param nonce 
	 *  * @return */
	public static String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce }; 
		Arrays.sort(strArray);   
		StringBuilder sbuilder = new StringBuilder(); 
		for (String str : strArray) { 
			sbuilder.append(str); 
			}   
		return sbuilder.toString();
		}
	
	}
