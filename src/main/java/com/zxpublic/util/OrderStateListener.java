package com.zxpublic.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zxpublic.service.OrderInfoService;
import com.zxpublic.vo.OrderInfo;

public class OrderStateListener implements ServletContextListener {
	private MyThread myThread;
	private static OrderInfoService orderInfoService;
	
	public void contextDestroyed(ServletContextEvent e) {
		if (myThread != null && myThread.isInterrupted()) {
			myThread.interrupt();
		}
	}

	public void contextInitialized(ServletContextEvent e) {
		try {
			orderInfoService = WebApplicationContextUtils.getWebApplicationContext(e.getServletContext()).getBean(OrderInfoService.class);
			String str = null;
			if (str == null && myThread == null) {
				myThread = new MyThread();
				myThread.start();
				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/*
	 * ��ʱ��ת��Ϊʱ���
	 */
	public static long dateToStamp(Date ordertime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sDate = sdf.format(ordertime);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(sDate);
		long ts = date.getTime();
		return ts;
	}

	static class MyThread extends Thread {
		public void run() {
			while (true) {
				changState();
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	public static void changState(){
		try {
			List<OrderInfo> oilist = orderInfoService.selectByStateAndSendTime();
			
			for (OrderInfo oi : oilist) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				long startTime = dateToStamp(oi.getOrdertime());
				long nowTime = dateToStamp(new Date());
				long  timeTable = nowTime-startTime;
				if(timeTable >= 10800000){
					List<OrderInfo> orderNumList = orderInfoService.selectByOrderNum(oi.getOrderNum());
					for(OrderInfo orderNum:orderNumList){
						orderNum.setOrderstate(3);
						orderNum.setFinishtime(stampToDate(nowTime+""));
						orderInfoService.updateByOrderNum(orderNum);
						System.out.println("�ж����Զ�������������ˮ��Ϊ�� "+orderNum.getOrderNum());
					}
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 /* 
     * ��ʱ���ת��Ϊʱ��
     */
    public static Date stampToDate(String s){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        return date;
    }
}
