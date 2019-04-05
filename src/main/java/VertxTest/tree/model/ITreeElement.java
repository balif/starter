package VertxTest.tree.model;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.List;
import java.util.Map;

public interface ITreeElement {

    Future<List<Integer>> getRes(Vertx vertx, Map<String,String> params);

    String getAddress();



}
