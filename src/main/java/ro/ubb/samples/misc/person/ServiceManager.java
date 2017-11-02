package ro.ubb.samples.misc.person;

class ServiceManager {

    private static Service INSTANCE;

    static Service getService() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceImpl();
        }
        return INSTANCE;
    }
}
