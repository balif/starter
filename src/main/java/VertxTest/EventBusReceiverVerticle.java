package VertxTest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import static VertxTest.VertxVerticleMain.atomicInteger;

public class EventBusReceiverVerticle extends AbstractVerticle {

    private String name = null;

    public EventBusReceiverVerticle() {
        this.name = "RR";
    }

    public void start(Future<Void> startFuture) {
        System.out.println("R T " + Thread.currentThread().getName());

        vertx.eventBus().consumer("anAddress", message -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            atomicInteger.decrementAndGet();
            System.out.println(Thread.currentThread().getName() + " " + this.name +
                " received message: " +
                message.body());
        });
    }
}