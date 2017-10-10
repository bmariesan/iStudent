package ro.ubb.samples.creational.singleton;

public final class SingletonThreadSafe {
    private static volatile SingletonThreadSafe instance = null;

    private SingletonThreadSafe() {}

    public static SingletonThreadSafe getInstance() {
        if (instance == null) {
            synchronized(SingletonThreadSafe.class) {
                if (instance == null) {
                    instance = new SingletonThreadSafe();
                }
            }
        }
        return instance;
    }
}