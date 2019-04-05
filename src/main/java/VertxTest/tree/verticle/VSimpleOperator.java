package VertxTest.tree.verticle;

import VertxTest.tree.model.operator.ListOperator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.List;

public class VSimpleOperator extends AbstractVerticle {
    public void start() {

        System.out.println("VSimpleOperator " + Thread.currentThread().getName());
        vertx.eventBus().consumer(ListOperator.ADDRESS, message -> {
//            System.out.println("VSimpleOperator received message: " + message.body() + " T " + Thread.currentThread().getName());
            TreeContext body = (TreeContext) message.body();
            Future<List<Integer>> res = body.getElement().getRes(vertx,body.getParams());
            res.setHandler(event -> message.reply(event.result()));
        });
    }
}