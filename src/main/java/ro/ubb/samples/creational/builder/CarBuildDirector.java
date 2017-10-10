package ro.ubb.samples.creational.builder;

public class CarBuildDirector {
    private CarBuilder builder;

    public CarBuildDirector(final CarBuilder builder) {
        this.builder = builder;
    }

    public Car construct() {
        return builder
                .wheels(4)
                .color("Red")
                .build();
    }

    public static void main(final String[] arguments) {
        final CarBuilder builder = new CarBuilderImpl();
        final CarBuildDirector carBuildDirector = new CarBuildDirector(builder);
        Car builtCar = carBuildDirector.construct();
        System.out.println(builtCar);
    }
}