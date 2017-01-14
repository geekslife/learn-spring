package tv;

import reactor.core.publisher.Flux;

/**
 * Created by geekslife on 2017. 1. 14..
 */
public class ReactorEx {
    public static void main(String[] args) {
        Flux.<Integer>create(e-> {
            e.next( 1 );
            e.next( 2 );
            e.next( 3 );
            e.complete();
        })
                .log()
                .map( s-> s*10 )
                .filter(s->s>10)
                .reduce( 0 , (a,b) -> a+b)
                .log()
                .subscribe(System.out::println);
    }
}
