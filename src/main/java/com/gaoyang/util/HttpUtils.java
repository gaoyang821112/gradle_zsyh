package com.gaoyang.util;

import com.gaoyang.bean.DianPingUser;
import com.gaoyang.bean.ShiHuiHttpParams;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtils {
	public static String postUrl4ZSYH(String requestUrl,
			Map<String, String> paramMap) {
		StringBuffer strbuffer = new StringBuffer();
		String paramUrl = "";
		if ((paramMap != null) && (paramMap.size() > 0)) {
			for (Map.Entry entry : paramMap.entrySet()) {
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				strbuffer.append(key);
				strbuffer.append("=");
				strbuffer.append(value);
				strbuffer.append("&");

			}
			paramUrl = strbuffer.toString();
			paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
		}

		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer("");
		try {
			URL url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "*/*");
			connection.connect();

			DataOutputStream writer = new DataOutputStream(
					connection.getOutputStream());

			writer.writeBytes(paramUrl);
			writer.flush();
			writer.close();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes());
				sb.append(lines);
			}

			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}

	public static String postUrl4BaoJie(String requestUrl,
			Map<String, String> paramMap, String openId, String cookie) {
		StringBuffer strbuffer = new StringBuffer();
		String paramUrl = "";
		if ((paramMap != null) && (paramMap.size() > 0)) {
			for (Map.Entry entry : paramMap.entrySet()) {
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				strbuffer.append(key);
				strbuffer.append("=");
				strbuffer.append(value);
				strbuffer.append("&");
			}
			paramUrl = strbuffer.toString();
			paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
		}

		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer("");
		try {
			URL url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Accept-Language", "zh-cn");
			connection.setRequestProperty("user-agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_3 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13E233 MicroMessenger/6.3.15 NetType/WIFI Language/zh_CN");
			connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			connection.setRequestProperty("Referer", "http://shenghuojia.campaign.socialjia.com/index.php?a=NineLife&m=index&brandid=1&apiKey=8a674fcd013ccbc5e0fc206d3b1771c4&timestamp=1459402308&sig=b79fe65fba9af44215cbcc3710decc8c&openid=" + openId);
			connection.setRequestProperty("Origin", "http://shenghuojia.campaign.socialjia.com");
			connection.setRequestProperty("Cookie",
					"_ga=GA1.4.1779946440.1459393425; " +
					"_gat=1; " +
					"sessionid=1459402307057s2; " +
					"PHPSESSID=" + cookie + "; " +
					"_ga=GA1.2.1779946440.1459393425; " +
					"_gat_brand=1; " +
					"_gat_global=1");
			connection.connect();
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(paramUrl);
			writer.flush();
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes());
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}
	
	public static String postUrl4ShiHui(String requestUrl, Map<String, String> paramMap, ShiHuiHttpParams param) {
		StringBuffer strbuffer = new StringBuffer();
		String paramUrl = "";
		if ((paramMap != null) && (paramMap.size() > 0)) {
			for (Map.Entry entry : paramMap.entrySet()) {
				String key = String.valueOf(entry.getKey());
				String value = String.valueOf(entry.getValue());
				strbuffer.append(key);
				strbuffer.append("=");
				strbuffer.append(value);
				strbuffer.append("&");
			}
			paramUrl = strbuffer.toString();
			paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
		}

		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer("");
		try {
			URL url = new URL(requestUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("user-agent", "实惠 1.7.2 (iPhone; iPhone OS 9.2; zh_CN)");
			connection.setRequestProperty("Authorization", param.getAuthorization());
			connection.setRequestProperty("ndeviceid", param.getNdeviceid());
			connection.setRequestProperty("X-Client-ID", "1-5-5beff1fa16b7ef434ac4e46baee420d4-ios");
			connection.setRequestProperty("X-WVersion", param.getVersion());	
			connection.connect();
			DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
			writer.writeBytes(paramUrl);
			writer.flush();
			writer.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			String lines;
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes());
				sb.append(lines);
			}
			reader.close();
			connection.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return sb.toString();
	}

	public static String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Cookie", "Cookie:_hc.v=\"\\\"2000748c-0f57-47e5-b980-dc70e57ab20d.1472525902\\\"\"; checkInCloseState=ignored; likeTips=ignored; __utma=1.1676118817.1472525940.1476338775.1476369631.13; __utmc=1; __utmz=1.1476200547.11.2.utmcsr=dianping.com|utmccn=(referral)|utmcmd=referral|utmcct=/beijing; PHOENIX_ID=0a016717-157be90017f-2397ba; dper=d9206bcb716530924357c33d251099b3f950c04555d7c819adb41b1a5820d3a4; ll=7fd06e815b796be3df069dec7836c3df; ua=%E6%96%B0%E8%8B%B1%E6%B5%8B%E8%AF%95; ctu=6c46d826e05ec92997f124e79d03bfc4e118cea3b972258ad1a4ba2b01869a18; uamo=18518982648; isChecked=checked; JSESSIONID=713ED5B8A3E31D0E7F6552EA96EF8280; aburl=1; cy=2; cye=beijing");
			connection.connect();
			Map map = connection.getHeaderFields();
