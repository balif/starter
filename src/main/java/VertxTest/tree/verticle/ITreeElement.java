package VertxTest.tree.verticle;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;

public interface ITreeElement {

    Future<List<Integer>> getRes(Vertx vertx);

    String getAddress();


}
