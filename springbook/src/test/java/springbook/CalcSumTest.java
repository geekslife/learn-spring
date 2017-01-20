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

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = calculator.calcSum(getClass().getResource("/numbers.txt").getPath());
        assertThat(sum,is(10));
    }

    @Test
    public void mulOfNumbers() throws IOException {
        int mul = calculator.calcMul(getClass().getResource("/numbers.txt").getPath());
        assertThat(mul,is(24));
    }
}
