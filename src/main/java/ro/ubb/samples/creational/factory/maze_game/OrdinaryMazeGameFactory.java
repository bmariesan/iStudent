package ro.ubb.samples.creational.factory.maze_game;

public class OrdinaryMazeGameFactory extends MazeGameFactory {

    @Override
    protected Room makeRoom() {
        return new OrdinaryRoom();
    }
}