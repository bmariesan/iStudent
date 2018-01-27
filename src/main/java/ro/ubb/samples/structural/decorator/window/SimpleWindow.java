package ro.ubb.samples.structural.decorator.window;

class SimpleWindow implements Window {
    @Override
    public void draw() {
    }

    @Override
    public String getDescription() {
        return "simple window";
    }
}