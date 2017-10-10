package ro.ubb.samples.creational.abstract_factory;

interface IGUIFactory {
    public IButton createButton();
}

class WinFactory implements IGUIFactory {
    @Override
    public IButton createButton() {
        return new WinButton();
    }
}

class OSXFactory implements IGUIFactory {
    @Override
    public IButton createButton() {
        return new OSXButton();
    }
}
