package ro.ubb.samples.creational.factory.maze_game;

public class GameLauncher {
    public static void main(String[] args) {
        MazeGameFactory ordinaryGame = new OrdinaryMazeGameFactory();
        MazeGameFactory magicGame = new MagicMazeGameFactory();
    }
}
