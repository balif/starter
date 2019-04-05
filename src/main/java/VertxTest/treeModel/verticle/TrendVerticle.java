package VertxTest.treeModel.verticle;

import VertxTest.treeModel.TrendLeaf;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.LinkedList;
import java.util.List;

public class TrendVerticle extends AbstractVerticle {
    public void start() {
        System.out.println("TrendVerticle " + Thread.currentThread().getName());

        vertx.eventBus().consumer(TrendLeaf.TREND_ENGINE_V, message -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            System.out.println("TrendVerticle received message: " + message.body() + " T " + Thread.currentThread().getName());
            List<Integer> list = new LinkedList();
            message.reply(list);
        });
    }
}