package org.revo;

import org.revo.ExchangeService.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ashraf on 9/3/2015.
 */
public class Main {
    static Main m = new Main();

    public static void main(String[] args) {
        execute("composed CompletableFuture", () -> m.findPricesFuture("myPhone27S"));
        execute("combined USD CompletableFuture v2", () -> m.findPricesInUSD2("myPhone27S"));

    }

    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }

    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public List<String> findPricesInUSD2(String product) {
        List<CompletableFuture<String>> priceFutures = new ArrayList<>();
        for (Shop shop : shops) {
            CompletableFuture<String> futurePriceInUSD =
                    CompletableFuture.supplyAsync(() -> shop.getPrice(product))
                            .thenCombine(
                                    CompletableFuture.supplyAsync(
                                            () -> ExchangeService.getRate(Money.EUR, Money.USD)),
                                    (price, rate) -> price * rate
                            ).thenApply(price -> shop.getName() + " price is " + price);
            priceFutures.add(futurePriceInUSD);
        }
        List<String> prices = priceFutures
                .stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return prices;
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> priceFutures = findPricesStream(product)
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPriceS(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)));
    }
}
