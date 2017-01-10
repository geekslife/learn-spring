package springbook.user.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by geekslife on 2017. 1. 11..
 */
public class UserDaoConnectionCountingTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(CountingDaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);

        CountingConnectionMaker ccm = context.getBean("connectionMaker",CountingConnectionMaker.class);
        ccm.makeNewConnection();
        System.out.printf("connection counter: " + ccm.getCounter());
    }
}
