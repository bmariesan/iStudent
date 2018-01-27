package ro.ubb.samples.misc.person.service;

public class ServiceManager {

    private static Service INSTANCE;

    public static Service getService() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceImpl();
        }
        return INSTANCE;
    }
}
