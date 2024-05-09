package com.my;

import reactor.core.publisher.Flux;

import java.util.List;

public class AsynchronousAndMultiThreaded {
    public static void main(String[] args) {
//        Flux<String> bridge = Flux.create(sink -> {
//            myEventProcessor.register(
//                    new MyEventListener<String>() {
//
//                        public void onDataChunk(List<String> chunk) {
//                            for(String s : chunk) {
//                                sink.next(s);
//                            }
//                        }
//
//                        public void processComplete() {
//                            sink.complete();
//                        }
//                    });
//        });
    }

    interface MyEventListener<T> {
        void onDataChunk(List<T> chunk);
        void processComplete();
    }
}
