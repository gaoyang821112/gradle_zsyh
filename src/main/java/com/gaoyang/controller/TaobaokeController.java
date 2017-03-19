package com.gaoyang.controller;

import com.gaoyang.bean.Taobaoke;
import com.gaoyang.poi.Common;
import com.gaoyang.poi.ReadExcel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/taobaoke")
public class TaobaokeController {

	@RequestMapping(value = "/start/{type}")
	public ModelAndView start(@PathVariable String type) {
		ModelAndView model = new ModelAndView();
		String excel2003_2007 = "1";
		if (type.equals("1"))
			excel2003_2007 = Common.HIGH_XLS_PATH;
		else
			excel2003_2007 = Common.LOW_XLS_PATH;
		List<Taobaoke> list = null;
		try {
			list = new ReadExcel().readExcel(excel2003_2007, type);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (list != null) {
			for (Taobaoke taobaoke : list) {
                System.out.println("productPic : " + taobaoke.getProductPic()
						+ ", ProductName : " + taobaoke.getProductName()
						+ ", ouponShortUrl : " + taobaoke.getCouponShortUrl()
						+ ", origin : " + taobaoke.getOrigin()
						+ ", discount : " + taobaoke.getDiscount()
						+ ", Intro : " + taobaoke.getIntro()
						+ ", CouponTaokouling : " + taobaoke.getCouponTaokouling());
			}
		}
		model.setViewName("taobaoke");
		model.addObject("list", list);
		return model;
	}




}