import java.io.IOException;

public class LoadCommand extends Command{
    LoadCommand(Catalog inputCatalog, String inputString)
    {
        this.catalog = inputCatalog;
        this.setNumberOfArguments(1);
        this.addArgument(inputString);
    }
    public void execute() throws IOException, CustomException, IOException {
        this.catalog.load(this.arguments.get(0));
    }
}
