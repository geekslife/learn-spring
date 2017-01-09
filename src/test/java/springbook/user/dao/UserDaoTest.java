package springbook.user.dao;

import org.junit.Test;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);

        User user = new User();
        user.setId("whiteship");
        user.setName("BAIK");
        user.setPassword("abc");

//        dao.add( user );

        System.out.println(user.getId() + " registered.");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " queried.");
    }

}