package VertxTest.tree;

import VertxTest.tree.codec.LocalCodec;
import VertxTest.tree.verticle.*;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeMain {
    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        Reflections reflections = new Reflections("java.util");

        Set<Class<? extends Collection>> allClasses =
                reflections.getSubTypesOf(Collection.class);
        Vertx vertx = Vertx.vertx();
        registerLocalCodec(vertx, ListOperator.class,LinkedList.class, TrendLeaf.class,ArrayList.class);


        vertx.deployVerticle(VListOperator.class, new DeploymentOptions().setInstances(1));
        vertx.deployVerticle(VTrend.class, new DeploymentOptions().setInstances(2));

        Thread.sleep(2000);

        ITreeElement treeElement = new ListOperator(Arrays.asList(
                new ListOperator(Arrays.asList(new TrendLeaf(1, "gr1"),new TrendLeaf(2, "gr2"))),
                new TrendLeaf(2, "gr2")));
        Future<List<Integer>> f = treeElement.getRes(vertx);

        f.setHandler(event -> {
            System.out.println(event.succeeded() + " " + event.result());
        });

    }

    static public void registerLocalCodec(Vertx vertx, Class... clazz){
        for (Class aClass : clazz) {
            vertx.eventBus().registerDefaultCodec(aClass, new LocalCodec(aClass));
        }
    }

}