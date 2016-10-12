package com.gaoyang.controller;
import com.gaoyang.service.ProductService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class PresureTestController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save() {
		return "ok";
	}

	@RequestMapping(value = "/{articleId}", method = RequestMethod.GET)
	@ResponseBody
	public String get(@PathVariable String articleId) {

		return articleId;
	}
	
}
