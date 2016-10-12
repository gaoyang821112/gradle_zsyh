package com.gaoyang.controller;

import com.gaoyang.bean.ShiHuiHttpParams;
import com.gaoyang.util.API_URL;
import com.gaoyang.util.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shihui")
public class ShiHuiController {
	
	@RequestMapping(value = "/start")
	public void start() {
	}
	
	@RequestMapping(value = "/stop")
	public void stop() {
		
	}
	
	private static List<ShiHuiHttpParams> list = new ArrayList<ShiHuiHttpParams>();
	
	public static void main(String[] args) {
		ShiHuiHttpParams shihui1 = new ShiHuiHttpParams("MAuth 0aca9c6114cff195238fa355782a580cb21d089de6b0358727a48ded1dc96817", "169176a6a85b2fc0db2b7cd0b8d412e7c8935433|CF53C010-390D-405B-ACE0-0D182312B9C8", "2563147", "3.9.17-1.7.2-169176a6a85b2fc0db2b7cd0b8d412e7c8935433-iPhone-wxq_AppStore");
		ShiHuiHttpParams shihui2 = new ShiHuiHttpParams("MAuth 20020efdb26ff5fc464a1f15feda4180081262b2a16140576138d5ea83ad2e05", "0313014b1d5aed40412ea78eae6a8478916d6879|F4EC94E2-1CC5-4931-9940-D2318E29E5BB", "2715842", "3.9.17-1.7.2-0313014b1d5aed40412ea78eae6a8478916d6879-iPhone-wxq_AppStore");
		ShiHuiHttpParams shihui3 = new ShiHuiHttpParams("MAuth e6fbb6f26a763176f6357431c1455a203952a88804eb9d24f44285f2b1fd30ed", "0313014b1d5aed40412ea78eae6a8478916d6879|F4EC94E2-1CC5-4931-9940-D2318E29E5BC", "2712079", "3.9.17-1.7.2-0313014b1d5aed40412ea78eae6a8478916d6879-iPhone-wxq_AppStore");
		
		list.add(shihui1);
		list.add(shihui2);
		list.add(shihui3);
		
		for (int i = 0; i < list.size(); i++) {
			boolean isFirst = true;
			String nextCursor = "-1";
			while (Integer.parseInt(nextCursor) > 0 || isFirst) {
				String url = API_URL.SHIHUI.SHIHUI_LIST_URL + "?cursor=" + nextCursor + "&lat=39.93143&gid=2715842&lon=116.4568&count=10";
				String str = HttpUtils.sendGet4ShiHui(url, list.get(i));
				JSONObject obj = JSONObject.fromObject(str);
				JSONObject result = obj.getJSONObject("result");
				nextCursor = result.getString("nextCursor");
				JSONArray welfares = result.getJSONArray("welfares");
				shihuiPlay(welfares, list.get(i).getCommunity_id(), list.get(i));
				isFirst = false;
			}
		}
	}

	private static void shihuiPlay(JSONArray welfares, String community_id, ShiHuiHttpParams shihuiParam) {
		for (int i = 0; i < welfares.size(); i++) {
			JSONObject welfare = welfares.getJSONObject(i);
			String welfare_id = welfare.getString("id");
			String type = welfare.getString("type");
			if ("1".equals(type)) {
				String lottery_url = API_URL.SHIHUI.SHIHUI_LOTTERY_URL;
				Map<String, String> params = new HashMap<String, String>();
				params.put("welfare_id", welfare_id);
				params.put("community_id", community_id);
				for (int j = 0; j < 3; j++) {
					String str1 = HttpUtils.postUrl4ShiHui(lottery_url, params, shihuiParam);
					System.out.println(welfare.getString("name") + str1);
				}
			} else if ("2".equals(type)) {
				String join_url = API_URL.SHIHUI.SHIHUI_JOIN_URL;
				Map<String, String> params = new HashMap<String, String>();
				params.put("welfare_id", welfare_id);
				params.put("community_id", community_id);
				String str1 = HttpUtils.postUrl4ShiHui(join_url, params, shihuiParam);
				System.out.println(welfare.getString("name") + str1);
			}
		}
	}

}