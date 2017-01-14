package kr.geekslife;

import org.springframework.util.StopWatch;

import java.util.Timer;

/**
 * Created by geekslife on 2017. 1. 15..
 */
public class StopWatchEx {
    public static void main(String[] args) throws InterruptedException {
        StopWatch watch = new StopWatch("watch1");
        watch.start("test1");
        for( int i=0; i<3; i++ ) {
            Thread.sleep(1000);
            System.err.println("tick");
        }
        watch.stop();
        System.err.println(watch.prettyPrint());
    }
}
