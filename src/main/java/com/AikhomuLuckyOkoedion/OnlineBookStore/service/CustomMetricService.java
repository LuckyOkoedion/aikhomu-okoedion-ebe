package com.AikhomuLuckyOkoedion.OnlineBookStore.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricService {

    private final Counter checkoutCounter;
    private final Counter cartAdditionCounter;
    private final Counter searchCounter;

    public CustomMetricService(MeterRegistry meterRegistry) {
        this.checkoutCounter = Counter.builder("checkout.requests")
            .description("Number of checkout requests")
            .register(meterRegistry);

        this.cartAdditionCounter = Counter.builder("cart.additions")
            .description("Number of books added to the cart")
            .register(meterRegistry);

        this.searchCounter = Counter.builder("search.requests")
            .description("Number of search requests")
            .register(meterRegistry);
    }

    public void incrementCheckoutCounter() {
        checkoutCounter.increment();
    }

    public void incrementCartAdditionCounter() {
        cartAdditionCounter.increment();
    }

    public void incrementSearchCounter() {
        searchCounter.increment();
    }
}

