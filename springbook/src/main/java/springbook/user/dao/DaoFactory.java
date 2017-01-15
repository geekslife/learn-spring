package springbook.user.dao;

import org.h2.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * Created by geekslife on 2017. 1. 8..
 */
@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        String connectionUrl = "jdbc:h2:tcp://localhost/~/default";
        dataSource.setUrl(connectionUrl);
        dataSource.setUsername("");
        dataSource.setPassword("");

        return dataSource;
    }
}
