import java.io.IOException;

public class SaveCommand extends Command{
    SaveCommand(Catalog inputCatalog, String inputString)
    {
        this.catalog = inputCatalog;
        this.setNumberOfArguments(1);
        this.addArgument(inputString);
    }
    public void execute() throws IOException, CustomException, IOException {
        this.catalog.save(this.arguments.get(0));
    }
}
