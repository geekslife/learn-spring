package kr.geekslife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by geekslife on 2017. 2. 1..
 */
@Configuration
@PropertySource("classpath:test.properties")
public class FooConfiguration {
    @Inject
    private Environment environment;

    @Bean
    public Foo createFoo() {
        return new Foo(environment.getProperty("foo"));
    }
}
