package VertxTest.tree.model;

import VertxTest.tree.verticle.TreeContext;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ITreeNode implements ITreeElement {

    public static final String ADDRESS = "VSimpleOperator";

    public List<Future<List<Integer>>> getSubNodeRes(Vertx vertx, List<ITreeElement> elementList, Map<String, String> params) {
        List<Future<List<Integer>>> futureList = elementList.stream()
                .map(iTreeElement -> sendTreeNode(vertx, iTreeElement, params))
                .collect(Collectors.toList());
        return futureList;
    }

    public Future<List<Integer>> sendTreeNode(Vertx vertx, ITreeElement iTreeElement, Map<String, String> params) {
        Future<List<Integer>> future = Future.future();
        vertx.eventBus().send(iTreeElement.getAddress(), new TreeContext(iTreeElement, params), event -> {
            List<Integer> r = (List<Integer>) event.result().body();
            future.complete(r);
        });
        return future;
    }

    public Future<List<List<Integer>>> getFuture(Vertx vertx, List<ITreeElement> elementList, Map<String, String> params) {
        if(elementList==null){
            Future<List<List<Integer>>> f = Future.future();
            f.complete(new LinkedList());
            return f;
        }
        List<Future> futureList = elementList.stream()
                .map(iTreeElement -> sendTreeNode(vertx, iTreeElement, params))
                .collect(Collectors.toList());
        Future future = join(futureList);
        return future;
    }

    public Future<List<Integer>> join(List<Future> futureList) {
        Future future = Future.future();
        CompositeFuture.join(futureList).setHandler(event -> {
            List<List<Integer>> resList = futureList.stream()
                    .map(f -> (List<Integer>) f.result()).collect(Collectors.toList());
            future.complete(resList);
        });
        return future;
    }

    @Override
    public String getAddress() {
        return ADDRESS;
    }

}
