package com.zxpublic.util;


import java.io.UnsupportedEncodingException;
/**
 * 
 * @ClassName: UrlUtil 
 * @Description: TODO(urlת�롢����) 
 * @author CW5128 Miccke 
 * @date 2017��8��21�� ����7:33:47 
 * @version V1.0
 */
public class UrlUtil {
    private final static String ENCODE = "utf-8"; 
    /**
     * 
     * @Description: TODO(URL ����) 
     * @param str
     * @return    �趨�ļ� 
     * @return String    �������� 
     * @throws
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 
     * @Description: TODO(URL ת��) 
     * @param str
     * @return    �趨�ļ� 
     * @return String    �������� 
     * @throws
     */
    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 
     * @return void
     * @author lifq
     * @date 2015-3-17 ����04:09:16
     */
    public static void main(String[] args) {
        String str = "�����µķ��񶩵�����ע����գ�������ΪSW201708210010��������������";
        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str));
        
    }

}