package VertxTest.tree.model.engine;

import VertxTest.tree.model.ILeafNode;
import io.vertx.core.Vertx;

public class TrendLeaf extends ILeafNode {

    public static final String TREND_ENGINE_V = "trendEngineV";
    private final String params;

    public TrendLeaf(Integer algId, String group) {
        this.params = algId+","+group;
    }

    @Override
    public Object getParameters() {
        return params;
    }

    @Override
    public String getAddress() {
        return TREND_ENGINE_V;
    }
}
