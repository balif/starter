package VertxTest.treeModel;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;

public abstract class ILeafNode implements ITreeElement {

    @Override
    public Future<List<Integer>> getRes(Vertx vertx) {
        Future<List<Integer>> future = Future.future();
        vertx.eventBus().send(getAddress(),getParameters(),event -> future.complete((List<Integer>) event.result()));
        return future;
    }

    public abstract Object getParameters();
}
