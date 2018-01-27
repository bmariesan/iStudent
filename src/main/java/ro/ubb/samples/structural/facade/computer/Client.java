package ro.ubb.samples.structural.facade.computer;

class Client {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}