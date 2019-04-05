package VertxTest.tree.model;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;
import java.util.Map;

public abstract class ILeafNode implements ITreeElement {

    @Override
    public Future<List<Integer>> getRes(Vertx vertx,Map<String, String> params) {
        Future<List<Integer>> future = Future.future();
        vertx.eventBus().send(getAddress(),getParameters(), event -> future.complete((List<Integer>) event.result()));
        return future;
    }

    public abstract Object getParameters();
}
