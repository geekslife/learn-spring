package kr.geekslife;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by geekslife on 2017. 1. 31..
 */
public class JavaLangTest {
    @Test
    public void Class_isAssignable() {
        Integer i = 10;
        Assert.assertTrue(i.getClass().isAssignableFrom(Integer.class));
//        AssertionEerror 를 뱉는다
//        Assert.assertTrue(i.getClass().isAssignableFrom(int.class));
    }
}
