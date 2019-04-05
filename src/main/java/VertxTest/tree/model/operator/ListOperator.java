package VertxTest.tree.model.operator;

import VertxTest.tree.model.ITreeElement;
import VertxTest.tree.model.ITreeNode;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListOperator extends ITreeNode {

    private final List<ITreeElement> elementList;

    public ListOperator(ITreeElement... elementList) {
        this.elementList = Stream.of(elementList).collect(Collectors.toList());
    }

    @Override
    public Future<List<Integer>> getRes(Vertx vertx, Map<String, String> params) {
        Future<List<List<Integer>>> r = getFuture(vertx, elementList, params);
        return r.map(lists -> {
//            System.out.println("ListOperator " + Thread.currentThread().getName());
            List<Integer> l = new LinkedList<>();
            lists.forEach(integers -> l.addAll(integers));
            return l;
        });
    }

}
