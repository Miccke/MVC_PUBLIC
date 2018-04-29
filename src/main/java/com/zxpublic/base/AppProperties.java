package com.zxpublic.base;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


public final class AppProperties {
	private final static String APP_CONFIGURE_PATH = "application.properties";

	private static Logger logger = LoggerFactory.getLogger(AppProperties.class);

	/**
	 * 版本
	 */
	private String version;
	/**
	 * 项目名称
	 */
	private String name;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 公司名前缀
	 */
	private String companyPrefix;
	/**
	 * 公司EN前缀
	 */
	private String companyEnprefix;
	/**
	 * 项目标题
	 */
	private String title;
	/**
	 * 项目部署路径
	 */
	private String basePath;
	/**
	 * 项目部署完整路径
	 */
	private String fullPath;

	// 域名
	private String domain;
	// 域名:不包含路径和端口
	private String rawDomain;

	/**
	 * 登录时显示的图片
	 */
	private String loginImage;
	/**
	 * 页面标题图片
	 */
	private String titleImage;

	/**
	 * 项目支持语言的列表
	 */
	private String applicationLangs;

	/**
	 * 是否是开发模式
	 */
	private boolean developeMode = true;

	/**
	 * 是否使用JQuery
	 */
	private boolean jQuery = false;

	/**
	 * 是否发送系统通知
	 */
	private boolean sendNotify = false;

	/**
	 * 是否记录用户访问日志
	 */
	private boolean logAction = true;

	/**
	 * 验证模式APP，SSO
	 */
	private boolean sso = true;

	/**
	 * 启用打包，压缩的js和css
	 */
	private boolean gzip = false;

	private int onlineTimeout = 60;

	/**
	 * Build版本标示
	 */
	private String buildNumber = "000001";

	private String docFolder = "C:/document/src";
	private String swfFolder = "C:/document/swf";

	private String attachFolder = "C:/attach/src";
	private String extendImgPath = "imagex";

	/**
	 * 应用程序URL
	 */
	private String appUrl = "http://localhost:8080/vbpm";

	/**
	 * 搜索引擎服务URL
	 */
	private String solrUrl = "http://localhost:8088/solr/";

	/**
	 * 是否开放搜索模式
	 */
	private boolean searchMode = false;

	public static boolean isSearchMode() {
		if (getInstance() == null) {
			return false;
		}
		return getInstance().searchMode;
	}

	public void setSearchMode(boolean searchMode) {
		this.searchMode = searchMode;
	}

	private boolean buildForms = false;

	private Map<String, String> optionMap = new HashMap<String, String>();

	private static AppProperties instance = null;

	public static AppProperties getInstance() {
		return AppProperties.instance;
	}

	private static void setInstance(AppProperties inst) {
		AppProperties.instance = inst;
	}

	public void initialize() {
		setInstance(this);

		try
		{
			Properties properties = PropertiesLoader.loadAllProperties(APP_CONFIGURE_PATH, "UTF-8");
			
			Map<String, String> options = new HashMap<String, String>(properties.size());

			CollectionUtils.mergePropertiesIntoMap(properties, options);

			this.name = properties.getProperty("application.name", "");
			this.title = properties.getProperty("application.title", "");
			this.basePath = properties.getProperty("application.basepath", "./");
			this.applicationLangs = properties.getProperty("application.languages", "zh_CN,en_US");

			this.loginImage = properties.getProperty("application.loginImage", "");
			this.titleImage = properties.getProperty("application.titleImage", "");

			this.jQuery = properties.getProperty("application.jQuery", "false").equalsIgnoreCase("true");

			// this.sendNotify =
			// properties.getProperty("application.sendNofity",
			// "false").equalsIgnoreCase("true");
			// this.developeMode =
			// properties.getProperty("application.developeMode",
			// "false").equalsIgnoreCase("true");
			// this.setVersion(properties.getProperty("application.version",
			// "n/a"));
			// this.sso = properties.getProperty("application.SSO",

			this.docFolder = properties.getProperty("document.baseFolderSrc", "C:/document/src");
			this.swfFolder = properties.getProperty("document.baseFolderSwf", "C:/document/swf");
			this.attachFolder = properties.getProperty("document.baseFolderAttach", "C:/attach/src");
			this.extendImgPath = properties.getProperty("document.extendImagePath", "imagex");
			this.buildForms = properties.getProperty("application.buildForms","false").equalsIgnoreCase("true");
			this.optionMap = options;
		} catch (Exception ex) {
			this.name = "";
			this.title = "";
			this.basePath = "./";
			this.applicationLangs = "zh_CN,en_US";

			logger.error("加载应用程序配置失败: {1}", ex.getMessage());
		}
	}

