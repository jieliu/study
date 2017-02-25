package com.tianma.data.repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.tianma.data.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by fiboliu on 16-8-29.
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private static final Logger log = LoggerFactory.getLogger(PersonRepositoryImpl.class);
    private static final String db = "payment";
    private static final String collectionName = "person";

    ApplicationContext annotationContext = new AnnotationConfigApplicationContext("com.tianma.data.config");
    private Mongo mongo = annotationContext.getBean(Mongo.class);// 创建bean的引用对象

    /**
     * 保存一个新的json串
     * @param person
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public int save(Person person) throws JsonProcessingException {

        if(person == null) {
            return -1;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        String personValue = objectMapper.writeValueAsString(person);
        Object personValueJson= JSON.parse(personValue);

        DBCollection collection = null;
        if (mongo.getDB(db).collectionExists(collectionName)) {

            collection = mongo.getDB(db).getCollection(collectionName);
            //insert session
            collection.insert((DBObject) personValueJson, WriteConcern.MAJORITY);
        } else {

            mongo.getDB(db).createCollection(collectionName, null);
            if(log.isInfoEnabled()) {
                log.info("create collection: " + collectionName);
            }
            mongo.getDB(db).getCollection(collectionName).save((DBObject) JSON.parse(personValue), WriteConcern.MAJORITY);
        }
        return 0;
    }

     /**
     *
     * @return
     * @throws IOException
     */
    @Override
    public Collection<Person> findAll() throws IOException {

        DBCollection collection = null;
        ObjectMapper objectMapper = new ObjectMapper();

        if (mongo.getDB(db).collectionExists(collectionName)) {

            collection = mongo.getDB(db).getCollection(collectionName);

            Cursor cursor = collection.find(new BasicDBObject());
            Collection<Person> collections = new ArrayList<Person>();

            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                Person person = objectMapper.convertValue(dbObject, new TypeReference<Person>() {

                });

                collections.add(person);
            }

            if(!collections.isEmpty()) {
                return collections;
            }
        }
        return null;
    }

    /**
     *
     * @param firstname
     * @return
     * @throws IOException
     */
    @Override
    public Collection<Person> findByFirstname(String firstname) throws IOException {

        DBCollection collection = null;
        ObjectMapper objectMapper = new ObjectMapper();

        if (mongo.getDB(db).collectionExists(collectionName)) {

            collection = mongo.getDB(db).getCollection(collectionName);

            BasicDBObject basicDBObject = new BasicDBObject("age", 26);
            Cursor cursor = collection.find(basicDBObject);

            Collection<Person> collections = new ArrayList<>();

            while (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                System.out.println("next");
                Person person = objectMapper.convertValue(dbObject, new TypeReference<Person>() {

                });

                collections.add(person);
            }

            if(!collections.isEmpty()) {
                return collections;
            }
        }
        return null;
    }

    /**
     *
     * @param index
     * @param perPageNumber
     * @return
     * @throws IOException
     */
    @Override
    public Collection<Person> findAllPaged(int index, int perPageNumber) throws IOException {

        DBCollection collection = null;
        ObjectMapper objectMapper = new ObjectMapper();

        if (mongo.getDB(db).collectionExists(collectionName)) {

            collection = mongo.getDB(db).getCollection(collectionName);

            Cursor cursor = collection.find();
            Collection<Person> collections = new ArrayList<Person>();

            //1. first step [begin, end]
            int i = 0;
            int begin = (index-1)*perPageNumber;
            int end = begin + perPageNumber;
            boolean flag = false;   //是否成功,存在两种情况：1. while循环正常结束，没有那么多数据 2 正常

            //2. 游标操作
            while (cursor.hasNext()) {
                i++;
                if(i > begin) {
                    flag = true;
                    DBObject dbObject = cursor.next();
                    Person person = objectMapper.convertValue(dbObject, new TypeReference<Person>() {

                    });
                    collections.add(person);
                }
                if(i > end) {
                    break;
                }
            }
            if(flag == true) {
                return  collections;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public int deleteAll() {
        if (mongo.getDB(db).collectionExists(collectionName)) {
            DBCollection collection = null;
            collection = mongo.getDB(db).getCollection(collectionName);
            WriteResult result = collection.remove(new BasicDBObject());
            return result.getN();
        }
        return -1;
    }

    /**
     *
     * @param firstname
     * @return
     * @throws IOException
     */
    @Override
    public int deleteAllByFirstname(String firstname) throws IOException {
        if (mongo.getDB(db).collectionExists(collectionName)) {
            DBCollection collection = null;
            collection = mongo.getDB(db).getCollection(collectionName);
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.append("firstaname", firstname);
            WriteResult result = collection.remove(basicDBObject);
            return result.getN();
        }
        return -1;
    }
}
