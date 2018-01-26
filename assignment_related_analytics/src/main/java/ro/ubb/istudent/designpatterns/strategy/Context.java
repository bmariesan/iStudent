package ro.ubb.istudent.designpatterns.strategy;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public long executeStrategy(){
        return strategy.computeStatistics();
    }

}