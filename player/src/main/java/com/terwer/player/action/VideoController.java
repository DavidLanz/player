package com.terwer.player.action;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terwer.player.model.CKModel;

@Controller
@RequestMapping("/video")
public class VideoController {
	
	/**
	 * ������Ƶ���ͺ�id���ckplayer��Ҫ��xml��ʽ
	 * @param model
	 * @return
	 */
	@XmlElement
	@RequestMapping(value = "ckxml", method = RequestMethod.GET)
	public  @ResponseBody Object ckxml(Model model) {
		CKModel obj=new CKModel();
		obj.setVid(1);
		obj.setVxml("aaa");
		//Object obj=new Object(){};
		return obj;
	}
	
	/**
	 * ��Ƶ����
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "play", method = RequestMethod.GET)
	public String play(Model model) {
		model.addAttribute("vid", "Video is playing");
		return "play";
	}
}
