package com.gaoyang.job;

import com.gaoyang.controller.ProductController;
import com.gaoyang.service.ProductService;
import com.gaoyang.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class QuartzJob {

	@Autowired
	ProductService productService;
	
	@Scheduled(cron = "1 * * * * ?")
	public void mailWork() {
//		ProductController controller = new ProductController();
//		controller.getProductList();
//		controller.getProductList2();

//		HttpUtils.sendGet("http://localhost:8588/zsyh/product/Nine");
//		HttpUtils.sendGet("http://localhost:8588/zsyh/product/Jifentao");
		HttpUtils.sendGet("http://localhost:8080/zsyh/product/Nine");
		HttpUtils.sendGet("http://localhost:8080/zsyh/product/Jifentao");
//		HttpUtils.sendGet("http://58.134.106.9:888/zsyh/product/Nine");
//		HttpUtils.sendGet("http://58.134.106.9:888/zsyh/product/Jifentao");
//		HttpUtils.sendGet("http://localhost:8080/zsyh/card/start");
//		HttpUtils.sendGet("http://localhost:8080/zsyh/card/everyday");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(date) + " 调用成功");


	}

	@Scheduled(cron = "0 59 13 * * ?")
	public void baojieWork1() {
		HttpUtils.sendGet("http://101.200.187.214:8080/zsyh/baojie/start");
	}

	@Scheduled(cron = "0 1 14 * * ?")
	public void baojieWork2() {
		HttpUtils.sendGet("http://101.200.187.214:8080/zsyh/baojie/stop");
	}

	@Scheduled(cron = "0 0 10 * * ?")
	public void cardEverydayWork() {
		HttpUtils.sendGet("http://localhost:8080/zsyh/dianping/start");
	}

}