package ro.ubb.samples.creational.builder;

interface CarBuilder {
    Car build();

    CarBuilder color(final String color);

    CarBuilder wheels(final int wheels);
}

class CarBuilderImpl implements CarBuilder {
    private Car car;

    public CarBuilderImpl() {
        car = new Car();
    }

    @Override
    public Car build() {
        return car;
    }

    @Override
    public CarBuilder color(final String color) {
        car.setColor(color);
        return this;
    }

    @Override
    public CarBuilder wheels(final int wheels) {
        car.setWheels(wheels);
        return this;
    }
}
