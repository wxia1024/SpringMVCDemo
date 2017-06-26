package com.xiawei.repository;

import com.xiawei.model.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * Created by xiawei on 2017/6/16.
 */
public class UserDAO {
//    public static List<UserEntity> getUsers(){
//        ApplicationContext context = SpringContextUtil.getApplicationContext();
//        EntityManager entityManager = (EntityManager)context.getBean("entityManagerFactory");
//        List<UserEntity> userEntityList = entityManager.createNativeQuery("SELECT * FROM user", UserEntity.class).getResultList();
//        return userEntityList;
//    }

    public static List<UserEntity> getUsers(){
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        List<Object[]> userEntityList = session.createSQLQuery("SELECT * FROM user u")
                //.setResultTransformer(Transformers.aliasToBean(UserEntity.class))
                //.addEntity("u", UserEntity.class)
                .list();
        ArrayList<UserEntity> result = new ArrayList<>();

         UserEntity userEntity = new UserEntity();
        for (Object[] user : userEntityList) {

            userEntity.setId((int)user[0]);
            userEntity.setNickname((String)user[1]);
            userEntity.setPassword((String)user[2]);
            userEntity.setFirstName((String)user[3]);
            userEntity.setLastName((String)user[4]);
            //userEntity.setBlogsById((Collection<BlogEntity>)user[5]);
            result.add(userEntity);
        }
        return result;
        //return userEntityList;
    }
}
