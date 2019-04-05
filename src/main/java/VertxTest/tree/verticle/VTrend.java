package VertxTest.tree.verticle;

import VertxTest.tree.model.engine.TrendLeaf;
import io.vertx.core.AbstractVerticle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class VTrend extends AbstractVerticle {
    public static AtomicInteger senderCount = new AtomicInteger(0);
    public void start() {
        System.out.println("VTrend " + Thread.currentThread().getName());
        Random r = new Random();
        vertx.eventBus().consumer(TrendLeaf.TREND_ENGINE_V, message -> {
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {

            }
            senderCount.incrementAndGet();
//            System.out.println("VTrend received message: " + message.body() + " T " + Thread.currentThread().getName());
            List<Integer> list = new LinkedList<>(Arrays.asList(1,2,3));
            message.reply(list);
        });
    }
}