	public static String get(String name) {
		return getInstance().optionMap.get(name);
	}

	public static String get(String name, String defaultValue) {
		String value = get(name);
		if (value == null)
			return defaultValue;

		return value;
	}

	public static boolean isDevelopeMode() {
		if (getInstance() == null) {
			return false;
		}
		return getInstance().developeMode;
	}

	public void setDevelopeMode(boolean devMode) {
		this.developeMode = devMode;
	}

	public static boolean isSso() {
		return getInstance().sso;
	}

	public void setSso(boolean sso) {
		this.sso = sso;
	}

	public static boolean isGzip() {
		return getInstance().gzip;
	}

	public void setGzip(boolean gzip) {
		this.gzip = gzip;
	}

	public static String getBuildNumber() {
		return getInstance().buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public static String getVersion() {
		return getInstance().version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static String getName() {
		return getInstance().name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public static String getCompanyName() {
		return getInstance().companyName;
	}

	public void setCompanyPrefix(String companyPrefix) {
		this.companyPrefix = companyPrefix;
	}

	public static String getCompanyPrefix() {
		return getInstance().companyPrefix;
	}

	public void setCompanyEnprefix(String companyEnprefix) {
		this.companyEnprefix = companyEnprefix;
	}

	public static String getCompanyEnprefix() {
		return getInstance().companyEnprefix;
	}

	public static String getTitle() {
		return getInstance().title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the basePath
	 */
	public static String getBasePath() {
		return getInstance().basePath;
	}

	/**
	 * @param basePath
	 *            the basePath to set
	 */
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	/**
	 * @param applicationLangs
	 *            the applicationLangs to set
	 */
	public void setApplicationLangs(String applicationLangs) {
		this.applicationLangs = applicationLangs;
	}

	/**
	 * @return the applicationLangs
	 */
	public static String getApplicationLangs() {
		return getInstance().applicationLangs;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public static String getFullPath() {
		return getInstance().fullPath;
	}

	public static String getRawDomain() {
		return getInstance().rawDomain;
	}

	public static String getDomain() {
		return getInstance().domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
		if (StringUtils.isBlank(domain)) {
			this.rawDomain = "";
		}
		this.rawDomain = domain.toLowerCase();
		if (this.rawDomain.startsWith("http://")) {
			this.rawDomain = this.rawDomain.substring(7);
		}

		int idx = this.rawDomain.indexOf(':');
		if (idx >= 0) {
			this.rawDomain = this.rawDomain.substring(0, idx);
		}
		idx = this.rawDomain.indexOf('/');
		if (idx >= 0) {
			this.rawDomain = this.rawDomain.substring(0, idx);
		}
	}

	public void setLoginImage(String loginImage) {
		this.loginImage = loginImage;
	}

	public static String getLoginImage() {
		return getInstance().loginImage;
	}

	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}

	public static String getTitleImage() {
		return getInstance().titleImage;
	}

	public static boolean useJQuery() {
		return getInstance().jQuery;
	}

	public static boolean isSendNotify() {
		return getInstance().sendNotify;
	}

	public void setSendNotify(boolean sendNotify) {
		this.sendNotify = sendNotify;
	}

	public static boolean isLogAction() {
		return getInstance().logAction;
	}

	public void setLogAction(boolean logAction) {
		this.logAction = logAction;
	}

	public static String getLogoutPath() {
		if (isSso()) {
			return "/j_spring_cas_security_logout";
		} else {
			return "/j_spring_security_logout";
		}
	}

	public static String getDocFolder() {
		return getInstance().docFolder;
	}

	public static String getSwfFolder() {
		return getInstance().swfFolder;
	}

	public static String getAttachFolder() {
		return getInstance().attachFolder;
	}

	public static String getExtendImgPath() {
		return getInstance().extendImgPath;
	}

	public static String getAppUrl() {
		return getInstance().appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public static String getSolrUrl() {
		return getInstance().solrUrl;
	}

	public void setSolrUrl(String solrUrl) {
		this.solrUrl = solrUrl;
	}

	public static boolean isBuildForms() {
		return getInstance().buildForms;
	}

	public void setBuildForms(boolean buildForms) {
		this.buildForms = buildForms;
	}

	public void setOnlineTimeout(int onlineTimeout) {
		this.onlineTimeout = onlineTimeout;
	}

	public static int getOnlineTimeout() {
		return getInstance().onlineTimeout;
	}
}