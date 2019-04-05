package VertxTest.tree.codec;

import VertxTest.tree.verticle.ListOperator;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class ListOperatorCodec implements MessageCodec<ListOperator, ListOperator> {

    @Override
    public void encodeToWire(Buffer buffer, ListOperator trendLeaf) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ListOperator decodeFromWire(int pos, Buffer buffer) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public ListOperator transform(ListOperator iTreeNode) {
        return iTreeNode;
    }

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
