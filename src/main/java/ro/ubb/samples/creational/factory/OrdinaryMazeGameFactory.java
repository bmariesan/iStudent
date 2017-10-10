package ro.ubb.samples.creational.factory;

public class OrdinaryMazeGameFactory extends MazeGameFactory {

    @Override
    protected Room makeRoom() {
        return new OrdinaryRoom();
    }
}