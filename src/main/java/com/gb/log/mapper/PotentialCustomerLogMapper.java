package com.gb.log.mapper;

import com.gb.log.entity.PotentialCustomerLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王一飞
 * @description: 产品咨询操作日志
 * @since 2021/3/12  10:03
 */
@Repository
public class PotentialCustomerLogMapper {

    /**
     * 由springboot自动注入，默认配置会产生mongoTemplate这个bean
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @description: 操作日志, 插入数据
     * @params: com.gb.product.entity.UserSpuConsulting
     * 　* @author wangyifei
     * 　* @date 2021/3/12
     */
    public PotentialCustomerLog insert(PotentialCustomerLog potentialCustomerLog) {
        //collectionName ： 存入集合名称
        return mongoTemplate.insert(potentialCustomerLog);
    }

    /**
     * @description: 操作日志, id查询
     * @returns: List
     * 　* @author wangyifei
     * 　* @date 2021/3/12
     */
    public List<PotentialCustomerLog> select(Query query) {
        return mongoTemplate.find(query, PotentialCustomerLog.class);
    }
}
