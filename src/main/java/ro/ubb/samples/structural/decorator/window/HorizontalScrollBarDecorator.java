package ro.ubb.samples.structural.decorator.window;

class HorizontalScrollBarDecorator extends WindowDecorator {
    public HorizontalScrollBarDecorator(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override public void draw() {
        super.draw();
        drawHorizontalScrollBar();
    }

    private void drawHorizontalScrollBar() { }

    @Override public String getDescription() {
        return super.getDescription() + ", including horizontal scrollbars";
    }
}