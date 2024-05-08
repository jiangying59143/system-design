package org.example.java9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReactiveSteamTest {

    private final static String SEPARATOR = " -> ";

    public static void main(String[] args) throws InterruptedException {
        SubmissionPublisher<String> publisher = new SubmissionPublisher();

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>() {
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                System.out.println("订阅开始");
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.print("数据 : ");
                System.out.println(item);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("错误");
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("订阅结束");
            }
        };



//        publisher.subscribe(subscriber);
        MyProcessor myProcessor = new MyProcessor("myProcessor"),
                myProcessor1 = new MyProcessor("myProcessor1"),
                myProcessor2 = new MyProcessor("myProcessor2");
        publisher.subscribe(myProcessor);
        myProcessor.subscribe(myProcessor1);
        myProcessor1.subscribe(myProcessor2);
        myProcessor2.subscribe(subscriber);

        System.out.println("myProcessor: " + myProcessor);
        System.out.println("myProcessor1: " + myProcessor1);
        System.out.println("myProcessor2: " + myProcessor2);
        System.out.println("subscriber: " + subscriber);


        for (int i = 0; i < 10; i++) {
            publisher.submit("p-"+i);
        }



        Thread.sleep(2000);

        publisher.close();

    }

    static class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<String,String>{

        private String subscriberName;

        private Flow.Subscription subscription;

        public MyProcessor(String subscriberName){
            this.subscriberName = subscriberName;
        }
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("订阅开始-processor");
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
//            System.out.print("数据-processor " + this + ": ");
            item = Stream.of(item.split(SEPARATOR))
                    .map(i -> {
                        if(i.startsWith("p-")) return subscriberName + SEPARATOR + i;
                        return i;
                    })
//                    .peek(System.out::println)
                    .collect(Collectors.joining(SEPARATOR));

//            item = subscriberName + " " + item;
            this.submit(item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("错误-processor");
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.println("订阅完成-processor");
        }
    }
}
