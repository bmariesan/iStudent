package ro.ubb.samples.creational.prototype;

interface Person {
    Person clone();
}

class Tom implements Person {
    @Override
    public Person clone() {
        return new Tom();
    }
    @Override
    public String toString() {
        return "Tom";
    }
}

class Rick implements Person {
    @Override
    public Person clone() {
        return new Rick();
    }
    @Override
    public String toString() {
        return "Rick";
    }
}

class Harry implements Person {
    @Override
    public Person clone() {
        return new Harry();
    }
    @Override
    public String toString() {
        return "Harry";
    }
}
