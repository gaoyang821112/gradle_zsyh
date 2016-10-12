package com.gaoyang.controller;

import com.gaoyang.bean.*;
import com.gaoyang.service.ParamService;
import com.gaoyang.service.ProductService;
import com.gaoyang.service.UserService;
import com.gaoyang.thread.BankThread;
import com.gaoyang.util.API_URL;
import com.gaoyang.util.HttpUtils;
import com.gaoyang.util.MailUtil;
import com.gaoyang.util.ValidateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.*;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	ParamService paramService;

	@RequestMapping(value = "/Nine")
	@ResponseBody
	public Vector<Product1> getProductList() {
		Vector productList = new Vector();
		String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
		Map params = new HashMap();
		params.put("p1", "35");
		params.put("body", "{\"cityNo\":\"10\",\"regionId\":\"\",\"longitude\":\"116.4505579326416\",\"labelId\":\"01\",\"dimension\":\"39.93005504289196\",\"merTypeId2\":\"\",\"signOfOrder\":\"1\",\"districtId\":\"\"}");
		params.put("p6", "478931494");
		params.put("p2", "68281119-1AB4-4D63-A54C-B9F499902DF3");
		params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
		params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
		params.put("p8", "10B9D155-E63B-4ABC-8956-769CC0D0F561");
		params.put("p4", "A83D0972-344A-4724-974B-9F059BA93BC1");
		params.put("p9", "7a33ae01d478482c81f9003277f7c90e");
		params.put("p0", "i");
		params.put("p10", "BE17D154-DD6A-4FB9-975D-00B601AD0D3E");
		params.put("p5", "17A8AF7157CD46FBAC97188626CA08A0");
		params.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0010\",\"chn1_id\":\"01\",\"sessionId\":\"7a33ae01d478482c81f9003277f7c90e\",\"session_id\":\"123124123\",\"user_id\":\"17A8AF7157CD46FBAC97188626CA08A0\",\"pageIndex\": 1}");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject bodyObj = (JSONObject) obj.get("body");
		int totalPage = (Integer.parseInt(bodyObj.getString("totalRecords")) / 10) + 1 ;
		String newProduct = "";
		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
