package VertxTest.tree.codec;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

import java.util.LinkedList;

public class JListCodec implements MessageCodec<LinkedList,LinkedList> {
    @Override
    public void encodeToWire(Buffer buffer, LinkedList list) {
        throw new RuntimeException("Not implemented");

    }

    @Override
    public LinkedList decodeFromWire(int pos, Buffer buffer) {
        throw new RuntimeException("Not implemented");

    }

    @Override
    public LinkedList transform(LinkedList list) {
        return list;
    }

    @Override
    public String name() {
        return JListCodec.class.getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}
