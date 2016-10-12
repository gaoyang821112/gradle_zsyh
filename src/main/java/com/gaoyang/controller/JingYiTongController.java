package com.gaoyang.controller;

import com.gaoyang.service.ParamService;
import com.gaoyang.util.HttpUtils;
import com.gaoyang.util.MailUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/jingyitong")
public class JingYiTongController {

    @Autowired
    ParamService paramService;

    @RequestMapping(value = "/start")
    @ResponseBody
	public String start() {
        Map<String, String> initParams = paramService.getParamMap();
        StringBuffer sb = new StringBuffer();
        String hosCode = initParams.get("hosCode");
        String hisId = initParams.get("hisId");
//        String wxId = initParams.get("wxId");
        Map<String, String> params = new HashedMap();
        params.put("hosCode", hosCode);
        String[] hisIdArray = hisId.split(",");
        for (int i = 0; i < hisIdArray.length; i++) {
            params.put("hisId", hisIdArray[i]);
            String result = HttpUtils.setJingYiTongRequest(params);
            JSONObject jsonObject = JSONObject.fromObject(result).getJSONObject("data");
            JSONArray dataListObj = jsonObject.getJSONArray("dateList");
            JSONArray jsonArray = (JSONArray) dataListObj.get(0);
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject obj3 = jsonArray.getJSONObject(j);
                String dateStr = obj3.getString("date");
                String typeStr = obj3.getString("type");

                if (typeStr.equals("2")) {
                    sb.append("产科" + (i + 1) + "科室" + dateStr + "有号了");
                }
            }
        }
        if (!"".equals(sb.toString())) {
            try {
                MailUtil.sendMail("有号了", sb.toString());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            sb.append("no");
        }

        return sb.toString();
	}




}