package VertxTest.tree;

import VertxTest.tree.codec.LocalCodec;
import VertxTest.tree.model.ITreeElement;
import VertxTest.tree.model.operator.ConditionOperator;
import VertxTest.tree.model.operator.ListOperator;
import VertxTest.tree.model.engine.TrendLeaf;
import VertxTest.tree.verticle.*;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeMain {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(200));
        LocalCodec.registerLocalCodec(vertx, TreeContext.class, ListOperator.class, LinkedList.class, TrendLeaf.class, ArrayList.class);


        vertx.deployVerticle(VSimpleOperator.class, new DeploymentOptions().setInstances(2));
        vertx.deployVerticle(VTrend.class, new DeploymentOptions().setInstances(200).setWorker(true));

        Thread.sleep(2000);

        ITreeElement treeElement = new ListOperator(
                new ListOperator(
                        new TrendLeaf(1, "gr1"),
                        new TrendLeaf(2, "gr2")
                ),
                new ListOperator(
                        new ConditionOperator("p1",
                                new ConditionOperator.Condition("v1",new TrendLeaf(1,"gr3")),
                                new ConditionOperator.Condition("v2",new TrendLeaf(1,"gr4"))
                        ),
                        new ListOperator(
                                new TrendLeaf(1, "gr1")
                        )
                ),
        new TrendLeaf(2, "gr2"));

        Map<String,String> params = new HashMap<>();
        params.put("p1","v2");
        System.out.println(Thread.activeCount());
        for (int i = 0; i < 1000; i++) {
            final long start = System.currentTimeMillis();
            Future<List<Integer>> f = treeElement.getRes(vertx,params);

            f.setHandler(event -> {
//                System.out.println(event.succeeded() + " " + event.result());
                System.out.println(System.currentTimeMillis() - start);
            });
            Thread.sleep(10);
        }

        System.out.println("------------------------END");
        Thread.sleep(1000);
        System.out.println(VTrend.senderCount.get());
    }


}