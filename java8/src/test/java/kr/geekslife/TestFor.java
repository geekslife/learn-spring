package kr.geekslife;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestFor {

    class ReturnClass {

        List<String> data;

        ReturnClass(List<String> data) {
            this.data = data;
        }

        String foo() {
            for (String s : data) {
                if (s.equals("foo")) {
                    return s+"!";
                }
            }
            return null;
        }

        String foo2() {
            return data.stream().filter(s -> s.equals("foo")).findFirst()
                    .map(s -> s + "!").orElse(null);
        }
    }

    @Test
    public void returnTest() {
        String[] data = {"bar"};
        ReturnClass cls = new ReturnClass(Arrays.asList(data));
        assertNull(cls.foo());


        String[] data2 = {"foo","bar"};
        ReturnClass cls2 = new ReturnClass(Arrays.asList(data2));
        assertEquals("foo!",cls2.foo());
    }

    @Test
    public void returnTest2() {
        String[] data = {"bar"};
        ReturnClass cls = new ReturnClass(Arrays.asList(data));
        assertNull(cls.foo());


        String[] data2 = {"foo","bar"};
        ReturnClass cls2 = new ReturnClass(Arrays.asList(data2));
        assertEquals("foo!",cls2.foo2());
    }
}
