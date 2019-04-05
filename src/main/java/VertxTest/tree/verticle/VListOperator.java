package VertxTest.tree.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.List;

public class VListOperator extends AbstractVerticle {
    public void start() {

        System.out.println("VListOperator " + Thread.currentThread().getName());
        vertx.eventBus().consumer(ListOperator.LIST_OPERATOR_V, message -> {
            System.out.println("VListOperator received message: " + message.body() + " T " + Thread.currentThread().getName());
            ITreeElement body = (ITreeElement) message.body();
            Future<List<Integer>> res = body.getRes(vertx);
            res.setHandler(event -> message.reply(event.result()));
        });
    }
}