package ro.ubb.samples.structural.decorator.coffee;

public class Client {
 public static void printInfo(Coffee c) {
  System.out.println("Cost: " + c.getCost() + "; Ingredients: " + c.getIngredients());
 }

 public static void main(String[] args) {
  Coffee c = new SimpleCoffee();  printInfo(c);

  c = new WithMilk(c);  printInfo(c);

  c = new WithSprinkles(c);  printInfo(c);
 }
}
