package ro.ubb.samples.behavioral.mediator.stock_broker;

public class TestStockMediator{
    public static void main(String[] args){
        StockMediator nyse = new StockMediator();
        StockBroker broker = new StockBroker("Gordon", nyse);
        StockBroker broker2 = new StockBroker("Daniel", nyse);

        broker.saleOffer("MSFT", 100);
        broker.saleOffer("GOOG", 50);

        broker2.buyOffer("MSFT", 100);
        broker2.saleOffer("NRG", 10);

        broker.buyOffer("NRG", 10);

        nyse.getStockOfferings();
    }
}