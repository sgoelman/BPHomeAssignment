package com.bigpanda.assignment.stream;

import com.bigpanda.assignment.logic.BusinessLogic;
import io.reactivex.subscribers.DefaultSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer extends DefaultSubscriber<String> {
    @Autowired
    private BusinessLogic businessLogic;

    @Override
    protected void onStart() {
        request(1);
    }

    @Override
    public void onNext(String message) {
        businessLogic.consumeMessage(message);
        request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("error received" + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("consumer finished");
    }

}
