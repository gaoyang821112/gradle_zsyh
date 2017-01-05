package com.gaoyang.controller;

import com.gaoyang.bean.*;
import com.gaoyang.service.ParamService;
import com.gaoyang.service.ProductService;
import com.gaoyang.service.UserService;
import com.gaoyang.thread.BankThread;
import com.gaoyang.util.*;
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
					        String mac = ValidateUtils.getMd5UpperCase(str1 + "0102030405060708");
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
					params.put("body", "{payType='1000', bakNo='" + productBean.getBakNo() + "', isCanRush='0', moduleType='1', mobilePhone='', productNo='" + productBean.getProductNo() + "', accountNum=1}");
					params.put("syshead", "{chnlUserId='" + userBean.getUserId() + "', chnlId='01', trans_code='SI_ORD0016', sessionId='" + params.get("p9") + "'}");
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
					params.put("body", "{payType='1000', bakNo='" + productBean.getBakNo() + "', isCanRush='0', moduleType='1', mobilePhone='', productNo='" + productBean.getProductNo() + "', accountNum=1}");
					params.put("syshead", "{chnlUserId='" + userBean.getUserId() + "', chnlId='01', trans_code='SI_ORD0016', sessionId='" + params.get("p9") + "'}");
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

		params.put("body", "{districtId='', labelId='04', cityNo='" + API_URL.CITY + "', longitude='116.456551', parmName='WEDNESDAY', signOfOrder='0', regionId='', dimension='39.931397', merTypeId2=''}");
		params.put("syshead", "{chnlUserId='" + userId + "', trans_code='SI_PRD0020', sessionId='null', chnlId='01', pageIndex=1, pageSize=10}");
		params.put("p0", "a");
		params.put("p1", "50");
		params.put("p2", "meizu");
		params.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
		params.put("p4", "afea3edbc21f41cfbf957b186361a517");
		params.put("p5", userId);
		params.put("p6", "507867717");
		params.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
		params.put("p8", "eda1b84e588c48e270d276affa");
		params.put("p9", "null");
		params.put("p10", "c589b6c9e0d432181688e5a705da728");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject bodyObj = (JSONObject) obj.get("body");
		//获取一共几页
		int totalPage = (Integer.parseInt(bodyObj.getString("totalRecords")) / 10) + 1 ;

		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
			params1.put("body", "{districtId='', labelId='04', cityNo='" + API_URL.CITY + "', longitude='116.456551', parmName='WEDNESDAY', signOfOrder='0', regionId='', dimension='39.931397', merTypeId2=''}");
			params1.put("syshead", "{chnlUserId='" + userId + "', trans_code='SI_PRD0020', sessionId='null', chnlId='01', pageIndex=" + (i + 1) + ", pageSize=10}");
			params1.put("p0", "a");
			params1.put("p1", "50");
			params1.put("p2", "meizu");
			params1.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
			params1.put("p4", "afea3edbc21f41cfbf957b186361a517");
			params1.put("p5", userId);
			params1.put("p6", "507867717");
			params1.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
			params1.put("p8", "eda1b84e588c48e270d276affa");
			params1.put("p9", "null");
			params1.put("p10", "c589b6c9e0d432181688e5a705da728");
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

		params.put("body", "{districtId='', labelId='03', cityNo='" + API_URL.CITY + "', longitude='116.456551', parmName='WEDNESDAY', signOfOrder='0', regionId='', dimension='39.931397', merTypeId2=''}");
		params.put("syshead", "{chnlUserId='" + userId + "', trans_code='SI_PRD0020', sessionId='null', chnlId='01', pageIndex=1, pageSize=10}");
		params.put("p0", "a");
		params.put("p1", "50");
		params.put("p2", "meizu");
		params.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
		params.put("p4", "afea3edbc21f41cfbf957b186361a517");
		params.put("p5", userId);
		params.put("p6", "507867717");
		params.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
		params.put("p8", "eda1b84e588c48e270d276affa");
		params.put("p9", "null");
		params.put("p10", "c589b6c9e0d432181688e5a705da728");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		JSONObject obj = JSONObject.fromObject(result);
		JSONObject bodyObj = (JSONObject) obj.get("body");
		//获取一共几页
		int totalPage = (Integer.parseInt(bodyObj.getString("totalRecords")) / 10) + 1 ;

		for (int i = 0; i < totalPage; i++) {
			Map params1 = new HashMap();
			params1.put("body", "{districtId='', labelId='03', cityNo='" + API_URL.CITY + "', longitude='116.456551', parmName='WEDNESDAY', signOfOrder='0', regionId='', dimension='39.931397', merTypeId2=''}");
			params1.put("syshead", "{chnlUserId='" + userId + "', trans_code='SI_PRD0020', sessionId='null', chnlId='01', pageIndex=" + (i + 1) + ", pageSize=10}");
			params1.put("p0", "a");
			params1.put("p1", "50");
			params1.put("p2", "meizu");
			params1.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
			params1.put("p4", "afea3edbc21f41cfbf957b186361a517");
			params1.put("p5", userId);
			params1.put("p6", "507867717");
			params1.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
			params1.put("p8", "eda1b84e588c48e270d276affa");
			params1.put("p9", "null");
			params1.put("p10", "c589b6c9e0d432181688e5a705da728");
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
//		HashMap localHashMap = new HashMap();
//		localHashMap.put("orderCardType", "7");
//		localHashMap.put("orderPoint", "9");
////		localHashMap.put("orderAmount", "0");
//		localHashMap.put("quantity", "1");
//		localHashMap.put("productId", "0060000005018");
//		localHashMap.put("userId", "9861c5e60f3c48fd98579918c4c44a1f");
//		String str1 = ValidateUtils.a(localHashMap, "asc");
//		String mac = ValidateUtils.getMd5UpperCase(str1 + "0102030405060708");
//		System.out.println(mac);

//		Map params = new HashMap();
//		for (int k = 0; k < API_URL.userArray.length; k++) {
//			String userId = API_URL.userArray[k][2];
//			params.put("quantity", "1");
//			params.put("userId", userId);
//			//电影票
////			params.put("productId", "0143000006246");
//			//华硕玩家国度整机500元优惠券
//			params.put("productId", "0143000006234");
//			params.put("orderPoint", "9");
//			params.put("orderCardType", "4");
//			params.put("p0", "a");
//			params.put("p1", "53");
//			params.put("p2", "wandoujia");
//			params.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
//			params.put("p4", "18105587056d4a5e806aac34e11900b7");
//			params.put("p5", userId);
//			params.put("p6", "509859639");
//			params.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
//			params.put("p8", "4933723c6a9f4451db54b53db342bff26");
//			params.put("p9", "null");
//			params.put("p10", "ae6376925aed41b78d613a2b4cf9715b");
//			params.put("groupFlag", "");
//			HashMap localHashMap = new HashMap();
//			localHashMap.put("orderPoint", "9");
//			localHashMap.put("orderCardType", "4");
//			localHashMap.put("quantity", "1");
//			localHashMap.put("productId", "0143000006234");
//			localHashMap.put("userId", userId);
//			String str1 = ValidateUtils.a(localHashMap, "asc");
//			String mac = ValidateUtils.b(str1 + "0102030405060708");
//			System.out.println(mac);
//			params.put("mac", mac);
//			String orderUrl = API_URL.ZSYH_ADDRESS1 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL3;
//			String getResult = HttpUtils.postUrl4ZSYH(orderUrl, params);
//			System.out.println(getResult);
//		}



		//10元风暴抢购代码
//		while (true) {
//			String productUrl = API_URL.ZSYH_ADDRESS3 + API_URL.ZSYH.LAGANXIANG;
//			String str1 = "9861c5e60f3c48fd98579918c4c44a1f";
//			String str2 = UUID.randomUUID().toString();
//			Map params = new HashMap();
//			params.put("goodsId", "4209");
//			params.put("campaignNo", "782e908f-8e75-4c33-adc2-1ce93a8f69ad");
//			params.put("cGName", "SYFBJFZX201611");
//			params.put("sign", getSign("SYFBJFZX201611",
//					"782e908f-8e75-4c33-adc2-1ce93a8f69ad",
//					"4209",
//					str2,
//					str1));
//			params.put("r", str2);
////		params.put("r", "36feed55-f8ee-40ba-a759-ec6c9dd692ae");
//
//			params.put("p0", "a");
//			params.put("p1", "50");
//			params.put("p2", "meizu");
//			params.put("p3", "c799040f6dea4b8dbcffb74eaf680e0cc");
//			params.put("p4", "58baa6a9a21b4b3e9773d0b2c28e0c11");
//			params.put("p5", str1);
//			params.put("p6", "508489926");
//			params.put("p7", "e92827f6fa9e4c78ae5f8e35407a9e72");
//			params.put("p8", "863ffee0f74b42b7be81d17002c61407");
//			params.put("p9", "7a85514ed46147539cf8631fe9d07f15");
//			params.put("p10", "eb0cd253da37484f960c0c4874b536bb");
//
//			String result = HttpUtils.postUrl4ZSYH(productUrl, params);
//			System.out.println(result);
//
//		}

		String productUrl = API_URL.ZSYH_ADDRESS3 + API_URL.ZSYH.TENSTORM;
		Map params = new HashMap();
		params.put("token", "exjo5WpwK1PLlIPRtvPhcGa%2FVwNmRj1I1nNdup2%2FLZBRLvORdwwTCQeUx99zFbDNnbxl44JDxkeGH0p9l0ip7Sd59tsHrN1YYvmrCTks%2BAQkoJ%2B3ygHhbi%2F5bCZ9BMbs1GWdCaweGFN5s1J16R8Jx9vCpVD47WGwpzHYTiYnX%2BA%3D");
		params.put("goodsId", "3214");
		params.put("campaignNo", "a4515cf5-9a53-444a-9f42-ca57a1f57738");
		params.put("cGName", "TENSTORM");
		String result = HttpUtils.postUrl4ZSYH(productUrl, params);
		System.out.println(result);

	}

	public static String getSign(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
	{
		paramString5 = MD5Tools.getMd5LowerCase("Pb5.0" + paramString5);
		paramString1 = MD5Tools.getMd5LowerCase(paramString1 + paramString2 + paramString3 + paramString4 + paramString5);
		return paramString1.toLowerCase();

	}
}