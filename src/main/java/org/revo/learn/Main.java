package org.revo.learn;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by revo on 03/09/15.
 */
public class Main {
    static FutureImpl future = new FutureImpl();

    public static void main(String[] args) {
        execute(() -> future.sequentialSocial());
        execute(() -> future.parallelSocial());
        execute(() -> future.futureSocial());
        execute(() -> future.futureSocialExecutor());
    }

    private static void execute(Supplier<List<Data>> f) {
        long start = System.currentTimeMillis();
        f.get();
        long end = System.currentTimeMillis();
        System.out.println("******************************************************" + (end - start));
    }
}
