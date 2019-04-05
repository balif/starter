package VertxTest.tree.verticle;

import io.vertx.core.AbstractVerticle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class VTrend extends AbstractVerticle {
    public void start() {
        System.out.println("VTrend " + Thread.currentThread().getName());

        vertx.eventBus().consumer(TrendLeaf.TREND_ENGINE_V, message -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            System.out.println("VTrend received message: " + message.body() + " T " + Thread.currentThread().getName());
            List<Integer> list = new LinkedList<>(Arrays.asList(1,2,3));
            message.reply(list);
        });
    }
}