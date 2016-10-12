package com.gaoyang.util;

import com.gaoyang.bean.ShiHuiHttpParams;

import java.io.*;
import java.net.*;
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
			connection.connect();
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
	static String getCookie(String name,String cookieStr){
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
}
