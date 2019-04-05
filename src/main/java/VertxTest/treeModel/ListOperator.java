package VertxTest.treeModel;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.LinkedList;
import java.util.List;

public class ListOperator extends ITreeNode {

    public static final String LIST_OPERATOR_V = "listOperatorV";

    public ListOperator(List<ITreeElement> elementList) {
        super(elementList);
    }

    @Override
    public Future<List<Integer>> getRes(Vertx vertx) {
        Future<List<List<Integer>>> r = getFuture(vertx);
        return r.map(lists -> {
            List<Integer> l = new LinkedList<>();
            lists.forEach(integers -> integers.addAll(integers));
            return l;
        });
    }

    @Override
    public String getAddress() {
        return LIST_OPERATOR_V;
    }
}
