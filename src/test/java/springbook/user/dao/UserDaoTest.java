package springbook.user.dao;

import org.junit.Test;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
public class UserDaoTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionMaker connectionMaker = new DConnectionManager();
        UserDao dao = new UserDao(connectionMaker);

        User user = new User();
        user.setId("whiteship");
        user.setName("BAIK");
        user.setPassword("abc");

        dao.add( user );

        System.out.println(user.getId() + " registered.");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " queried.");
    }

}