package com.gaoyang.controller;

import com.gaoyang.bean.DianPingUser;
import com.gaoyang.bean.LocalResponse;
import com.gaoyang.bean.Product1;
import com.gaoyang.bean.User;
import com.gaoyang.service.ParamService;
import com.gaoyang.service.UserService;
import com.gaoyang.thread.BankThread;
import com.gaoyang.util.FileUtil;
import com.gaoyang.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Controller
@RequestMapping("/dianping")
public class DianPingController {

    @Autowired
    ParamService paramService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
	public void login() {
        //获取点评用户id
        List<DianPingUser> userList = userService.getDianPingList();
        for (int j = 0; j < userList.size(); j++) {
            DianPingUser user = userList.get(j);
            //是否显示验证码代码
            Map<String, String> captchaMap = new HashedMap();
            captchaMap.put("captchaChannel", "101");
            captchaMap.put("params", "{\"username\":\"" + user.getUa() + "\",\"source\":2}");
            captchaMap.put("callback", "EasyLoginCallBack1");
            String result1 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaShow", captchaMap);
            String json_str1 = result1.substring(19, result1.length() - 1);
            System.out.println("step1:" + user.getUserName() + "请求是否弹出验证码结果：" + result1);
            JSONObject obj1 = JSONObject.fromObject(json_str1);
            boolean isShow = obj1.getJSONObject("msg").getBoolean("isShow");
            userService.update(user.getTel(), "isShow", isShow + "");
            if (!isShow) {
                //如果验证码是
                String uuid = obj1.getJSONObject("msg").getString("uuid");
                userService.update(user.getTel(), "userId", uuid);
            }
            if (isShow) {
                //获取验证码代码
                Map<String, String> picMap = new HashedMap();
                picMap.put("source", "2");
                picMap.put("callback", "EasyLoginCallBack2");
                String result2 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaAuth", picMap);
                String json_str2 = result2.substring(19, result2.length() - 1);
                //获取图片
                JSONObject obj2 = JSONObject.fromObject(json_str2);
                String requestCode = obj2.getJSONObject("msg").getString("requestCode");
                String picUrl = obj2.getJSONObject("msg").getString("picUrl");
                System.out.println("step2:" + user.getUserName() + "获取验证码保存：" + result2);
                userService.update(user.getTel(), "signature", requestCode);
                try {
                    FileUtil.download(picUrl, "output_" + user.getTel() + ".png", "/Users/gaoyang/Downloads/", user);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




    @RequestMapping(value = "/start")
    @ResponseBody
    public String start() {
        String appendResult = "";
        List<DianPingUser> userList = userService.getDianPingList();
        for (int j = 0; j < userList.size(); j++) {
            DianPingUser user = userList.get(j);

            if (userLogin(user)) continue;

            //循环点评事件页面
            List<String> typeList = new ArrayList();
            typeList.add("1");
            List<Map<String, String>> productList = new ArrayList<>();
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
                            Map<String, String> productMap = new HashedMap();
                            productMap.put("title", title);
                            productMap.put("id", id);
                            productList.add(productMap);


                            //请求抽奖代码
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return appendResult;
    }

    private boolean userLogin(DianPingUser user) {
        if (user.getIsShow().equals("true")) {
            Map<String, String> picMap = new HashedMap();
            picMap.put("vcode", user.getVcode());
            picMap.put("requestCode", user.getSignature());
            picMap.put("source", "2");
            picMap.put("callback", "EasyLoginCallBack3");
            String result1 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaCheck", picMap);
            String json_str1 = result1.substring(19, result1.length() - 1);
            System.out.println("step3:" + user.getUserName() + "验证码验证结果：" + json_str1);
            JSONObject obj1 = JSONObject.fromObject(json_str1);
            String code = obj1.getString("code");
            if (!code.equals("200")) {
                System.out.println("----------------------此人验证码有问题，执行下一个--------------------------");
                return true;
            }
            String uuid = obj1.getJSONObject("msg").getString("uuid");
            user.setUserId(uuid);
        }

        //login代码
        Map<String, String> loginMap = new HashedMap();
        loginMap.put("uuid", user.getUserId());
        loginMap.put("username", user.getTel());
        loginMap.put("password", user.getPassword());
        List<String> cookieStrList = HttpUtils.returnCookies("https://m.dianping.com/account/ajax/passwordLogin", loginMap);
        if (cookieStrList != null) {
            String dper = HttpUtils.getCookie("dper", cookieStrList.get(3));
            String ua = HttpUtils.getCookie("ua", cookieStrList.get(1));
            user.setDper(dper);
            user.setUa(ua);
        }
        return false;
    }

    @RequestMapping(value = "/xiaobawang")
    @ResponseBody
    public String xiaobawang() {
        String appendResult = "";
        List<DianPingUser> userList = userService.getDianPingList();
        for (int j = 0; j < userList.size(); j++) {
            DianPingUser user = userList.get(j);
            if (!(user.getTel().equals("18632607391") || user.getTel().equals("18601162343"))) {
                continue;
            }
            if (userLogin(user)) continue;
            //循环点评事件页面
            Map<String, String> paramMap = new HashedMap();
            paramMap.put("activityId", "7413");
            paramMap.put("cityId", "2");
            String mobile = "";
            if (user.getTel().equals("18632607391")) {
                mobile = "18601162346";
            } else {
                mobile = user.getTel();
            }
            paramMap.put("mobile", mobile);
            paramMap.put("type", "fnHvGYyzrswY");
            paramMap.put("env", "job");
            paramMap.put("job", "");
            paramMap.put("dpid", "");
            paramMap.put("cxString", "");
            paramMap.put("callback", "jsonp4");
            String result = HttpUtils.setXiaobawangRequest(paramMap, user);
            System.out.println(user.getUserName() + "报名：" + "小霸王" + " 结果：" + result);
        }
        return appendResult;
    }

    @RequestMapping(value = "/page")
    public ModelAndView toPage (HttpSession httpSession) {
        ModelAndView model = new ModelAndView();
        //用户信息列表
        List<DianPingUser> userList = userService.getDianPingList();
        model.addObject("userList", userList);
        System.out.println("-------------点评——获取用户列表成功--------------------");
        model.setViewName("dianping");
        return model;
    }


    @RequestMapping(value = "/submitUserLogin", method = RequestMethod.POST)
    @ResponseBody
    public LocalResponse submitUserLogin (HttpServletRequest request) {
        LocalResponse response = new LocalResponse();
        StringBuffer output = new StringBuffer();
        String userStr = request.getParameter("userStr");
        String[] userArray = userStr.split(",");
        for (int i = 0; i < userArray.length; i++) {
            DianPingUser user = userService.findDianpingUserByTel(userArray[i]);
            //是否显示验证码代码
            Map<String, String> captchaMap = new HashedMap();
            captchaMap.put("captchaChannel", "101");
            captchaMap.put("params", "{\"username\":\"" + user.getUa() + "\",\"source\":2}");
            captchaMap.put("callback", "EasyLoginCallBack1");
            String result1 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaShow", captchaMap);
            String json_str1 = result1.substring(19, result1.length() - 1);
            System.out.println("step1:" + user.getUserName() + "请求是否弹出验证码结果：" + result1);
            JSONObject obj1 = JSONObject.fromObject(json_str1);
            boolean isShow = obj1.getJSONObject("msg").getBoolean("isShow");
            userService.update(user.getTel(), "isShow", isShow + "");
            if (!isShow) {
                //如果验证码是
                String uuid = obj1.getJSONObject("msg").getString("uuid");
                userService.update(user.getTel(), "userId", uuid);
            }
            if (isShow) {
                //获取验证码代码
                Map<String, String> picMap = new HashedMap();
                picMap.put("source", "2");
                picMap.put("callback", "EasyLoginCallBack2");
                String result2 = HttpUtils.setDianPingLoginRequest("https://m.dianping.com/account/ajax/captchaAuth", picMap);
                String json_str2 = result2.substring(19, result2.length() - 1);
                //获取图片
                JSONObject obj2 = JSONObject.fromObject(json_str2);
                String requestCode = obj2.getJSONObject("msg").getString("requestCode");
                String picUrl = obj2.getJSONObject("msg").getString("picUrl");
                System.out.println("step2:" + user.getUserName() + "获取验证码保存：" + result2);
                userService.update(user.getTel(), "signature", requestCode);
                try {
                    String savePath = new File(".").getAbsolutePath().replace("bin/", "") + "/webapps/zsyh/static/images/";
                    String uuid = UUID.randomUUID().toString();
                    FileUtil.download(picUrl, "output_" + user.getTel() + "_" + uuid + ".png", savePath, user);
                    output.append("output_" + user.getTel() + "_" + uuid + ".png");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.setData(output.toString());
        return response;
    }


    @RequestMapping(value = "/submitUserStart", method = RequestMethod.POST)
    @ResponseBody
    public LocalResponse submitUserStart (HttpServletRequest request) {
        LocalResponse response = new LocalResponse();
        StringBuffer output = new StringBuffer();
        String userStr = request.getParameter("userStr");
        String qiangType = request.getParameter("qiangType");
        String vcode = request.getParameter("vcode");
        String[] userArray = userStr.split(",");
        for (int i = 0; i < userArray.length; i++) {
            if (qiangType.equals("bawangcan")) {
                DianPingUser user = userService.findDianpingUserByTel(userArray[i]);
                user.setVcode(vcode);
                if (userLogin(user)) continue;
                //循环点评事件页面
                List<String> typeList = new ArrayList();
                typeList.add("1");
                List<Map<String, String>> productList = new ArrayList<>();
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
                                Map<String, String> productMap = new HashedMap();
                                productMap.put("title", title);
                                productMap.put("id", id);
                                productList.add(productMap);


                                //请求抽奖代码
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
                                output.append(user.getUserName() + "报名：" + title + " 结果：" + result  + "</br>");
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        response.setData(output.toString());
        return response;
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