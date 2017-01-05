package com.gaoyang.service;

import com.gaoyang.bean.BaojieUser;
import com.gaoyang.bean.DianPingUser;
import com.gaoyang.bean.Product1;
import com.gaoyang.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private static String PRODUCT_USER_COLLECTION = "user";

	private static String PRODUCT_BAOJIE_USER_COLLECTION = "baojie_user";

	private static String PRODUCT_DIANPING_USER_COLLECTION = "dianping_user";

	@Autowired
	MongoTemplate mongoTemplate;

	public List<User> getUserList() {
		return mongoTemplate.findAll(User.class, PRODUCT_USER_COLLECTION);
	}

	public User findUserByUserId(String userId) {
		return mongoTemplate.findOne(new Query(Criteria.where("userId").is(userId)), User.class, PRODUCT_USER_COLLECTION);
	}

	public List<BaojieUser> getBaojieUserList() {
		return mongoTemplate.findAll(BaojieUser.class, PRODUCT_BAOJIE_USER_COLLECTION);
	}

	public List<DianPingUser> getDianPingList() {
		return mongoTemplate.findAll(DianPingUser.class, PRODUCT_DIANPING_USER_COLLECTION);
	}

	public void update(String tel, String column, String value) {
		Query query = new Query(Criteria.where("tel").is(tel));
		Update update = Update.update(column, value);
		this.mongoTemplate.updateFirst(query, update, PRODUCT_DIANPING_USER_COLLECTION);


	}


}