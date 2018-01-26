package ro.ubb.istudent.util;

public class MockBuilder {
    static final MockBuilder shared = new MockBuilder();

    static public MockBuilder sharedInstace() {
        return shared;
    }

    private MockBuilder() {
        this.mockData();
    }

    public void mockData() {
        System.out.println("Data mocked");
    }
}
