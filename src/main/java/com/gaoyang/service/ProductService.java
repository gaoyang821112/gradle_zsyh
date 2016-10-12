package com.gaoyang.service;

import com.gaoyang.bean.Product;
import com.gaoyang.bean.Product1;
import com.gaoyang.bean.Product2;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
	private static String PRODUCT_NINE_COLLECTION = "product1";
	
	private static String PRODUCT_JIFENTAO_COLLECTION = "product2";

	@Autowired
	MongoTemplate mongoTemplate;

	public void saveProduct(Object product) {
		if (product instanceof com.gaoyang.bean.Product1) {
			this.mongoTemplate.save(product, PRODUCT_NINE_COLLECTION);
		} else if (product instanceof com.gaoyang.bean.Product2) {
			this.mongoTemplate.save(product, PRODUCT_JIFENTAO_COLLECTION);
		}
	}

	public String updateRemaiding(String type, String productNo) {
		Criteria criteria = Criteria.where("productNo").is(productNo);
		Query query = new Query(criteria);
		Class clazz = null;
		if (type.equals("1")) {
			clazz = Product1.class;
		} else if (type.equals("2")) {
			clazz = Product2.class;
		}
		List<Product> productList = this.mongoTemplate.find(query, clazz);
		if (productList.size() > 0) {
			Product product = (Product) productList.get(0);
			String isRemained = "0";
			if (product.getIsRemained().equals("0")) {
				isRemained = "1";
			} else {
				isRemained = "0";
			}
			Update update = Update.update("isRemained", isRemained);
			this.mongoTemplate.updateFirst(query, update, clazz);
			return isRemained;
		} else {
			return "木有";
		}
		
	}

	/**
	 * 查询并更新库存，开售开始时间和开售结束时间
	 * 删除开售结束时间小于当前时间的
	 * @param product
	 */
	public void updateProduct1(Product1 product) {
		try {
			Criteria criteria = Criteria.where("productNo").is(product.getProductNo());
			Query query = new Query(criteria);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateTime1 = dateFormat.parse(product.getValidityPerEnd() + " 23:59:59");
			Date dateTime2 = new Date();
			if (dateTime1.compareTo(dateTime2) < 0) {
				this.mongoTemplate.remove(query, PRODUCT_NINE_COLLECTION);
			} else {
				Update update = Update.update("btnStatus", product.getBtnStatus())
						.set("btnText", product.getBtnText())
						.set("validityPerBegin", product.getValidityPerBegin())
						.set("validityPerEnd", product.getValidityPerEnd());
				this.mongoTemplate.updateFirst(query, update, Product1.class);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void updateProduct2(Product2 product) {
		try {
			Criteria criteria = Criteria.where("productNo").is(product.getProductNo());
			Query query = new Query(criteria);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateTime1 = dateFormat.parse(product.getSaleEndTime());
			Date dateTime2 = new Date();
			if (dateTime1.compareTo(dateTime2) < 0) {
				this.mongoTemplate.remove(query, PRODUCT_JIFENTAO_COLLECTION);
			} else {
				Update update = Update.update("stock", product.getStock())
						.set("saleBeginTime", product.getSaleBeginTime())
						.set("saleEndTime", product.getSaleEndTime());
				this.mongoTemplate.updateFirst(query, update, Product2.class);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有比赛并且删除已经过期 或者激活状态为0的商品
	 */
	public void removePastProducts1() {
		try {
			List<Product1> productList = (List<Product1>)mongoTemplate.findAll(Product1.class);
			for (Product1 product : productList) {
				Criteria criteria = Criteria.where("productNo").is(product.getProductNo());
				Query query = new Query(criteria);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateTime1 = dateFormat.parse(product.getValidityPerEnd() + " 23:59:59");
				Date dateTime2 = new Date();
				if (dateTime1.compareTo(dateTime2) < 0) {
					this.mongoTemplate.remove(query, PRODUCT_NINE_COLLECTION);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void removePastProducts2() {
		try {
			List<Product2> productList = (List<Product2>)mongoTemplate.findAll(Product2.class);
			for (Product2 product : productList) {
				Criteria criteria = Criteria.where("productNo").is(product.getProductNo());
				Query query = new Query(criteria);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date dateTime1 = dateFormat.parse(product.getSaleEndTime());
				Date dateTime2 = new Date();
				if (dateTime1.compareTo(dateTime2) < 0) {
					this.mongoTemplate.remove(query, PRODUCT_JIFENTAO_COLLECTION);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按照商品编号查找商品
	 * @param productNo
	 * @return
	 */
	public Product1 findProductByNo1(String productNo) {
		return (Product1) this.mongoTemplate.findOne(
				new Query(Criteria.where("productNo").is(productNo)), Product1.class);
	}
	
	public Product2 findProductByNo2(String productNo) {
		return (Product2) this.mongoTemplate.findOne(
				new Query(Criteria.where("productNo").is(productNo)), Product2.class);
	}

	/**
	 * 按照商品名称查找商品
	 * @param name
	 * @return
	 */
	public Product1 findProductByName1(String name) {
		return (Product1) this.mongoTemplate.findOne(
				new Query(Criteria.where("productName").is(name)), Product1.class,
				PRODUCT_NINE_COLLECTION);
	}
	
	public Product2 findProductByName2(String name) {
		return (Product2) this.mongoTemplate.findOne(
				new Query(Criteria.where("name").is(name)), Product2.class,
				PRODUCT_JIFENTAO_COLLECTION);
	}

	/**
	 * 按照商品编号查看商品是否存在
	 * @param productNo
	 * @return
	 */
	public boolean isExistProductByProductNo1(String productNo) {
 		Product1 product = (Product1) this.mongoTemplate.findOne(new Query(
				Criteria.where("productNo").is(productNo)), Product1.class);
		if (product != null) {
			return true;
		}
		return false;
	}
	
	public boolean isExistProductByProductNo2(String productNo) {
		Product2 product = (Product2) this.mongoTemplate.findOne(new Query(
				Criteria.where("productNo").is(productNo)), Product2.class);
		if (product != null) {
			return true;
		}
		return false;
	}

	/**
	 * 按照商品名称查看商品是否存在
	 * @param name
	 * @return
	 */
	public boolean isExistProductByName1(String name) {
		Product1 product = (Product1) this.mongoTemplate.findOne(new Query(
				Criteria.where("productName").is(name)), Product1.class);
		if (product != null) {
			return true;
		}
		return false;
	}
	
	public boolean isExistProductByName2(String name) {
		Product2 product = (Product2) this.mongoTemplate.findOne(new Query(
				Criteria.where("name").is(name)), Product2.class);
		if (product != null) {
			return true;
		}
		return false;
	}

	public void saveComment(JSONObject jsonObject) {
		this.mongoTemplate.save(jsonObject, "comment_article");
	}
}