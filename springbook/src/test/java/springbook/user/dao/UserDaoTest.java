package springbook.user.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springbook.user.domain.User;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by geekslife on 2017. 1. 8..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoFactory.class})
public class UserDaoTest {
    @Autowired ApplicationContext context;
    @Autowired UserDao dao;
    User user1, user2, user3;

    @Before
    public void setUp() {
        user1 = new User("gyumee","박성철","springno1");
        user2 = new User("geekslife","haes","springno2");
        user3 = new User("lulumeme","자자자","springno3");
    }

    @After
    public void tearDown() throws SQLException {
        dao.deleteAll();
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void addAndGet() throws SQLException, ClassNotFoundException {

        assertThat(dao.getCount(),is(0));
        dao.add( user1 );
        dao.add( user2 );
        assertThat(dao.getCount(),is(2));

        User userget1 = dao.get(user1.getId());

        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getPassword(), is(user2.getPassword()));
        dao.deleteAll();
        assertThat(dao.getCount(),is(0));

        dao.get("foo");
    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao",UserDao.class);

        assertThat(dao.getCount(), is(0));

        dao.add(user1);

        assertThat(dao.getCount(), is(1));


        dao.add(user2);

        assertThat(dao.getCount(), is(2));

        dao.deleteAll();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JUnitCore.main("springbook.user.dao.UserDaoTest");
    }

}