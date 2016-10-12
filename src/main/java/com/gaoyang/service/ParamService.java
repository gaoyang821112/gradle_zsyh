package com.gaoyang.service;

import com.gaoyang.bean.Param;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParamService {

    private static String PARAMS_COLLECTION = "params";

    @Autowired
    MongoTemplate mongoTemplate;

    public Map<String, String> getParamMap() {
        List<Param> paramList = mongoTemplate.findAll(Param.class, PARAMS_COLLECTION);
        Map<String, String> paramMap = new HashedMap();
        for (int i = 0; i < paramList.size(); i++) {
            paramMap.put(paramList.get(i).getName(), paramList.get(i).getValue());
        }
        return paramMap;
    }



}