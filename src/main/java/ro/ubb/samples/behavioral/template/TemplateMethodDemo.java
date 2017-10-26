package ro.ubb.samples.behavioral.template;

public class TemplateMethodDemo {
    public static void main(String[] args) {
        Abst algorithm = new Realiz();
        algorithm.findSolution();
    }
}
