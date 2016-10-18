package com.gaoyang.controller;

import com.gaoyang.bean.DianPingUser;
import com.gaoyang.service.ParamService;
import com.gaoyang.service.UserService;
import com.gaoyang.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dianping")
public class DianPingController {

    @Autowired
    ParamService paramService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/start")
    @ResponseBody
	public String start() {
        String appendResult = "";
        //获取点评用户id
        List<DianPingUser> userList = userService.getDianPingList();
        //循环点评事件页面
        List<String> typeList = new ArrayList();
        typeList.add("1");
        try {
            for (String type : typeList) {
                String pageHtml = getAllPage(1, type);
                Document doc = Jsoup.parse(pageHtml);
                if (doc != null) {
                    Elements monad = doc.select("li.monad-default");
                    for (Element pic : monad) {
                        Element a = pic.child(0).child(0);
                        String href = a.attr("href");
                        String title = a.attr("title");
                        String id = href.substring(7, href.length());

                        for (int j = 0; j < userList.size(); j++) {
                            DianPingUser user = userList.get(j);
                            Map<String, String> paramMap = new HashedMap();
                            paramMap.put("offlineActivityId", id);
                            paramMap.put("phoneNo", user.getTel());
                            paramMap.put("shippingAddress", "");
                            paramMap.put("extraCount", "");
                            paramMap.put("birthdayStr", "");
                            paramMap.put("email", "");
                            paramMap.put("marryDayStr", "");
                            paramMap.put("babyBirths", "2014-05-05");
                            paramMap.put("pregnant", "");
                            paramMap.put("marryStatus", "0");
                            paramMap.put("comboId", "1");
                            paramMap.put("branchId", "");
                            paramMap.put("usePassCard", "");
                            paramMap.put("passCardNo", "");
                            paramMap.put("extInfo1", "愿意");
                            paramMap.put("isShareSina", "true");
                            paramMap.put("isShareQQ", "true");
                            String result = HttpUtils.setDianPingRequest(paramMap, user);
                            System.out.println(user.getUserName() + "报名：" + title + " 结果：" + result);
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return appendResult;
	}

	private String getAllPage(int pageNum, String type) {
        String result = HttpUtils.sendGet("http://s.dianping.com/ajax/json/activity/offline/moreActivityList?activityIds=&page=" + pageNum + "&typeId=" + type + "&activityStatus=0&modeId=0&passCount=0");
        JSONObject jsonObject = JSONObject.fromObject(result);
        boolean finished = jsonObject.getBoolean("finished");
        String page = jsonObject.getJSONObject("msg").getString("html");
        if (!finished) {
            pageNum++;
            page += getAllPage(pageNum, type);
        }
        return page;
    }
}