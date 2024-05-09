package com.my;

import org.reactivestreams.Subscription;
import org.w3c.dom.ls.LSOutput;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args) throws IOException {
//        mono();
        flux();
    }

    public static void mono(){
        Mono<Integer> just = Mono.just(1);
        just.subscribe(e -> System.out.println("m->"+e));
    }
    public static void flux() throws IOException {
//        Flux<Integer> just = Flux.just(1,2,3,4,5);
//        just.subscribe(e-> System.out.println("e1->" + e));
//        just.subscribe(e-> System.out.println("e2->" + e));

        //每秒产生一个数据
//        Flux.interval(Duration.ofSeconds(1)).subscribe(e-> System.out.println(e));


//        Flux<Integer> empty = Flux.empty();
//        empty
//                .doOnComplete(()->System.out.println("empty 结束"))
//                .subscribe(System.out::println);
//        System.in.read();

//        Flux<Integer> stream = Flux.range(1,7)
//                .map(ele -> {
//                    if(ele == 7) return 8;
//                    return ele;
//                })
//                .doOnComplete(()-> System.out.println("完成了"));
//        stream.subscribe(
//                System.out::println
//        );


//        Flux.range(1,7)
//                .log()
//                .filter(i -> i > 3)
//                .map(i -> "..." + i)
//                .subscribe(System.out::println);

//        Flux.range(1, 1000)
//                .log()
//                //限流触发，看上游是怎么限流获取数据的
//                .subscribe();

//        Flux.range(1,7)
//                .map(i->{
//                    if(i == 5) return i/0;
//                    return i;})
//                .subscribe(
//                        v-> System.out.println("v = " + v),
//                        Throwable::printStackTrace,
//                        ()-> System.out.println("结束")
//                );

        // 异常处理
//        Flux.range(1,7)
//                .map(i->{
//                    if(i == 5) return i/0;
//                    return i;})
//                .onErrorReturn(ArithmeticException.class, 100)
//                .subscribe(
//                        v-> System.out.println("v = " + v),
//                        Throwable::printStackTrace,
//                        ()-> System.out.println("结束")
//                );

//        Flux.range(1,7)
//                .map(i->{
//                    if(i == 5) return i/0;
//                    return i;})
//                .onErrorContinue((err,item)-> {
//                    err.printStackTrace();
//                    System.out.println("错误数据----"+item);
//                })
//                .subscribe(
//                        v-> System.out.println("v = " + v),
//                        Throwable::printStackTrace,
//                        ()-> System.out.println("结束")
//                );

//        Flux.range(1,7)
//                .map(i->{
//                    if(i == 5) return i/0;
//                    return i;})
//                .doOnError((err)-> {
//                    err.printStackTrace();
//                })
//                .subscribe(
//                        v-> System.out.println("v = " + v),
//                        Throwable::printStackTrace,
//                        ()-> System.out.println("结束")
//                );

//        Flux.range(1,7)
//                .map(i->{
//                    if(i == 5) return i/0;
//                    return i;})
//                .doOnError((err)-> {
//                    err.printStackTrace();
//                })
//                .subscribe(
//                        new BaseSubscriber<Integer>() {
//                            @Override
//                            protected void hookOnSubscribe(Subscription subscription) {
//                                super.hookOnSubscribe(subscription);
//                                requestUnbounded();
//                            }
//
//                            @Override
//                            protected void hookOnNext(Integer value) {
//                                super.hookOnNext(value);
//                                System.out.println(value);
//                            }
//
//                            @Override
//                            protected void hookOnComplete() {
//                                super.hookOnComplete();
//                            }
//
//                            @Override
//                            protected void hookOnError(Throwable throwable) {
//                                super.hookOnError(throwable);
//                            }
//
//                            @Override
//                            protected void hookOnCancel() {
//                                super.hookOnCancel();
//                            }
//
//                            @Override
//                            protected void hookFinally(SignalType type) {
//                                super.hookFinally(type);
//                            }
//                        }
//                );

//        Flux.range(1,10)
//                .buffer(3)
//                .subscribe(v-> System.out.println(v.getClass() + " v = " + v));

        Flux.range(1,100)
                .log()
                .limitRate(10)
                .subscribe();
        System.in.read();
        //
        
//        Flux.range(1, 1000)
//                .log()
//                //限流触发，看上游是怎么限流获取数据的
//                .limitRate(100) //一次预取30个元素； 第一次 request(100)，以后request(75)
//                .subscribe();
    }
}
