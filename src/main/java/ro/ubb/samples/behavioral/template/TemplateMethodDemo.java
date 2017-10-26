package ro.ubb.samples.behavioral.template;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        Abstraction algorithm = new Realization();
        algorithm.findSolution();
    }
}
