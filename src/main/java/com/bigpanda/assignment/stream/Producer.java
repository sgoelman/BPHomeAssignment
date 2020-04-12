package com.bigpanda.assignment.stream;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;


@Component
public class Producer extends Flowable<String> {

    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(10000);

    private Consumer consumer;

    public Producer(@Autowired Consumer consumer) {
        this.consumer = consumer;
        this.subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.single())
                .safeSubscribe(consumer);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        System.out.println("application is started up");
        this.startProduceData();

    }

    @Override
    protected void subscribeActual(Subscriber subscriber) {
        subscriber.onSubscribe(new Subscription() {

            private volatile boolean cancelled = false;


            @Override
            public void request(long n) {

                while (!cancelled) {
                    try {
                        if (queue.size() > 0) {
                            subscriber.onNext(queue.take());
                        }
                    } catch (InterruptedException e) {
                        subscriber.onError(e);
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void cancel() {
                cancelled = true;
                subscriber.onComplete();
            }
        });
    }


    public void startProduceData() {
        CompletableFuture.runAsync(() -> {
            try {
                startStream();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    private void startStream() throws IOException,
            InterruptedException {

        ProcessBuilder processBuilder = new ProcessBuilder();
        String base = System.getProperty("user.dir");
        String amd64 = ("\\src\\main\\resources\\generator-windows-amd64.exe");
        processBuilder.command(base + amd64);

        try {
            Process process = processBuilder.start();
            // blocked :(
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                queue.put(line);
            }
            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}
