package com.zxpublic.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumUtil {

    private static final String SERIAL_NUMBER = "SW2017082100001"; // ��ˮ�Ÿ�ʽ
    private static OrderNumUtil orderNumUtil = null;
 
    private OrderNumUtil() {
    }
 
    /**
     * ȡ��OrderNumUtil�ĵ���ʵ��
     * @return
     */
    public static OrderNumUtil getInstance() {
        if (orderNumUtil == null) {
            synchronized (OrderNumUtil.class) {
                if (orderNumUtil == null) {
                    orderNumUtil = new OrderNumUtil();
                }
            }
        }
        return orderNumUtil;
    }
 
    /**
     * ������һ�����
     */
    public String generaterNextNumber(String sno) {
        String id = null;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id = formatter.format(date) + "00001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("00000");
            id = formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(11, 15)));
        }
        return id;
    }
}
