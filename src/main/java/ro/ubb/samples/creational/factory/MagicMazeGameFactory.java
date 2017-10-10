package ro.ubb.samples.creational.factory;

public class MagicMazeGameFactory extends MazeGameFactory {

    @Override
    protected Room makeRoom() {
        return new MagicRoom();
    }
}
