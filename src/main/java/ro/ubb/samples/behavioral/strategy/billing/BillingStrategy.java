package ro.ubb.samples.behavioral.strategy.billing;

interface BillingStrategy {
    double getActPrice(final double rawPrice);
}

