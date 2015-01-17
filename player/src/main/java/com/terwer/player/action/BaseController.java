package com.terwer.player.action;

import com.terwer.player.model.SiteConfig;
import com.terwer.player.util.Config;

/**
 * �����������ڼ���վ������
 * 
 * @author Tangyouwei
 * @version 1.0.0 14-01-16
 */
public class BaseController {

	private SiteConfig siteConfig = null;

	public BaseController() {
		siteConfig = Config.getSiteConfig();
		System.out.println("���ǻ�����������ҿ�ʼ���죬���ó�ʼ�����...");
	}

	/**
	 * @return the siteConfig
	 */
	public SiteConfig getSiteConfig() {
		return siteConfig;
	}
}
