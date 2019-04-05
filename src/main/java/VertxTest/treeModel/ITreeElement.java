package VertxTest.treeModel;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;

public interface ITreeElement {

    Future<List<Integer>> getRes(Vertx vertx);

    String getAddress();


}
