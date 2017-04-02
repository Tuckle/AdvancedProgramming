import java.io.IOException;

public class ListCommand extends Command{
    ListCommand(Catalog inputCatalog)
    {
        this.setNumberOfArguments(0);
        this.catalog = inputCatalog;
    }
    public void execute() throws IOException, CustomException, IOException
    {
        this.catalog.list();
    }
}
