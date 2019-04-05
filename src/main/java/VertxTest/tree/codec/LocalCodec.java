package VertxTest.tree.codec;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class LocalCodec implements MessageCodec {

    private final String name;

    public LocalCodec(Class clazz) {
        this.name = clazz.getSimpleName();
    }

    @Override
    public void encodeToWire(Buffer buffer, Object o) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object decodeFromWire(int pos, Buffer buffer) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object transform(Object o) {
        return o;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }

    public static void registerLocalCodec(Vertx vertx, Class... clazz) {
        for (Class aClass : clazz) {
            vertx.eventBus().registerDefaultCodec(aClass, new LocalCodec(aClass));
        }
    }
}
