package com.gaoyang.thread;

import com.gaoyang.controller.ProductController;
import com.gaoyang.util.API_URL;
import com.gaoyang.util.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

public class BankThread implements Callable {

	private int times = 1;

	private Map<String, String> params;
	private Map<String, String> logParams = null;

	public BankThread(int times, Map<String, String> params, Map<String, String> logParams) {
		this.times = times;
		this.params = params;
		this.logParams = logParams;
	}

	@Override
	public String call() throws Exception {
		String logOutput = "";
		for (int i = 0; i < times; i++) {
			if (ProductController.switchActivity) {
				String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
				String result = HttpUtils.postUrl4ZSYH(productUrl, params);
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				System.out.println(sdf.format(date) + " threadName=" + Thread.currentThread().getName() + " thread-" + logParams.get("userName") + " " + logParams.get("productName") + ":" + result);
				logOutput += logParams.get("userName") + " " + logParams.get("productName") + ":" + result + "</br>";
			}
			else {
				System.out.println("threadName=" + Thread.currentThread().getName() + "终止");
				break;
			}
		}
		return logOutput;
	}

}