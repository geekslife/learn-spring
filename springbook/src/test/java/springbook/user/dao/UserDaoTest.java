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
import java.util.List;

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
    public void getAll() throws Exception {
        dao.deleteAll();

        List<User> user0 = dao.getAll();
        assertThat(user0.size(),is(0));

        dao.add(user1);
        List<User> users1 = dao.getAll();
        assertThat(users1.size(), is(1));

        dao.add(user2);
        List<User> users2 = dao.getAll();
        assertThat(users2.size(), is(2));
        checkSameUser(user2, users2.get(0));
        checkSameUser(user1, users2.get(1));

        dao.add(user3);
        List<User> users3 = dao.getAll();
        assertThat(users3.size(), is(3));
        checkSameUser(user2, users3.get(0));
        checkSameUser(user1, users3.get(1));
        checkSameUser(user3, users3.get(2));

    }

    private  void checkSameUser(User user1, User user2) {
        System.err.println("user1="+user1+",user2="+user2);
        assertThat(user1.getId(), is(user2.getId()));
        assertThat(user1.getName(), is(user2.getName()));
        assertThat(user1.getPassword(), is(user2.getPassword()));
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