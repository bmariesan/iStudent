package ro.ubb.samples.creational.factory.maze_game;

import java.util.ArrayList;
import java.util.List;

public abstract class MazeGameFactory {
    private final List<Room> rooms = new ArrayList<>();
    public MazeGameFactory() {
        Room room1 = makeRoom();
        Room room2 = makeRoom();
        room1.connect(room2);
        rooms.add(room1);
        rooms.add(room2);
    }
    abstract protected Room makeRoom();
}