package ro.ubb.samples.behavioral.mediator.stock_broker;

import java.util.ArrayList;
import java.util.List;

public class StockMediator implements IMediator {

    private List<Broker> colleagues = new ArrayList<>();
    private List<StockOffer> stockBuyOffers = new ArrayList<>();
    private List<StockOffer> stockSaleOffers = new ArrayList<>();
    private int colleagueCodes = 0;

    public void addColleague(Broker newColleague) {
        colleagues.add(newColleague);
        newColleague.setCollCode(colleagueCodes++);
    }

    public void saleOffer(String stock, int shares, int collCode) {
        boolean stockSold = false;
        for (StockOffer offer : stockBuyOffers) {
            if ((offer.getStockSymbol() == stock) && (offer.getstockShares() == shares)) {
                System.out.println(shares + " shares of " + stock +
                        " sold to colleague code " + offer.getCollCode());
                stockBuyOffers.remove(offer);
                stockSold = true;
            }
            if (stockSold) break;
        }
        if (!stockSold) {
            System.out.println(shares + " shares of " + stock +
                    " added to inventory");
            StockOffer newOffering = new StockOffer(shares, stock, collCode);
            stockSaleOffers.add(newOffering);
        }
    }

    public void buyOffer(String stock, int shares, int collCode) {
        boolean stockBought = false;
        for (StockOffer offer : stockSaleOffers) {
            if ((offer.getStockSymbol() == stock) && (offer.getstockShares() == shares)) {
                System.out.println(shares + " shares of " + stock +
                        " bought by colleague code " + offer.getCollCode());
                stockSaleOffers.remove(offer);
                stockBought = true;
            }
            if (stockBought) break;
        }
        if (!stockBought) {
            System.out.println(shares + " shares of " + stock + " added to inventory");
            stockBuyOffers.add(new StockOffer(shares, stock, collCode));
        }
    }

    public void getStockOfferings() {
        System.out.println("\n Stocks for Sale");
        for (StockOffer offer : stockSaleOffers) {
            System.out.println(offer.getstockShares() + " of " + offer.getStockSymbol());
        }
        System.out.println("\n Stock Buy Offers");
        for (StockOffer offer : stockBuyOffers) {
            System.out.println(offer.getstockShares() + " of " + offer.getStockSymbol());
        }
    }
}