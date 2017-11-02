package ro.ubb.samples.creational.factory.maze_game;

public class MagicMazeGameFactory extends MazeGameFactory {

    @Override
    protected Room makeRoom() {
        return new MagicRoom();
    }
}
