package VertxTest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.concurrent.atomic.AtomicInteger;

import static VertxTest.VertxVerticleMain.atomicInteger;

public class EventBusSenderVerticle extends AbstractVerticle {
    public static AtomicInteger senderCount = new AtomicInteger(0);

    private final int i;

    public EventBusSenderVerticle(int i) {
        this.i = i;
    }

    public void start() {
        System.out.println("senderCount.incrementAndGet() "+ senderCount.incrementAndGet());
//        vertx.eventBus().publish("anAddress", "message 2");
        vertx.setPeriodic(1000, event ->{
//                    System.out.println("EventBusSenderVerticle send");
                    atomicInteger.incrementAndGet();
                    vertx.eventBus().send("anAddress", "message " + i);
    }
        );
//        vertx.setTimer(10000,event -> {
//            System.out.println("context.deploymentID() " + context.deploymentID());
//            vertx.undeploy(context.deploymentID());
//        });
    }

    @Override
    public void stop() throws Exception {
        System.out.println(Thread.currentThread().getName() + " EventBusSenderVerticle stopped " +  senderCount.decrementAndGet());
    }
}