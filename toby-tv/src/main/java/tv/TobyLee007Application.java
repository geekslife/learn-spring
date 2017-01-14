package tv;

import com.sun.javafx.geom.transform.BaseTransform;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reactive Streams - Operator 를 추가하는 방법
 * Operator란 : Pub-Sub 사이의 데이터를 조작 또는 변환하는 일을 하는 인터페이스
 * Publisher -> Data1 -> Operator -> Data -> Op2 -> Data3 -> Subscriber
 * pub -> [Data1] -> mapPub -> [Data2] -> logSub
 *              <- subscribe(logSub)
 *              -> onSubscribe(s)
 *              -> onNext
 *              -> onNext
 *              -> onComplete
 *
 * Reactor 프로젝트의 사용법
 */
@Slf4j
public class TobyLee007Application {
    static class PubSub {
    }
    public static void main(String[] args) {
        Publisher<Integer> pub = iterPub(Stream.iterate(1, a->a+1).limit(10).collect(Collectors.toList()));
//        Publisher<Integer> mapPub = mapPub( pub, s-> s*10 );
//        Publisher<Integer> map2Pub = mapPub( mapPub, s-> -s );
//        Publisher<Integer> sumPub = sumPub(pub);

        Publisher<Integer> reducePub = reducePub(pub, 0, (a, b) -> a+b);
        reducePub.subscribe(logSub());
    }

    private static Publisher<Integer> reducePub(Publisher<Integer> pub, int init, BiFunction<Integer, Integer, Integer> bf) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                pub.subscribe(new DelegateSub(sub) {
                    int result = init;
                    @Override
                    public void onNext(Integer i) {
                        result =bf.apply(result, i);
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(result);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
        return sub -> pub.subscribe(new DelegateSub(sub){
            int sum = 0;

            @Override
            public void onNext(Integer i) {
                sum  += i;
            }

            @Override
            public void onComplete() {
                sub.onNext(sum);
                sub.onComplete();
            }
        });

    }

    private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {
        return sub ->
                pub.subscribe(new DelegateSub(sub) {
                    @Override
                    public void onNext(Integer i) { sub.onNext(f.apply(i)); }
                });
    }

    private static Subscriber<Integer> logSub() {
        return new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                log.debug("onSubscribe:");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.debug("onNext:{}", integer);
            }

            @Override
            public void onError(Throwable t) {
                log.debug("onError:{}", t);
            }

            @Override
            public void onComplete() {
                log.debug("onCompelte");
            }
        };
    }

    private static Publisher<Integer> iterPub(final Iterable<Integer> iter) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                sub.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
                        try {
                            iter.forEach(s -> sub.onNext(s));
                            sub.onComplete();
                        } catch (Throwable t) {
                            sub.onError(t);
                        }
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
    }
}
