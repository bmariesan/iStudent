package ro.ubb.samples.creational.abstract_factory;

interface IButton {
    void paint();
}

class WinButton implements IButton {
    @Override
    public void paint() {
        System.out.println("WinButton");
    }
}

class OSXButton implements IButton {
    @Override
    public void paint() {
        System.out.println("OSXButton");
    }
}