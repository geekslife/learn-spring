package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by geekslife on 2017. 1. 11..
 */
@Configuration
public class CountingDaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao( connectionMaker() );
    }

    @Bean
    public ConnectionMaker connectionMaker() {
        return new CountingConnectionMaker(readConnectionMaker());
    }

    @Bean
    public ConnectionMaker readConnectionMaker() {
        return new DConnectionManager();
    }
}
