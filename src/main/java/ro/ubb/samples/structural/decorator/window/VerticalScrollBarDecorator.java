package ro.ubb.samples.structural.decorator.window;

public class VerticalScrollBarDecorator extends WindowDecorator {
    VerticalScrollBarDecorator(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override public void draw() {
        super.draw();
        drawVerticalScrollBar();
    }

    private void drawVerticalScrollBar() { }

    @Override public String getDescription() {
        return super.getDescription() + ", including vertical scrollbars";
    }
}

