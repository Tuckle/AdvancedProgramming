package AdvancedProgramming.Lab4.Sources;
import java.io.IOException;

public class PlayCommand extends Command{
    public PlayCommand(Catalog inputCatalog, String inputString)
    {
        this.catalog = inputCatalog;
        this.setNumberOfArguments(1);
        this.addArgument(inputString);
    }
    public void execute() throws IOException, CustomException {
        for (int index = 0; index < this.catalog.itemsList.size(); index ++) {
            if (this.catalog.itemsList.get(index).getName().equals(this.arguments.get(0))) {
                this.catalog.play(this.catalog.itemsList.get(index));
                break;
            }
        }
    }
};
