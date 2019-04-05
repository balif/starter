package VertxTest;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * The main verticle for Data Charon.
 *
 * @author jarek
 */
public class TestVerticle  {

    private static final Logger logger = LoggerFactory.getLogger(TestVerticle.class.getName());

    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        Verticle1 verticle = new Verticle1(1);

        Single<String> vd = vertx.rxDeployVerticle(verticle);
        Observable<Integer> fmo = vd.flatMapObservable(a ->   Observable.fromArray(1, 2, 3, 4, 5, 6, 7, 8));
        Observable<String> fms = fmo.flatMapSingle(id -> vertx.rxDeployVerticle(new Verticle1(id)));
        Observable<String> d = fms.delay(1, TimeUnit.SECONDS);
                d.doOnComplete(() ->
                        Observable.fromIterable(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15))
                .subscribe(i ->
                        verticle.getVertx().eventBus().send("sleep", i)))
        .subscribe();

    }


    public static class  Verticle1 extends AbstractVerticle {

        final int id;

        public Verticle1(int id) {
            this.id = id;
        }

        @Override
        public void start() {
            this.vertx.eventBus().localConsumer("sleep", a -> {
                try {
                    System.out.println("Verticle id: "+id+" Odebrałem "+a.body() + " Thread: "+ Thread.currentThread().getId());

                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Verticle started");
        }


    }


}