//			for (Object key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendTaobaokeGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty(":authority", "detail.tmall.com");
			connection.setRequestProperty(":method", "GET");
			connection.setRequestProperty(":path", url);
			connection.setRequestProperty(":scheme", "https");
			connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.setRequestProperty("accept-encoding", "deflate, sdch, br");
			connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("cache-control", "max-age=0");
			connection.setRequestProperty("cookie", "thw=cn; miid=875413649462218552; uc2=wuf=https%3A%2F%2Ftrade.tmall.com%2Fdetail%2ForderDetail.htm%3Fspm%3Da220l.12.0.0.atnF74%26null%3Dnull%26bizOrderId%3D2617231387716326; _uab_collina=148924472113707239909363; v=0; ctoken=vfwVZcYM1kp0tu5waeVLiceland; uc3=sg2=VFPrdW8zoyGQ3KyjElSjWTC3LvsYop%2BrezdEhfx%2BNoQ%3D&nk2=tYqFc05l1rsSJzIwyVU%3D&id2=VAStVnLdLZMV&vt3=F8dARVWNUnzfXFmAaJM%3D&lg2=UtASsssmOIJ0bQ%3D%3D; existShop=MTQ4OTQ3Mzg0NA%3D%3D; uss=UtAEEYwJEpUbq1m4EhL1zenU1EuZRmYa%2BiyeUNqY0rbshiXszyM9bKWUZQ%3D%3D; lgc=%5Cu81F3%5Cu7231%5Cu53D1%5Cu5982%5Cu96EA0204; tracknick=%5Cu81F3%5Cu7231%5Cu53D1%5Cu5982%5Cu96EA0204; cookie2=4a6bc6a76997616f92dad083949325b7; sg=485; mt=np=&ci=0_1; cookie1=VAYq3MRGHGDP5x1T8NO7TJdAd5pYsi720ritNXzDczI%3D; unb=757232148; skt=bad2079c2ef6ca0f; t=cc58407bf4e4e0c33218bf3c4d039458; _cc_=U%2BGCWk%2F7og%3D%3D; tg=0; _l_g_=Ug%3D%3D; _nk_=%5Cu81F3%5Cu7231%5Cu53D1%5Cu5982%5Cu96EA0204; cookie17=VAStVnLdLZMV; linezing_session=pLDIhREdo7wsNtwV1A35ngMt_1489473878511TuGG_1; _tb_token_=ebb5ee54b6301; uc1=cookie14=UoW%2FVObws7d28g%3D%3D&lng=zh_CN&cookie16=VFC%2FuZ9az08KUQ56dCrZDlbNdA%3D%3D&existShop=false&cookie21=UtASsssme%2BBq&tag=2&cookie15=U%2BGCWk%2F75gdr5Q%3D%3D&pas=0; cna=bLxGETrQ7w4CAX0jM6IPFKhw; _umdata=0823A424438F76ABB0E6774F9F8A8DCB971FFE887666C70C5B2EEA7F1FE2DE37EE97D7A5A6C1610FCD43AD3E795C914C4D042EC9DFA2C6DFC8268D19426E80C3; l=AkxMGsYfWqpBSW7Ox47QoO8WnKB-hfAv; isg=AnR0o6E5kGAvVgQp2zGsuRWPRTT_tpg3gNy9CA7VAP-CeRTDNl1oxyq7jwZb");
