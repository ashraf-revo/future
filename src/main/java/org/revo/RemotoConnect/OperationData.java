package org.revo.RemotoConnect;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * Created by ashraf on 9/3/2015.
 */
public interface OperationData {
     List<String> social = Arrays.asList(
            "https://www.facebook.com/",
            "https://www.youtube.com/",
            "https://twitter.com/",
            "https://www.linkedin.com/",
            "https://soundcloud.com/",
            "https://plus.google.com/",
            "https://www.quora.com",
            "https://io.hsoub.com/");


    default Executor executor(int size) {
        return Executors.newFixedThreadPool(size, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }
     static void execute(String Message, Supplier<List<Site>> f) {
        long start = System.currentTimeMillis();
        f.get();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "         for " + Message);
    }

    List<Site> sequentialSocial();

    List<Site> parallelSocial();

    List<Site> futureSocial();

    List<Site> futureSocialExecutor();
}
