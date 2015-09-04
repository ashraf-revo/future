package org.revo.RemotoConnect;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * Created by ashraf on 9/3/2015.
 */
public class GetDataFutureTranslateImpl implements OperationData {


    @Override
    public List<Site> sequentialSocial() {
        return social.stream()
                .map(Remoto::getData)
                .map(Remoto::Translate)
                .collect(Collectors.toList());
    }

    @Override
    public List<Site> parallelSocial() {
        return social.parallelStream()
                .map(Remoto::getData)
                .map(Remoto::Translate)
                .collect(Collectors.toList());

    }

    @Override
    public List<Site> futureSocial() {
        List<CompletableFuture<Site>> futureStream = social.stream().
                map(s ->
                                CompletableFuture.supplyAsync(() -> Remoto.getData(s))
                                        .thenApply(Remoto::Translate)
                )
                .collect(Collectors.toList());

        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    @Override
    public List<Site> futureSocialExecutor() {

        List<CompletableFuture<Site>> futureStream = social.stream().
                map(s ->
                                CompletableFuture.supplyAsync(() -> Remoto.getData(s), executor(social.size()))
                                        .thenApply(Remoto::Translate)
                )
                .collect(Collectors.toList());

        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
}
