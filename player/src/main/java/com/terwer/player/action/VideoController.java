package com.terwer.player.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.terwer.player.model.CKModel;
import com.terwer.player.model.CKVideo;
import com.terwer.player.model.Video;

@Controller
@RequestMapping("/video")
public class VideoController extends BaseController {

	public VideoController() {
		System.out.println("����VideoController���ҿ�ʼ����...");
	}

	/**
	 * ������Ƶ����vtype����Ƶvid���ckplayer��Ҫ��xml��ʽ
	 * 
	 * @param vtype
	 * @param vid
	 * @return
	 */
	@RequestMapping(value = "ckxml", method = RequestMethod.GET)
	@ResponseBody
	public String ckxml(String vtype, String vid) {
		// public @ResponseBody CKModel ckxml(String vtype,String vid) {
		// //�����ǽ�������f�Ĺ��̣��ǳ��ؼ���
		String f = "<?xml version=\"1.0\" encoding=\"utf-8\"?><player><flashvars>{h->3}{a->bq_MTAyMDc0MTU1_56|gq_MTAyMDc0MTU1_56|cq_MTAyMDc0MTU1_56}{f->http://localhost:8080/video/play.do?url=[$pat1]}</flashvars><video><file><![CDATA[http://f5.r.56.com/f5.c127.56.com/flvdownload/24/11/sc_138632158682hd_clear.flv?v=1&t=0MqqDxdLRSIQt_DmnFheXg&r=27194&e=1421670920&tt=2765&sz=191745133&vid=102074155]]></file></video></player>";
		return f;
		// CKModel ckModel=new CKModel();
		// ckModel.setFlashvars("{h->3}{a->bq_MTAyMDc0MTU1_56|gq_MTAyMDc0MTU1_56|cq_MTAyMDc0MTU1_56}{f->http://www.terwer.com/tools/player/ckplayer/video.php?url=[$pat1]}");
		// CKVideo video=new CKVideo();
		// ArrayList<String> files=new ArrayList<String>();
		// files.add("<![CDATA[http://f5.r.56.com/f5.c127.56.com/flvdownload/24/11/sc_138632158682hd_clear.flv?v=1&t=YfXNI977OZLyXFJZkFFsBA&r=73681&e=1421510480&tt=2765&sz=191745133&vid=102074155]]>");
		// video.setFile(files);
		// ckModel.setVideo(video);
		// return ckModel;
	}

	/**
	 * �������������xml
	 * 
	 * @return
	 */
	@RequestMapping(value = "shareXml", method = RequestMethod.GET)
	public String shareXml(Model model,HttpServletResponse response)  {
	    response.setContentType("text/xml");
	    response.setCharacterEncoding("UTF-8");
		model.addAttribute("siteConfig", super.getSiteConfig());
		return "video/share";
	}

	/**
	 * ��Ƶ����
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "play", method = RequestMethod.GET)
	public String play(Model model, String vtype, String vid) {
		// ��ȡVideo����
		Video video = new Video();// ���Ӧ��ͨ��vtype��vid��ȡ
		video.setVideoTitle("������Ƶ����");
		video.setVideoUrl("http://video.test");
		// f���ǳ���Ҫ���˲����ṩckplayer�ɲ��ŵ����ݣ�
		video.setF(super.getSiteConfig().getPlayerUrl()
				+ "/video/ckxml.do?vtype=" + vtype + "&vid=" + vid);
		//a���ǳ���Ҫ�����f��s=2ʱʹ��xml��
		video.setA("cq_v_MTAyMDc0MTU1_56");
		//s���ǳ���Ҫ�����f��s=2ʱʹ��xml��
		video.setS("2");
		// ���ݲ�����ҳ��
		model.addAttribute("video", video);
		model.addAttribute("siteConfig", super.getSiteConfig());
		return "video/play";
	}

	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String search(Model model) {
		// public String search(ModelMap model) {
		// �Ѳ���ֵ�ŵ�request������ȥ
		model.addAttribute("keyword", "½С���뻨��¥");
		// model.addAttribute("args", new SearchArgs());
		// return "/home/search";
		return "video/search";
	}

	/**
	 * ��Ƶ�����Y��
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "result", method = RequestMethod.GET)
	public String result(Model model) {
		model.addAttribute("vtype", "youku");
		model.addAttribute("vid", "abcdefg");
		return "result";
	}
}
