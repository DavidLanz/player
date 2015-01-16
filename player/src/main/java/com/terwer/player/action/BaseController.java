package com.terwer.player.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.terwer.player.model.SiteConfig;

/**
 * �����������ڼ���վ������
 * 
 * @author Tangyouwei
 * @version 1.0.0 14-01-16
 */
public class BaseController {
	private SiteConfig siteConfig = null;

	public BaseController() {
		siteConfig = new SiteConfig();
		siteConfig.setDomain("localhost:8080/player");
		siteConfig.setPlayerDomain("localhost:8080/player");
		siteConfig.setSearchDomain("localhost:8080/search");
		siteConfig.setBlogDomain("www.terwer.com");
		siteConfig.setCkplayerHome(siteConfig.getPlayerDomain()+"/plugins/ckplayer6.6");
		System.out.println("���ǻ�����������ҿ�ʼ���죬���ó�ʼ�����...");
	}

	/**
	 * @return the siteconfig
	 */
	public SiteConfig getSiteconfig() {
		return siteConfig;
	}

}
