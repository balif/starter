package VertxTest.tree.verticle;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ITreeNode implements ITreeElement {

    private final List<ITreeElement> elementList;

    public ITreeNode(List<ITreeElement> elementList) {
        this.elementList = elementList;
    }

    public List<Future<List<Integer>>> getSubNodeRes(Vertx vertx) {
        List<Future<List<Integer>>> futureList = elementList.stream()
                .map(iTreeElement -> sendTreeNode(vertx, iTreeElement))
                .collect(Collectors.toList());
        return futureList;
    }


    public Future<List<Integer>> sendTreeNode(Vertx vertx, ITreeElement iTreeElement) {
        Future<List<Integer>> future = Future.future();
        vertx.eventBus().send(iTreeElement.getAddress(), iTreeElement, event -> {
            List<Integer> r = (List<Integer>) event.result().body();
            future.complete(r);
        });
        return future;
    }

    public Future<List<List<Integer>>> getFuture(Vertx vertx ){
        List<Future> futureList = elementList.stream()
                .map(iTreeElement -> sendTreeNode(vertx, iTreeElement))
                .collect(Collectors.toList());
        Future future = Future.future();
        CompositeFuture.join(futureList).setHandler(event -> {
            List<List<Integer>> resList = futureList.stream()
                    .map(f -> (List<Integer>) f.result()).collect(Collectors.toList());
            future.complete(resList);
        });
        return future;
    }


}
