package org.revo;

import static org.revo.Util.delay;

public class ExchangeService {

    public enum Money {
        USD(1.0), EUR(1.35387);

        private final double rate;

        Money(double rate) {
            this.rate = rate;
        }
    }

    public static double getRate(Money source, Money destination) {
        return getRateWithDelay(source, destination);
    }

    private static double getRateWithDelay(Money source, Money destination) {
        delay();
        return destination.rate / source.rate;
    }

}
