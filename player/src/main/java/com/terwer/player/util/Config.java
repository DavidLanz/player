package com.terwer.player.util;

import com.terwer.commons4j.PropertyHelper;
import com.terwer.player.model.SiteConfig;

/**
 * ����������
 * 
 * @author Tangyouwei
 *
 */
public class Config {
	/**
	 * commons4j.properties�����ļ���ַ
	 */
	public static final String propPath = Thread.currentThread()
			.getContextClassLoader().getResource("commons4j.properties")
			.getPath();

	private static SiteConfig siteConfig = null;

	public static SiteConfig getSiteConfig() {
		if (null == siteConfig) {
			siteConfig = new SiteConfig();
		}
		// ��������
		loadSiteConfig();
		return siteConfig;
	}

	/**
	 * ��������
	 */
	private static void loadSiteConfig() {
		siteConfig.setMainUrl(PropertyHelper.getValue(propPath, "mainUrl"));
		siteConfig.setPlayerUrl(PropertyHelper.getValue(propPath, "playerUrl"));
		siteConfig.setSearchUrl(PropertyHelper.getValue(propPath, "searchUrl"));
		siteConfig.setBlogUrl(PropertyHelper.getValue(propPath, "blogUrl"));
		siteConfig.setCkplayerHome(PropertyHelper.getValue(propPath,
				"ckPlayerHome"));
	}
}
