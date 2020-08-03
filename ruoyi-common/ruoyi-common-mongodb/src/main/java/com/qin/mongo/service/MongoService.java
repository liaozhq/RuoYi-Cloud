package com.qin.mongo.service;

import com.qin.mongo.configure.MongoPageHelper;
import com.qin.mongo.configure.PageResult;
import com.qin.mongo.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.List;

@Service
public class MongoService<T extends BaseModel> {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private MongoPageHelper pageHelper;

    private Class<T> clazz;

    public void init(Class<T> clazz){
        this.clazz = clazz;
    }

    public PageResult<T> pageQuery(Query query, int pageNum){
        return pageHelper.pageQuery(query, this.clazz, 10 ,pageNum);
    }

    public PageResult<T> pageQuery(Query query, int pagesize, int pageNum){
        return pageHelper.pageQuery(query, this.clazz, pagesize ,pageNum);
    }



    public void save(T t){
        t.setCreateTime(Calendar.getInstance().getTime());
        t.setUpdateTime(Calendar.getInstance().getTime());
        template.save(t);
    }

    public void delete(T t){
        template.remove(t);
    }

    public void deleteById(String id,  Class<T> clazz){
        T t = getById(id);
        delete(t);
    }

    public List<T> findall(){
        return template.findAll(this.clazz);
    }

    public T getById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return template.findOne(query, this.clazz);
    }

    public List<T> find(Query query){
        return template.find(query, this.clazz);
    }
}
