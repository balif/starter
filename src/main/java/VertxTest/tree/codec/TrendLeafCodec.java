package VertxTest.tree.codec;

import VertxTest.tree.verticle.TrendLeaf;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class TrendLeafCodec implements MessageCodec<TrendLeaf,TrendLeaf> {

    @Override
    public void encodeToWire(Buffer buffer, TrendLeaf trendLeaf) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TrendLeaf decodeFromWire(int pos, Buffer buffer) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public TrendLeaf transform(TrendLeaf trendLeaf) {
        return trendLeaf;
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
