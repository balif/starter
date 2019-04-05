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

public class ConditionOperator extends ITreeNode {

    private final Map<String, List<ITreeElement>> conditionsList;
    private final String conditionParameterName;

    public ConditionOperator(String conditionParameterName, Condition... conditions) {
        this.conditionParameterName = conditionParameterName;
        conditionsList = transformConditionsToCaseSubTreeMap(conditions);
    }

    private Map<String, List<ITreeElement>> transformConditionsToCaseSubTreeMap(Condition[] conditions) {
        return Stream.of(conditions).collect(Collectors
                .groupingBy(
                        Condition::getKey,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                cL -> cL.stream()
                                        .map(Condition::getElement)
                                        .collect(Collectors.toList()))
                ));
    }

    @Override
    public Future<List<Integer>> getRes(Vertx vertx, Map<String, String> params) {
        String valueToCheck = params.get(conditionParameterName);
        List<ITreeElement> el = conditionsList.get(valueToCheck);
        if (el == null) {
            Future f = Future.future();
            f.complete(new LinkedList());
            return f;
        }
        Future<List<List<Integer>>> r = getFuture(vertx, el, params);
        return r.map(lists -> {
            List<Integer> l = new LinkedList<>();
            lists.forEach(integers -> l.addAll(integers));
            return l;
        });
    }

    public static class Condition {
        private final String key;
        private final ITreeElement element;

        public Condition(String key, ITreeElement element) {
            this.key = key;
            this.element = element;
        }

        public String getKey() {
            return key;
        }

        public ITreeElement getElement() {
            return element;
        }
    }
}
