package ro.ubb.samples.behavioral.template;

class Realization extends Specialization {
    // 4. Derived classes can override placeholder methods
    protected void stepTwo() {
        System.out.println("Realization.stepTwo");
    }

    protected void step3_2() {
        System.out.println( "Realization.step3_2");
    }

    // 5. Derived classes can override implemented methods
    // 6. Derived classes can override and "call back to" base class methods
    protected void stepFor() {
        System.out.println("Realization.stepFor");
        super.stepFor();
    }
}
