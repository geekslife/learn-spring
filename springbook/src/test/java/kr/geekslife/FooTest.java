package kr.geekslife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;

/**
 * Created by geekslife on 2017. 2. 1..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FooConfiguration.class)
public class FooTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void readingProperty() {

        Foo foo = context.getBean(Foo.class);
        Assert.assertThat(foo.bar, is("bar"));
        System.err.println(Arrays.asList(context.getEnvironment().getDefaultProfiles()));
    }
}
