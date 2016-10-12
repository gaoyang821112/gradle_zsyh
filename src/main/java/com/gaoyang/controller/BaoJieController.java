package com.gaoyang.controller;

import com.gaoyang.bean.BaojieUser;
import com.gaoyang.bean.User;
import com.gaoyang.service.ParamService;
import com.gaoyang.service.UserService;
import com.gaoyang.thread.BaojieThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/baojie")
public class BaoJieController {
	
	public volatile static boolean switchActivity = false;

    @Autowired
    ParamService paramService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/start")
	public void start() {
        startTreads();
	}

    @RequestMapping(value = "/stop")
	public void stop() {
		switchActivity = false;
	}

    private void startTreads() {
        switchActivity = true;
    Map<String, String> initParams = paramService.getParamMap();
    String piaoId = initParams.get("piaoId");
    boolean baojie_isopen = Boolean.parseBoolean(initParams.get("baojie_isopen"));
    if (baojie_isopen) {
        List<BaojieUser> userList = userService.getBaojieUserList();
        for (BaojieUser user : userList) {
            new Thread(new BaojieThread(user.getOpenId(), user.getUserName(), piaoId)).start();
        }
    }
}

}