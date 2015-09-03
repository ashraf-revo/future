package org.revo.learn;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by revo on 03/09/15.
 */
public class FutureImpl {
    public static List<String> social = Arrays.asList(
            "https://www.facebook.com/",
            "https://www.youtube.com/",
            "https://twitter.com/",
            "https://www.linkedin.com/",
            "https://soundcloud.com/",
            "https://plus.google.com/",
            "https://www.quora.com",
            "https://io.hsoub.com/");
    Executor executor = Executors.newFixedThreadPool(social.size(), r ->
            {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }
    );

    public List<Data> sequentialSocial() {
        return social.stream()
                .map(Remoto::getData)
                .collect(Collectors.toList());
    }

    public List<Data> parallelSocial() {
        return social.parallelStream()
                .map(Remoto::getData)
                .collect(Collectors.toList());
    }

    public List<Data> futureSocial() {
        List<CompletableFuture<Data>> futureStream = social.stream().
                map(s ->
                        CompletableFuture.supplyAsync(() -> Remoto.getData(s))).collect(Collectors.toList());

        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    public List<Data> futureSocialExecutor() {
        List<CompletableFuture<Data>> futureStream = social.stream().
                map(s ->
                        CompletableFuture.supplyAsync(() -> Remoto.getData(s), executor)).collect(Collectors.toList());
        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

}