//			params1.put("body", "{districtId='', labelId='01', cityNo='10', longitude='116.456522', signOfOrder='1', regionId='', dimension='39.93138', merTypeId2=''}");
//			params1.put("syshead", "{chnlUserId='17A8AF7157CD46FBAC97188626CA08A0', trans_code='SI_PRD0010', sessionId='null', chnlId='01', pageIndex=" + (i + 1) + ", pageSize=10}");
			params1.put("p1", "35");
			params1.put("body", "{\"cityNo\":\"10\",\"regionId\":\"\",\"longitude\":\"116.4505579326416\",\"labelId\":\"01\",\"dimension\":\"39.93005504289196\",\"merTypeId2\":\"\",\"signOfOrder\":\"1\",\"districtId\":\"\"}");
			params1.put("p6", "478931494");
			params1.put("p2", "68281119-1AB4-4D63-A54C-B9F499902DF3");
			params1.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
			params1.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
			params1.put("p8", "10B9D155-E63B-4ABC-8956-769CC0D0F561");
			params1.put("p4", "A83D0972-344A-4724-974B-9F059BA93BC1");
			params1.put("p9", "7a33ae01d478482c81f9003277f7c90e");
			params1.put("p0", "i");
			params1.put("p10", "BE17D154-DD6A-4FB9-975D-00B601AD0D3E");
			params1.put("p5", "17A8AF7157CD46FBAC97188626CA08A0");
			params1.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0010\",\"chn1_id\":\"01\",\"sessionId\":\"7a33ae01d478482c81f9003277f7c90e\",\"session_id\":\"123124123\",\"user_id\":\"17A8AF7157CD46FBAC97188626CA08A0\",\"pageIndex\":" + (i + 1) + "}");
			String pageResult = HttpUtils.postUrl4ZSYH(productUrl, params1);
			JSONObject obj2 = JSONObject.fromObject(pageResult);
			JSONObject bodyObj2 = (JSONObject) obj2.get("body");
			JSONArray rows = bodyObj2.getJSONArray("rows");
			if (rows != null) {
				for (int j = 0; j < rows.size(); j++) {
					JSONObject obj3 = rows.getJSONObject(j);
					Product1 product = new Product1();
					product.setProductName(obj3.getString("productName"));
					product.setProductPicUrl(obj3.getString("productPicUrl"));
					product.setProductNo(obj3.getString("productNo"));
					product.setValidityPerBegin(obj3.getString("validityPerBegin"));
					product.setValidityPerEnd(obj3.getString("validityPerEnd"));
					product.setBtnStatus(obj3.getString("btnStatus"));
					product.setBtnText(obj3.getString("btnText"));
					product.setIsRemained("0");
					productList.add(product);
					this.productService.updateProduct1(product);
//					//到货提醒
					if ((this.productService.findProductByNo1(obj3.getString("productNo")) != null)
							&& (this.productService.findProductByNo1(obj3.getString("productNo")).getIsRemained().equals("1"))
							&& obj3.getString("btnText").equals("立即购买")) {
						newProduct = newProduct + "<img src='" + obj3.getString("productPicUrl") + "'>" + "</br>";
						newProduct = newProduct + obj3.getString("productName") + " 库存：" + obj3.getString("btnText") + "</br>";
					}
					//新品上市	
					if ((!this.productService.isExistProductByProductNo1(obj3.getString("productNo")))
							|| (!this.productService.isExistProductByName1(obj3.getString("productName")))) {
						this.productService.saveProduct(product);
						newProduct = newProduct + "<img src='" + obj3.getString("productPicUrl") + "'>" + "</br>";
						newProduct = newProduct + obj3.getString("productName")
								+ " 库存:" + obj3.getString("btnText")
								+ " 开售时间:" + obj3.getString("validityPerBegin")
								+ "</br>";
					}
				}
			}
		}
		if (!newProduct.equals(""))
			try {
				MailUtil.sendMail("新品上市Nine", newProduct);
			} catch (Exception localException) {
		}
		productService.removePastProducts1();
		return productList;
	}
	
	@RequestMapping(value = "/Jifentao")
	@ResponseBody
	public Vector<Product2> getProductList2() {
		Vector productList = new Vector();
		String productUrl = API_URL.ZSYH_ADDRESS1 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL;
		Map params = new HashMap();
		params.put("cityNo", "10");
		params.put("pageIndex", "1");
		params.put("couponType", "Jifentao");
		String result = HttpUtils.postUrl4ZSYH(productUrl
				+ API_URL.userArray[0][1], params);
		JSONObject obj = JSONObject.fromObject(result);
		int totalPage = Integer.parseInt(obj.getString("totalPages"));
		String newProduct = "";
		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
			params1.put("cityNo", "10");
			params1.put("pageIndex", i + 1);
			params1.put("couponType", "Jifentao");
			String pageResult = HttpUtils.postUrl4ZSYH(productUrl
					+ API_URL.userArray[0][1], params1);
			JSONObject obj2 = JSONObject.fromObject(pageResult);
			JSONArray coupons = obj2.getJSONArray("coupons");
			if (coupons != null) {
				for (int j = 0; j < coupons.size(); j++) {
					JSONObject obj3 = coupons.getJSONObject(j);
					Product2 product = new Product2();
					product.setName(obj3.getString("name"));
					product.setPicUrl(obj3.getString("picUrl"));
					product.setProductNo(obj3.getString("productNo"));
					product.setSaleBeginTime(obj3.getString("saleBeginTime"));
					product.setSaleEndTime(obj3.getString("saleEndTime"));
					product.setShowStatus(obj3.getString("showStatus"));
					product.setStock(obj3.getString("stock"));
					product.setType("Jifentao");
					product.setIsRemained("0");
					productList.add(product);

					this.productService.updateProduct2(product);
					
					if ((this.productService.findProductByNo2(obj3.getString("productNo")) != null)
							&& (this.productService.findProductByNo2(obj3.getString("productNo")).getIsRemained().equals("1"))
							&& (Integer.parseInt(obj3.getString("stock")) > 0)) {
						newProduct = newProduct + "<img src='" + obj3.getString("picUrl") + "'>" + "</br>";
						newProduct = newProduct + obj3.getString("name") + " 库存：" + obj3.getString("stock") + "</br>";
						//库存有时自动抢
						String getMsg = "";
						for (int k = 0; k < API_URL.userArray.length; k++) {
							String userId = API_URL.userArray[k][2];
							params.put("quantity", "1");
							params.put("userId", userId);
							params.put("productId", obj3.getString("productNo"));
							params.put("orderPoint", "9");
							params.put("orderCardType", "4");
							HashMap localHashMap = new HashMap();
							localHashMap.put("orderPoint", "9");
							localHashMap.put("orderCardType", "4");
							localHashMap.put("quantity", "1");
					        localHashMap.put("productId", obj3.getString("productNo"));
					        localHashMap.put("userId", userId);
					        String str1 = ValidateUtils.a(localHashMap, "asc");
					        String mac = ValidateUtils.b(str1 + "0102030405060708");
							params.put("mac", mac);
							String orderUrl = API_URL.ZSYH_ADDRESS1 + API_URL.ZSYH.CREATE_ORDER_URL;
							String getResult = HttpUtils.postUrl4ZSYH(orderUrl, params);
							if (!getResult.contains("尊敬的客户，您的操作过于频繁，请休息片刻")) {
								getMsg += (API_URL.userArray[k][0] + "---" + getResult) + "</br>";
							}
						}
						newProduct += getMsg;
					}

					if ((!this.productService.isExistProductByProductNo2(obj3
							.getString("productNo")))
							|| (!this.productService.isExistProductByName2(obj3
									.getString("name")))) {
						this.productService.saveProduct(product);
						newProduct = newProduct + "<img src='" + obj3.getString("picUrl") + "'>" + "</br>";
						newProduct = newProduct + obj3.getString("name")
								+ " 库存:" + obj3.getString("stock")
								+ " 开售时间:" + obj3.getString("saleBeginTime")
								+ "</br>";
					}
				}
			}
		}
		if (!newProduct.equals(""))
			try {
				MailUtil.sendMail("新品上市JiFentao", newProduct);
			} catch (Exception localException) {
		}
		productService.removePastProducts2();
		return productList;
	}

	@RequestMapping(value = "/t/{type}/{productNo}")
	@ResponseBody
	public String updateRemaiding(@PathVariable String type, @PathVariable String productNo) {
		String returnMsg = this.productService.updateRemaiding(type, productNo);
		return productNo + " update " + returnMsg + " success";
	}

	@RequestMapping(value = "/userList")
	@ResponseBody
	public List<User> getUserList() {
		return userService.getUserList();
	}








	public volatile static boolean switchActivity = false;

	@RequestMapping(value = "/submit3hour", method = RequestMethod.POST)
	public String submit3hour (HttpServletRequest request, HttpSession httpSession) {
		switchActivity = true;
		String userStr1 = request.getParameter("userStr1");
		String[] user1Array = userStr1.split(",");
		String[] product1Array = request.getParameterValues("product1Box");
		Map<String, String> initParams = paramService.getParamMap();
		Map<String, Product1> product1Map = (Map<String, Product1>)httpSession.getAttribute("product1Map");
		for (int i = 0; i < user1Array.length; i++) {
			if (StringUtils.isNotBlank(user1Array[i])) {
				User userBean = userService.findUserByUserId(user1Array[i]);
				for (int j = 0; j < product1Array.length; j++) {
					Map<String, String> params = new HashedMap();
					Product1 productBean = product1Map.get(product1Array[j]);
					params.put("body", "{\"isCanRush\":\"1\",\"payType\":\"1000\",\"labelId\":\"01\",\"productNo\":\"" + productBean.getProductNo() + "\",\"moduleType\":\"1\",\"accountNum\":1,\"mobilePhone\":\"" + "" + "\",\"bakNo\":\"" + productBean.getBakNo() + "\"}");
					params.put("p0", initParams.get("p0"));
					params.put("p1", initParams.get("p1"));
					params.put("p2", initParams.get("p2"));
					params.put("p3", initParams.get("p3"));
					params.put("p4", initParams.get("p4"));
					params.put("p5", userBean.getUserId());
					params.put("p6", initParams.get("p6"));
					params.put("p7", initParams.get("p7"));
					params.put("p8", initParams.get("p8"));
					params.put("p9", initParams.get("p9"));
					params.put("p10", initParams.get("p10"));
					params.put("syshead", "{\"trans_code\":\"SI_ORD0001\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userBean.getUserId() + "\",\"sessionId\":\"" + params.get("p9") + "\"}");
					Map<String, String> logMap = new HashedMap();
					logMap.put("userName", userBean.getUserName());
					logMap.put("productName", productBean.getProductName());
					new Thread(new BankThread(Integer.parseInt(initParams.get("times")), params, logMap)).start();
				}
			}
		}
		return "success";
	}

	@RequestMapping(value = "/submitWednesDay", method = RequestMethod.POST)
	public String submitWednesDay (HttpServletRequest request, HttpSession httpSession) {
		switchActivity = true;
		String userStr2 = request.getParameter("userStr2");
		String[] user2Array = userStr2.split(",");
		String[] product2Array = request.getParameterValues("product2Box");
		Map<String, String> initParams = paramService.getParamMap();
		Map<String, Product1> product1Map = (Map<String, Product1>)httpSession.getAttribute("product2Map");
		for (int i = 0; i < user2Array.length; i++) {
			if (StringUtils.isNotBlank(user2Array[i])) {
				User userBean = userService.findUserByUserId(user2Array[i]);
				for (int j = 0; j < product2Array.length; j++) {
					Map<String, String> params = new HashedMap();
					Product1 productBean = product1Map.get(product2Array[j]);
					params.put("body", "{\"isCanRush\":\"1\",\"payType\":\"1000\",\"labelId\":\"01\",\"productNo\":\"" + productBean.getProductNo() + "\",\"moduleType\":\"1\",\"accountNum\":1,\"mobilePhone\":\"" + "" + "\",\"bakNo\":\"" + productBean.getBakNo() + "\"}");
					params.put("p0", initParams.get("p0"));
					params.put("p1", initParams.get("p1"));
					params.put("p2", initParams.get("p2"));
					params.put("p3", initParams.get("p3"));
					params.put("p4", initParams.get("p4"));
					params.put("p5", userBean.getUserId());
					params.put("p6", initParams.get("p6"));
					params.put("p7", initParams.get("p7"));
					params.put("p8", initParams.get("p8"));
					params.put("p9", initParams.get("p9"));
					params.put("p10", initParams.get("p10"));
					params.put("syshead", "{\"trans_code\":\"SI_ORD0001\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userBean.getUserId() + "\",\"sessionId\":\"" + params.get("p9") + "\"}");
					Map<String, String> logMap = new HashedMap();
					logMap.put("userName", userBean.getUserName());
					logMap.put("productName", productBean.getProductName());
					new Thread(new BankThread(Integer.parseInt(initParams.get("times")), params, logMap)).start();
				}
			}
		}
		return "success";
	}

	@RequestMapping(value = "/stop")
	public void stop() {
		switchActivity = false;
	}

	@RequestMapping(value = "/page")
	public ModelAndView toPage (HttpSession httpSession) {
		ModelAndView model = new ModelAndView();
		//用户信息列表
		List<User> userList = userService.getUserList();
		model.addObject("userList", userList);

		String userId = userList.get(0).getUserId();
		System.out.println("-------------获取用户列表成功--------------------");

		//下午三点产品列表
		List<Product1> product1List = this.get3HourList(userId);
		model.addObject("product1List", product1List);

		Map<String, Product1> product1Map = new HashedMap();
		for(Product1 product1 : product1List) {
			product1Map.put(product1.getProductNo(), product1);
		}

		httpSession.setAttribute("product1Map", product1Map);
		System.out.println("-------------获取下午三点列表成功--------------------");

		//周三五折产品列表
		List<Product1> product2List = this.getWednesdayList(userId);
		model.addObject("product2List", product2List);

		Map<String, Product1> product2Map = new HashedMap();
		for(Product1 product2 : product2List) {
			product2Map.put(product2.getProductNo(), product2);
		}

		httpSession.setAttribute("product2Map", product2Map);
		System.out.println("-------------获取周三五折列表成功--------------------");
		model.setViewName("index");
		return model;
	}



	public List<Product1> get3HourList(String userId) {
		List<Product1> productList = new ArrayList<>();
		String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
		Map params = new HashMap();
		params.put("body", "{\"cityNo\":\"" + API_URL.CITY + "\",\"labelId\":\"04\"}");
		params.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0002\",\"chn1Id\":\"01\",\"chnlUserId\":\"" + userId + "\",\"pageIndex\": 1}");
		params.put("p0", "a");
		params.put("p1", "40");
		params.put("p2", "wandoujia");
		params.put("p3", "74816aade7414cd489fe0fd7e389305c7");
		params.put("p4", "1d2ef922ad7546b986f41daa108c058a");
		params.put("p5", userId);
		params.put("p6", "491198976");
		params.put("p7", "0d02645bd2a14a79a8c94ce8f1ca4fe7");
		params.put("p8", "be33231cd74b4ad982ffd58031648671");
		params.put("p9", "");
		params.put("p10", "1194d51599f54ad8a160fc511e06e78b");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject bodyObj = (JSONObject) obj.get("body");
		//获取一共几页
		int totalPage = (Integer.parseInt(bodyObj.getString("totalRecords")) / 10) + 1 ;

		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
			params1.put("body", "{\"cityNo\":\"" + API_URL.CITY + "\",\"labelId\":\"04\"}");
			params1.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0002\",\"chn1Id\":\"01\",\"chnlUserId\":\"" + userId + "\",\"pageIndex\": " + (i + 1) + "}");
			params1.put("p0", "a");
			params1.put("p1", "40");
			params1.put("p2", "wandoujia");
			params1.put("p3", "74816aade7414cd489fe0fd7e389305c7");
			params1.put("p4", "1d2ef922ad7546b986f41daa108c058a");
			params1.put("p5", userId);
			params1.put("p6", "491198976");
			params1.put("p7", "0d02645bd2a14a79a8c94ce8f1ca4fe7");
			params1.put("p8", "be33231cd74b4ad982ffd58031648671");
			params1.put("p9", "");
			params1.put("p10", "1194d51599f54ad8a160fc511e06e78b");
			String pageResult = HttpUtils.postUrl4ZSYH(productUrl, params1);
			JSONObject obj2 = JSONObject.fromObject(pageResult);
			JSONObject bodyObj2 = (JSONObject) obj2.get("body");
			JSONArray rows = bodyObj2.getJSONArray("rows");
			if (rows != null) {
				for (int j = 0; j < rows.size(); j++) {
					JSONObject obj3 = rows.getJSONObject(j);
					Product1 product = new Product1();
					product.setProductName(obj3.getString("productName"));
					product.setProductPicUrl(obj3.getString("productPicUrl"));
					product.setProductNo(obj3.getString("productNo"));
					product.setBakNo(obj3.getString("bakNo"));
					product.setValidityPerBegin(obj3.getString("validityPerBegin"));
					product.setValidityPerEnd(obj3.getString("validityPerEnd"));
					product.setBtnStatus(obj3.getString("btnStatus"));
					product.setBtnText(obj3.getString("btnText"));
					product.setIsRemained("0");
					productList.add(product);
				}
			}
		}
		return productList;
	}

	public List<Product1> getWednesdayList(String userId) {
		List<Product1> productList = new ArrayList<>();
		String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
		Map params = new HashMap();
		params.put("body", "{\"cityNo\":\"" + API_URL.CITY + "\",\"labelId\":\"03\"}");
		params.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0002\",\"chn1Id\":\"01\",\"chnlUserId\":\"" + userId + "\",\"pageIndex\": 1}");
		params.put("p0", "a");
		params.put("p1", "40");
		params.put("p2", "meizu");
		params.put("p3", "74816aade7414cd489fe0fd7e389305c7");
		params.put("p4", "1d2ef922ad7546b986f41daa108c058a");
		params.put("p5", userId);
		params.put("p6", "491198976");
		params.put("p7", "0d02645bd2a14a79a8c94ce8f1ca4fe7");
		params.put("p8", "be33231cd74b4ad982ffd58031648671");
		params.put("p9", "");
		params.put("p10", "1194d51599f54ad8a160fc511e06e78b");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject bodyObj = (JSONObject) obj.get("body");
		//获取一共几页
		int totalPage = (Integer.parseInt(bodyObj.getString("totalRecords")) / 10) + 1 ;

		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
			params1.put("body", "{\"cityNo\":\"" + API_URL.CITY + "\",\"labelId\":\"03\"}");
			params1.put("syshead", "{\"pageSize\":10,\"trans_code\":\"SI_PRD0002\",\"chn1Id\":\"01\",\"chnlUserId\":\"" + userId + "\",\"pageIndex\": " + (i + 1) + "}");
			params1.put("p0", "a");
			params1.put("p1", "40");
			params1.put("p2", "meizu");
			params1.put("p3", "74816aade7414cd489fe0fd7e389305c7");
			params1.put("p4", "1d2ef922ad7546b986f41daa108c058a");
			params1.put("p5", userId);
			params1.put("p6", "491198976");
			params1.put("p7", "0d02645bd2a14a79a8c94ce8f1ca4fe7");
			params1.put("p8", "be33231cd74b4ad982ffd58031648671");
			params1.put("p9", "");
			params1.put("p10", "1194d51599f54ad8a160fc511e06e78b");
			String pageResult = HttpUtils.postUrl4ZSYH(productUrl, params1);
			JSONObject obj2 = JSONObject.fromObject(pageResult);
			JSONObject bodyObj2 = (JSONObject) obj2.get("body");
			JSONArray rows = bodyObj2.getJSONArray("rows");
			if (rows != null) {
				for (int j = 0; j < rows.size(); j++) {
					JSONObject obj3 = rows.getJSONObject(j);
					Product1 product = new Product1();
					product.setProductName(obj3.getString("productName"));
					product.setProductPicUrl(obj3.getString("productPicUrl"));
					product.setProductNo(obj3.getString("productNo"));
					product.setBakNo(obj3.getString("bakNo"));
					product.setValidityPerBegin(obj3.getString("validityPerBegin"));
					product.setValidityPerEnd(obj3.getString("validityPerEnd"));
					product.setBtnStatus(obj3.getString("btnStatus"));
					product.setBtnText(obj3.getString("btnText"));
					product.setIsRemained("0");
					productList.add(product);
				}
			}
		}
		return productList;
	}


	public static void main(String[] args) throws Exception {
//		ProductController p = new ProductController();
//		try {
//			p.sendMail("hello");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date dateTime1 = dateFormat.parse("2015-11-09 11:13:00");
//		Date dateTime2 = new Date();
//		System.out.println(dateTime1.compareTo(dateTime2));
		
//		String str = "p1=35&body=%7B%22isCanRush%22%3A%221%22%2C%22payType%22%3A%221111%22%2C%22labelId%22%3A%2201%22%2C%22productNo%22%3A%222015122811512%22%2C%22moduleType%22%3A%221%22%2C%22accountNum%22%3A1%2C%22mobilePhone%22%3A%22186%2A%2A%2A%2A2346%22%2C%22bakNo%22%3A%22201512281151200003%22%7D&p6=478925117&p2=30CC6EF6-FA26-4EC9-8091-C394EC4FC40D&p7=C7C5BC31E5544437BE1D43CF7F735627&p3=22FCB3CBBC534C61B4775AD4740DB4E7f&p8=2178A726-B223-4F04-B4A3-C38502FA506E&p4=5DE43A41-7C4F-481D-86FC-12302FBC3530&p9=bad4741a918b449ba32f2042fce05f00&p0=i&p10=43765A41-0CFC-4C88-9982-8FA90BA6B2EA&p5=17A8AF7157CD46FBAC97188626CA08A0&syshead=%7B%22trans_code%22%3A%22SI_ORD0001%22%2C%22chnlId%22%3A%2201%22%2C%22chnlUserId%22%3A%2217A8AF7157CD46FBAC97188626CA08A0%22%2C%22sessionId%22%3A%22bad4741a918b449ba32f2042fce05f00%22%7D";
//		String s = URLDecoder.decode(str);
//		System.out.println(s);

//		String[][] userArray = new String[][]{
//				{"高洋", "?p0=a&p1=35&p2=f59b4efef5454bb18561c4dde7974c67&p3=522e29c023924b379e53df1bf555587c8&p4=8013b5f1ae704ce3a20075dcea7ded58&p5=17A8AF7157CD46FBAC97188626CA08A0&p6=477725532&p7=928817042e334b46b5322d21277da205&p8=bfcff26b5b294170b6bfa7cf31f2369b&p9=09642418d500463a9fd4f2f9eab03cbf&p10=018744f4a94c435099095ba6132d02b4", "17A8AF7157CD46FBAC97188626CA08A0", "186****2346"},
//				{"王志强", "?p0=a&p1=31&p2=78faedc5eb27401980b6734a316f8be2&p3=D9D850D6908A54E77EAFAD2A4D3BE30C&p4=e69d447e339a4966955f26c4cd03a6bb&p5=c9d2cf6c8b3c4f519695ab59c35f00cb&p6=471678020&p7=4a47b3639a364d85a92088b79c4a6c0c&p8=f13d454eabcb4b3a98b8660f3e61188b&p9=null&p10=a943ef6af7814a4486ceebcab49d731f", "c9d2cf6c8b3c4f519695ab59c35f00cb", "185****4298"},
//				{"荣佳丽", "?p0=a&p1=31&p2=13ed11a11b2040eda7aad3b15218ae0f&p3=FE73ED0E3803F5C41A8A573EFA114AF7&p4=4079cc4296164fa3a33535eb9dc99efe&p5=9861c5e60f3c48fd98579918c4c44a1f&p6=471677430&p7=967ecdf77abf4b1b8da34ae8a12b58b9&p8=7e10a746a41e4f9782be29420e6eb518&p9=null&p10=36c7dfb8db35402d9d05da0f6582751b", "9861c5e60f3c48fd98579918c4c44a1f", "186****2343"},
//				{"宋强", "?p0=a&p1=31&p2=deb54f0fe5314204a8c04e3dc06d8168&p3=751DEB5D047AFF136BBDA42FBD99B4EC&p4=b2ff0501b28f418f9cacd6f377fd791d&p5=7c521c86114e4f8aa2c901a91d5d25c5&p6=471679289&p7=47155f6109224699a029e7ad6b32c302&p8=2570bbe079cc41a49e877ffb2bdc1274&p9=null&p10=9080f5dfea0a47ceaf03a06a0e33c56c", "7c521c86114e4f8aa2c901a91d5d25c5", "138****8430"},
//				{"王鹏", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "03570d38aab14714831b62a132fd61e2", "1369****3985"},
//				{"王悦", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "23e645508dde487c92069a231ff99d9f", "1351****5398"},
//				{"李成", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "1fa9b434100f45258ff4afc422a8a703", "1501****2836"},
//				{"张伟", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "d1ad7c70c25e42458cd87ce5abe9aeaf", "1381****0985"},
//				{"肖博士", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "c8d23e4a0fec4575a96ca09bd0670f83", "1852****2288"}
//		};
//
//		for (int i = 0; i < userArray.length; i++) {
//			JSONObject obj = new JSONObject();
//			obj.put("userName", userArray[i][0]);
//			obj.put("userStr", userArray[i][1]);
//			obj.put("p9", userArray[i][2]);
//			obj.put("tel", userArray[i][3]);
//			System.out.println(obj.toString());
//		}

	}
}