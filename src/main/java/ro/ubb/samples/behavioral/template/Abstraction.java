package ro.ubb.samples.behavioral.template;

abstract class Abstraction {
    // 1. Standardize the skeleton of an algorithm in a "template" method
    void findSolution() {
        stepOne();
        stepTwo();
        stepThr();
        stepFor();
    }
    // 2. Common implementations of individual steps are defined in base class
    private void stepOne() {
        System.out.println("Abstraction.stepOne");
    }
    // 3. Steps requiring peculiar implementations are "placeholders" in the base class
    abstract void stepTwo();
    abstract void stepThr();

    void stepFor() {
        System.out.println( "Abstraction.stepFor" );
    }
}
