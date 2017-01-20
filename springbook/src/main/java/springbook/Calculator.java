package springbook;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by geekslife on 2017. 1. 16..
 */
public class Calculator {
    public int calcSum(String path) throws IOException {
        try ( Stream<String> stream = Files.lines(Paths.get(path)) ) {
            return stream.mapToInt(Integer::parseInt).sum();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
        }
    }

    public int calcMul(String path) throws IOException {
        try ( Stream<String> stream = Files.lines(Paths.get(path)) ) {
            return stream.mapToInt(Integer::parseInt).reduce(1,(a,b) -> a*b);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw e;
        } finally {
        }
    }
}
