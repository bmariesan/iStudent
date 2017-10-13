package ro.ubb.samples.structural.decorator.coffee;


class WithMilk extends CoffeeDecorator {
    public WithMilk(Coffee c) { super(c); }

    public double getCost() { // Overriding methods defined in the abstract superclass
        return super.getCost() + 0.5;
    }

    public String getIngredients() {
        return super.getIngredients() + ", Milk";
    }
}

