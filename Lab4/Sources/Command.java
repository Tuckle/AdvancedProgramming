package AdvancedProgramming.Lab4.Sources;
import java.io.IOException;
import java.util.*;

public abstract class Command {
    Integer numberOfArguments;
    List<String> arguments;
    Catalog catalog;

    public Command()
    {
        this.arguments = new ArrayList<String>();
        this.numberOfArguments = 0;
    }

    public void setNumberOfArguments(Integer inputNumber) {
        this.numberOfArguments = inputNumber;
    }
    public Integer getNumberOfArguments() {
        return this.numberOfArguments;
    }

    public void setArguments(String... inputString) {
        for (String item : inputString)
        {
            this.arguments.add(item);
        }
    }

    public void addArgument(String inputString){
        this.arguments.add(inputString);
    }
    public abstract void execute() throws IOException, CustomException, IOException;
}
