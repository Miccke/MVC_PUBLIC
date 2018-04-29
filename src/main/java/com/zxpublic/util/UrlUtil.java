package com.zxpublic.util;


import java.io.UnsupportedEncodingException;
/**
 * 
 * @ClassName: UrlUtil 
 * @Description: TODO(url转码、解码) 
 * @author CW5128 Miccke 
 * @date 2017年8月21日 下午7:33:47 
 * @version V1.0
 */
public class UrlUtil {
    private final static String ENCODE = "utf-8"; 
    /**
     * 
     * @Description: TODO(URL 解码) 
     * @param str
     * @return    设定文件 
     * @return String    返回类型 
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
     * @Description: TODO(URL 转码) 
     * @param str
     * @return    设定文件 
     * @return String    返回类型 
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
     * @date 2015-3-17 下午04:09:16
     */
    public static void main(String[] args) {
        String str = "您有新的服务订单，请注意查收，订单号为SW201708210010【信伊健康服务】";
        System.out.println(getURLEncoderString(str));
        System.out.println(getURLDecoderString(str));
        
    }

}