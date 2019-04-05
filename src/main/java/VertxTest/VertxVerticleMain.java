package VertxTest;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.util.concurrent.atomic.AtomicInteger;

public class VertxVerticleMain {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, event -> System.out.println(Thread.currentThread().getName() + " atomicInteger " + atomicInteger.get()));
        DeploymentOptions deploymentOptions = new DeploymentOptions();
        deploymentOptions.setInstances(3);
//        deploymentOptions.setIsolationGroup("readGR");
//        deploymentOptions.setMultiThreaded(true);
        deploymentOptions.setWorker(true);
        vertx.deployVerticle(EventBusReceiverVerticle.class,deploymentOptions);
        Thread.sleep(1000);
        for (int i = 0; i < 100; i++) {
            vertx.deployVerticle(new EventBusSenderVerticle(i), event -> {
                String s = event.result();
//                System.out.println(s);
                vertx.setTimer(10000, event1 -> {
                    vertx.undeploy(s);
//                    System.out.println("timer ev " + event1.toString());
                });
            });
//            Thread.sleep(1000);

//            vertx.deployVerticle(new BasicVerticle());


//        vertx.deployVerticle(new EventBusReceiverVerticle("R2"));
        }
        System.out.println(Thread.activeCount());
//        Thread.sleep(10000);
//        System.out.println(vertx.deploymentIDs());

    }
}