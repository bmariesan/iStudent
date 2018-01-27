package ro.ubb.samples.behavioral.command.basic;

interface Command {
    void execute();
}

class DomesticEngineer implements Command {
    public void execute() {
        System.out.println("take out the trash");
    }
}

class Politician implements Command {
    public void execute() {
        System.out.println("take money from the rich, take votes from the poor");
    }
}

class Programmer implements Command {
    public void execute() {
        System.out.println("sell the bugs, charge extra for the fixes");
    }
}

