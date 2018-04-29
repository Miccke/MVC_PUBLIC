package com.zxpublic.base;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
/**
 * <p>properties文件工具类，读取配置</p>
 * 
 * @author jason.liu 流程与信息管理部
 * @date 2011-5-20
 * @version 1.0
 * @see 
 */
public final class PropertiesLoader extends PropertiesLoaderUtils {

	public static Properties loadProperties(Resource resource, String encoding) throws IOException {
		Properties props = new Properties();
		fillProperties(props, resource, encoding);
		return props;
	}

	public static void fillProperties(Properties props, Resource resource, String encoding) throws IOException {
		InputStream is = resource.getInputStream();
		try {
			if (encoding == null)
				props.load(is);
			else
				props.load(new InputStreamReader(is, encoding));
		}
		finally {
			is.close();
		}
	}
	
	public static Properties loadAllProperties(String resourceName, String encoding) throws IOException {
		return loadAllProperties(resourceName, null, encoding);
	}

	public static Properties loadAllProperties(String resourceName, ClassLoader classLoader, String encoding) throws IOException {
		Assert.notNull(resourceName, "Resource name must not be null");
		ClassLoader clToUse = classLoader;
		if (clToUse == null) {
			clToUse = ClassUtils.getDefaultClassLoader();
		}
		Properties properties = new Properties();
		Enumeration<URL> urls = clToUse.getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			InputStream is = null;
			try {
				URLConnection con = url.openConnection();
				con.setUseCaches(false);
				is = con.getInputStream();
				if (encoding == null)
					properties.load(is);
				else
					properties.load(new InputStreamReader(is, encoding));
			}
			finally {
				if (is != null) {
					is.close();
				}
			}
		}

		return properties;
	}
}
