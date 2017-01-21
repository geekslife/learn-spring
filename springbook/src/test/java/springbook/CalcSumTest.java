package springbook;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by geekslife on 2017. 1. 16..
 */
public class CalcSumTest {
    Calculator calculator;
    String path;

    @Before
    public void setUp() {
        calculator = new Calculator();
        path = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(path);
        assertThat(sum,is(10));
    }

    @Test
    public void mulOfNumbers() throws IOException {
        int mul = calculator.calcMul(path);
        assertThat(mul,is(24));
    }

    @Test
    public void concat() throws IOException {
        String result = calculator.concat(path);
        assertThat(result, is("1234"));
    }
}
