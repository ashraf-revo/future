package org.revo.RemotoConnect;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.revo.RemotoConnect.Data.social;

/**
 * Created by revo on 03/09/15.
 */
public class GetDataFutureImpl implements OperationData {

    @Override
    public List<Site> sequentialSocial() {
        return social.stream()
                .map(Remoto::getData)
                .collect(Collectors.toList());
    }

    @Override
    public List<Site> parallelSocial() {
        return social.parallelStream()
                .map(Remoto::getData)
                .collect(Collectors.toList());
    }

    @Override
    public List<Site> futureSocial() {
        List<CompletableFuture<Site>> futureStream = social.stream().
                map(s ->
                        CompletableFuture.supplyAsync(() -> Remoto.getData(s))).collect(Collectors.toList());
        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }

    @Override
    public List<Site> futureSocialExecutor() {
        List<CompletableFuture<Site>> futureStream = social.stream().
                map(s ->
                        CompletableFuture.supplyAsync(() -> Remoto.getData(s), executor(social.size()))).collect(Collectors.toList());
        return futureStream.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

    }
}