//			connection.setRequestProperty("refer", "https://s.click.taobao.com/t_js?tu=https%3A%2F%2Fs.click.taobao.com%2Ft%3Fspm%3Da311n.8189758%2Fa.90200000.1.432ZJD%26e%3Dm%253D2%2526s%253DFALaBssK1t5w4vFB6t2Z2ueEDrYVVa64LKpWJ%252Bin0XLjf2vlNIV67qmQRxWEeB19Nq%252BDna%252F8eQepSO1rEcg0b8LN6jK7NHy78hWRy4bCQTYQd0cc4xbQVFKrHkWq9YOLwd3%252F8TPoKJgwqccMEwZVKxKihrUHtpJuYM7FHiwA1nbNkn9R3PC9OI1GG7Jaai63zQG%252BCWQ69P1YdC0Lyzdv9cYOae24fhW0%26pvid%3D11_58.31.14.111_1369_1489422947754%26ref%3Dhttps%253A%252F%252Fuland.taobao.com%252Fcoupon%252Fedetail%253Fe%253DGyIHiUeO40oN%25252BoQUE6FNzBenlb4YusJVQRgq5ELCLwZxZ778529PF%25252FkV8Ovtq%25252BCjRPai36SzjGQYGBU%25252BUYnml0YUejVMpzGc7sRUcQe1PUdw8qc1XLan0YgD8TGIivUFwexwE9iPmnf9jfeG5Bp2avNVXe6BDqYX%2526pid%253Dmm_120456803_22352448_74180429%2526af%253D1%26et%3DcEheJbOZekpD2AX%252Fie57NyH3gjAHuotM");
			connection.setRequestProperty("accept-encoding", "deflate, sdch, br");
			connection.setRequestProperty("upgrade-insecure-requests", "1");
			connection.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
			connection.connect();
			Map map = connection.getHeaderFields();
//			for (Object key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendGet4ShiHui(String url, ShiHuiHttpParams param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("user-agent", "1.7.2 (iPhone; iPhone OS 9.2; zh_CN)");
			connection.setRequestProperty("Authorization", param.getAuthorization());
			connection.setRequestProperty("ndeviceid", param.getNdeviceid());
			connection.setRequestProperty("X-Client-ID", "1-5-5beff1fa16b7ef434ac4e46baee420d4-ios");
			connection.setRequestProperty("X-WVersion", param.getVersion());
			connection.setRequestProperty("X-Timestamp", "1453168432");	
			connection.setRequestProperty("X-Sign", "c6d15872dd1cf88c55f1f096dd2dba2fe4efa4b3");	
			Map map = connection.getHeaderFields();
//			for (Object key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String sendGetCookie(String url,String name) {
		String result = "";
		URLConnection connection = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			connection = realUrl.openConnection();
			connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Host","shenghuojia.campaign.socialjia.com");
			connection.setRequestProperty("Pragma", "no-cache");
			connection.setRequestProperty("Proxy-Connection", "keep-alive");
