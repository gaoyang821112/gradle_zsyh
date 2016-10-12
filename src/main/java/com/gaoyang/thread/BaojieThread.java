package com.gaoyang.thread;

import com.gaoyang.controller.BaoJieController;
import com.gaoyang.service.ParamService;
import com.gaoyang.util.API_URL;
import com.gaoyang.util.HttpUtils;
import com.gaoyang.util.UnicodeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaojieThread implements Runnable {

	private String person = "";

	private int num = 1000;
	
	private String openId;

	private String piaoId;

	public BaojieThread(String openId, String person, String piaoId) {
		this.openId = openId;
		this.person = person;
		this.piaoId = piaoId;
	}

	public void run() {
		for (int i = 0; i < num; i++) {
			if (BaoJieController.switchActivity) {
				String requestUrl1 = API_URL.BAOJIE.BAOJIE_9_LIST_URL1 + openId;
				String cookie = HttpUtils.sendGetCookie(requestUrl1,"PHPSESSID");
				String requestUrl2 = API_URL.BAOJIE.BAOJIE_9_LIST_URL2;
				String cookieStr = "PHPSESSID=" + cookie + ";sessionid=" + cookie;
				String str2 = HttpUtils.sendGet(requestUrl2,cookieStr);
				String tokenId = str2.substring(str2.indexOf("token=") + 6, (str2.indexOf("token=") + 38));
				String requestUrl3 = API_URL.BAOJIE.BAOJIE_9_LIST_URL3 + tokenId;
				Map<String, String> map = new HashMap();
//				map.put("openid", openId);
				map.put("id", piaoId);
				map.put("city", "北京市");
				String str = UnicodeUtil.convertUnicode(HttpUtils.postUrl4BaoJie(requestUrl3, map, openId, cookie));

				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				System.out.println(sdf.format(date) + " threadName=" + Thread.currentThread().getName() + " thread-" + person + ":" + str);
			}
			else {
				System.out.println("threadName=" + Thread.currentThread().getName() + "终止");
				break;
			}
		}
	}
}