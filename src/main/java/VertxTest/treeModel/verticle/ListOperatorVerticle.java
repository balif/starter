package VertxTest.treeModel.verticle;

import VertxTest.treeModel.ITreeElement;
import VertxTest.treeModel.ListOperator;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

import java.util.List;

public class ListOperatorVerticle extends AbstractVerticle {
    public void start() {
        System.out.println("ListOperatorVerticle " + Thread.currentThread().getName());

        vertx.eventBus().consumer(ListOperator.LIST_OPERATOR_V, message -> {
            System.out.println("ListOperatorVerticle received message: " + message.body() + " T " + Thread.currentThread().getName());
            ITreeElement body = (ITreeElement) message.body();
            Future<List<Integer>> res = body.getRes(vertx);
            res.setHandler(event -> message.reply(event.result()));
        });
    }
}