//			connection.setRequestProperty("Referer","http://shenghuojia.campaign.socialjia.com/index.php?a=ScoreExchange&m=index&brandid=1&apiKey=8a674fcd013ccbc5e0fc206d3b1771c4&timestamp=1451443756&sig=8e4b7a7e3877904d8ac7a89550a8c885&openid=ocJOVjkgTgRH_bUjjqD-6Lg6bX6c");
			connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; U; Android 5.0.1; zh-cn; MX4 Pro Build/LRX22C) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025488 Mobile Safari/533.1 MicroMessenger/6.3.8.50_r251a77a.680 NetType/WIFI Language/zh_CN");
			connection.connect();
			String set_cookie = connection.getHeaderField("Set-Cookie");
			return getCookie("PHPSESSID", set_cookie);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(connection != null){
				
			}
		}
		return result;
	}
	public static String sendGet(String url,String cookie) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);

			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
			connection.setRequestProperty("Cache-Control", "no-cache");
			connection.setRequestProperty("Cookie",cookie);
			connection.setRequestProperty("Host","shenghuojia.campaign.socialjia.com");
			connection.setRequestProperty("Pragma", "no-cache");
			connection.setRequestProperty("Proxy-Connection", "keep-alive");
//			connection.setRequestProperty("Referer","http://shenghuojia.campaign.socialjia.com/index.php?a=ScoreExchange&m=index&brandid=1&apiKey=8a674fcd013ccbc5e0fc206d3b1771c4&timestamp=1451443756&sig=8e4b7a7e3877904d8ac7a89550a8c885&openid=ocJOVjkgTgRH_bUjjqD-6Lg6bX6c");
			connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; U; Android 5.0.1; zh-cn; MX4 Pro Build/LRX22C) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025488 Mobile Safari/533.1 MicroMessenger/6.3.8.50_r251a77a.680 NetType/WIFI Language/zh_CN");
			connection.connect();
			String set_cookie = connection.getHeaderField("Set-Cookie");
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	public static String getCookie(String name,String cookieStr){
			String cookie = "";
		if(cookieStr != null && !"".equals(cookieStr)){
			cookieStr = cookieStr.replaceAll("^\\s*$", "");
			String[] cookies = cookieStr.split(";");
			for(String c : cookies){
				String key = c.split("=")[0];
				String value = c.split("=")[1];
				if(name.equals(key)){
					return value;
				}
			}
		}
		return cookie;
	}

	public static String setJingYiTongRequest(Map<String, String> paramMap) {
		String paramUrl = "";
		StringBuffer strbuffer = new StringBuffer();
		String result = "";
		BufferedReader in = null;
//		if ((paramMap != null) && (paramMap.size() > 0)) {
//			for (Map.Entry entry : paramMap.entrySet()) {
//				String key = String.valueOf(entry.getKey());
//				String value = String.valueOf(entry.getValue());
//				strbuffer.append(key);
//				strbuffer.append("=");
//				strbuffer.append(value);
//				strbuffer.append("&");
//			}
//			paramUrl = strbuffer.toString();
//			paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
//		}
		try {
//			String url = "http://m.benmu-health.com/bjmc/api/Date/getNewDate?" + paramUrl;
			String url = "https://m.benmu-health.com/bjmc/api/Date/getNewDate?" + "hisId=" + paramMap.get("hisId") + "&hisPid=2&oneName=%25E5%25A4%2596%25E7%25A7%2591&deptname=%25E8%2584%258A%25E6%259F%25B1%25E5%25A4%2596%25E7%25A7%2591&hosCode=" + paramMap.get("hosCode") + "&hosName=%25E5%258C%2597%25E4%25BA%25AC%25E4%25B8%2596%25E7%25BA%25AA%25E5%259D%259B%25E5%258C%25BB%25E9%2599%25A2&ref=1";
			System.out.println(url);
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);

			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Host","m.benmu-health.com");
			connection.setRequestProperty("connection","keep-alive");
			connection.setRequestProperty("content-length","264");
			connection.setRequestProperty("Accept","application/json, text/javascript, */*; q=0.01");
			connection.setRequestProperty("origin","m.benmu-health.com");
			connection.setRequestProperty("x-requested-with","XMLHttpRequest");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; MX4 Pro Build/LMY48W) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036555 Safari/537.36 MicroMessenger/6.3.22.821 NetType/WIFI Language/zh_CN");
			connection.setRequestProperty("content-type","application/x-www-form-urlencoded; charset=UTF-8");
			connection.setRequestProperty("referer", "https://m.benmu-health.com/service/getResource.html?hisId=JZWK&hisPid=2&oneName=%E5%A4%96%E7%A7%91&deptname=%E8%84%8A%E6%9F%B1%E5%A4%96%E7%A7%91&hosCode=H110481&hosName=%E5%8C%97%E4%BA%AC%E4%B8%96%E7%BA%AA%E5%9D%9B%E5%8C%BB%E9%99%A2&ref=1");
			connection.setRequestProperty("accept-language","zh-CN,en-US;q=0.8");
			connection.setRequestProperty("Cookie", "_ucp=2-dRL-Fgn6KZdeirl3EEvSUTlhNBfY0F9mLvP7J9gAk7di8GgWDRI4OMHkxBi8kOZfrwLg..; _attention=1");

			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	public static String setDianPingLoginRequest(String address, Map<String, String> paramMap) {
		String paramUrl = "";
		StringBuffer strbuffer = new StringBuffer();
		String result = "";
		BufferedReader in = null;
		try {

			if ((paramMap != null) && (paramMap.size() > 0)) {
				for (Map.Entry entry : paramMap.entrySet()) {
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					strbuffer.append(key);
					strbuffer.append("=");
					strbuffer.append(value);
					strbuffer.append("&");
				}
				paramUrl = strbuffer.toString();
				paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
			}
			String url = address + "?" + paramUrl;
			URL realUrl = new URL(url);

			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Accept","*/*");
			connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
			connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setRequestProperty("Cookie", "PHOENIX_ID=0a01783d-1592a984866-5694e8; _hc.v=\"970e51a7-8d29-4c94-b8d1-fb74b0a689aa.1482481011\"");
			connection.setRequestProperty("Host","www.dianping.com");
			connection.setRequestProperty("Referer","https://mlogin.dianping.com/login/password?redir=https%3A%2F%2Fm.dianping.com%2Fmy");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");

			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	public static List<String> returnCookies(String address, Map<String, String> paramMap) {
		String paramUrl = "";
		StringBuffer strbuffer = new StringBuffer();
		List<String> result = new ArrayList<>();
		BufferedReader in = null;
		try {

			if ((paramMap != null) && (paramMap.size() > 0)) {
				for (Map.Entry entry : paramMap.entrySet()) {
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					strbuffer.append(key);
					strbuffer.append("=");
					strbuffer.append(value);
					strbuffer.append("&");
				}
				paramUrl = strbuffer.toString();
				paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
			}
			String url = address + "?" + paramUrl;
			URL realUrl = new URL(url);

			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Accept","*/*");
			connection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
			connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setRequestProperty("Cookie", "PHOENIX_ID=0a01783d-1592a984866-5694e8; _hc.v=\"970e51a7-8d29-4c94-b8d1-fb74b0a689aa.1482481011\"");
			connection.setRequestProperty("Host","www.dianping.com");
			connection.setRequestProperty("Referer","https://mlogin.dianping.com/login/password?redir=https%3A%2F%2Fm.dianping.com%2Fmy");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");

			connection.connect();
			Map<String, List<String>> cookies = connection.getHeaderFields();
			result = cookies.get("Set-Cookie");

		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


	public static String setDianPingRequest(Map<String, String> paramMap, DianPingUser user) {
		String paramUrl = "";
		StringBuffer strbuffer = new StringBuffer();
		String result = "";
		BufferedReader in = null;
		try {

			if ((paramMap != null) && (paramMap.size() > 0)) {
				for (Map.Entry entry : paramMap.entrySet()) {
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					strbuffer.append(key);
					strbuffer.append("=");
					strbuffer.append(value);
					strbuffer.append("&");
				}
				paramUrl = strbuffer.toString();
				paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
			}
			String url = "http://s.dianping.com/ajax/json/activity/offline/saveApplyInfo?" + paramUrl;
			URL realUrl = new URL(url);

			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Accept","application/json, text/javascript");
			connection.setRequestProperty("Accept-Encoding","gzip, deflate");
			connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setRequestProperty("Content-Length","251");
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8;");
			connection.setRequestProperty("Host","s.dianping.com");
			connection.setRequestProperty("Origin","http://s.dianping.com");
			connection.setRequestProperty("Referer","http://s.dianping.com/event/" + paramMap.get("offlineActivityId"));
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
			connection.setRequestProperty("X-Request","JSON");
			connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
			connection.setRequestProperty("Cookie", "_hc.v=\"\\\"2000748c-0f57-47e5-b980-dc70e57ab20d.1472525902\\\"\"; dper=" + user.getDper() + "; ua=" + user.getUa() + "; checkInCloseState=ignored; likeTips=ignored; __utma=1.1676118817.1472525940.1476200547.1476338775.12; __utmc=1; __utmz=1.1476200547.11.2.utmcsr=dianping.com|utmccn=(referral)|utmcmd=referral|utmcct=/beijing; ll=7fd06e815b796be3df069dec7836c3df; PHOENIX_ID=0a0102f6-157bca62826-e8532; isChecked=checked; JSESSIONID=2BE2C723CFE01082C220EE2A22D06E62; aburl=1; cy=2; cye=beijing");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}


	public static String setXiaobawangRequest(Map<String, String> paramMap, DianPingUser user) {
		String paramUrl = "";
		StringBuffer strbuffer = new StringBuffer();
		String result = "";
		BufferedReader in = null;
		try {

			if ((paramMap != null) && (paramMap.size() > 0)) {
				for (Map.Entry entry : paramMap.entrySet()) {
					String key = String.valueOf(entry.getKey());
					String value = String.valueOf(entry.getValue());
					strbuffer.append(key);
					strbuffer.append("=");
					strbuffer.append(value);
					strbuffer.append("&");
				}
				paramUrl = strbuffer.toString();
				paramUrl = paramUrl.substring(0, paramUrl.length() - 1);
			}
			String url = "https://act.dianping.com/customized/citic/lottery?" + paramUrl;
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("Host","act.dianping.com");
			connection.setRequestProperty("Accept","*/*");
			connection.setRequestProperty("Connection","keep-alive");
			connection.setRequestProperty("Cookie", "ctu=" + user.getCtu() + "; dper=" + user.getDper() + "; ll=7fd06e815b796be3df069dec7836c3df; ua=" + user.getUa() + "; PHOENIX_ID=0a030e6b-15ada20d047-405de2b; m_flash2=1; pvhistory=\\\"\"6L+U5ZuePjo8L2dldGxvY2FsY2l0eWlkP2xhdD0zOS45MzAwMjAwMjE3NzQyMSZsbmc9MTE2LjQ1MDQ2MDE1OTE0NjkmY2FsbGJhY2s9V2hlcmVBbUkxMTQ4OTcxODI2NzI5OT46PDE0ODk3MTgyNjc5NzhdX1s=\\\"\"; geoType=wgs84; locallat=39.93002002177421; locallng=116.4504601591469; _hc.v=75c75ba9-9c80-653c-e865-e26473d60ff8.1489718266");
			connection.setRequestProperty("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Mobile/14D27 DKKJ/4.0.3");
			connection.setRequestProperty("Referer","https://evt.dianping.com/midas/1activities/a7037Ie879zZAlYYQ/index.html");
			connection.setRequestProperty("Accept-Encoding","gzip, deflate");
			connection.setRequestProperty("Content-Type","application/javascript; charset=utf-8");


//			connection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8");
//			connection.setRequestProperty("Content-Length","251");
//			connection.setRequestProperty("Origin","http://s.dianping.com");
//			connection.setRequestProperty("X-Request","JSON");
//			connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result = result + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}
}
