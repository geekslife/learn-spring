package springbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * Created by geekslife on 2017. 1. 16..
 */
public class Calculator {

    <T> T lineReadTemplate(String path, Function<String,T> mapper, T init, BinaryOperator<T> func) throws IOException {
        try ( Stream<String> stream = Files.lines(Paths.get(path)) ) {
//            return stream.mapToInt(Integer::parseInt).reduce(init,func);
            return stream.map(mapper).reduce(init, func);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
        }
    }

    public int calcSum(String path) throws IOException {
        return lineReadTemplate( path, Integer::parseInt, 0, (a,b) -> (a+b) );
    }

    public int calcMul(String path) throws IOException {
        return lineReadTemplate( path, Integer::parseInt, 1, (a,b) -> (a*b) );
    }

    public String concat(String path) throws IOException {
                return lineReadTemplate( path, Function.identity(), "", (a,b)->(a+b) );
    }
}
