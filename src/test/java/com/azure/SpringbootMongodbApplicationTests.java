package com.azure;

import com.azure.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpringbootMongodbApplicationTests {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public SpringbootMongodbApplicationTests(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // 1、创建集合
    @Test
    void testCreateCollection() {
        boolean exists = mongoTemplate.collectionExists("users");
        if (!exists) {
            mongoTemplate.createCollection("users");
        } else {
            mongoTemplate.dropCollection("users");
        }
    }


    // 2、插入文档
    @Test
    public void testInsertAndSave() {
/*        User user = new User(1, "azure", 25010.12, new Date());
        User save = mongoTemplate.save(user);//如果存在数据时更新数据，按id是否存在算
        System.out.println(save);*/
        List<User> users = Arrays.asList(new User(4, "azure", 2500.12, new Date()), new User(5, "azure", 2500.12, new Date()));
//        Collection<User> insert = mongoTemplate.insert(users, User.class);
        Collection<User> insert = mongoTemplate.insert(users, "users");
        System.out.println(insert);
    }


    // 3、查询文档
    @Test
    public void testFound() {
        List<User> users = mongoTemplate.findAll(User.class);
        users.forEach(System.out::println);
        List<User> users1 = mongoTemplate.findAll(User.class, "users");
        users1.forEach(System.out::println);

    }

}
