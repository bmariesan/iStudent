package ro.ubb.samples.behavioral.mediator.stock_broker;

public abstract class Broker {
    private IMediator mediator;
    private int colleagueCode;

    public Broker(IMediator newMediator) {
        mediator = newMediator;
        mediator.addColleague(this);
    }
    public void saleOffer(String stock, int shares) {
        mediator.saleOffer(stock, shares, this.colleagueCode);
    }
    public void buyOffer(String stock, int shares) {
        mediator.buyOffer(stock, shares, this.colleagueCode);
    }
    public void setCollCode(int collCode) {
        colleagueCode = collCode;
    }
}
