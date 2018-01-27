package ro.ubb.samples.behavioral.strategy.billing;

// Strategy for Happy hour (50% discount)
class HappyHourStrategy
        implements BillingStrategy {

    @Override
    public double getActPrice
            (double rawPrice) {
        return rawPrice * 0.5;
    }
}
