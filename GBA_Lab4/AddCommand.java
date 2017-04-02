import java.io.IOException;

/**
 * Created by gbalan on 3/25/2017.
 */
public class AddCommand extends Command{
    AddCommand(Catalog inputCatalog, String... inputStrings) throws CustomException {
        if (inputStrings.length != 5)
        {
            throw new CustomException("Numar de parametru invalid pentru comanda move");
        }
        this.setArguments(inputStrings);
        this.setNumberOfArguments(4);
        this.catalog = inputCatalog;
    }
    public void execute() throws CustomException, IOException
    {
        String itemType = this.arguments.get(0);
        if (itemType.equals("book"))
        {
            Book b1 = new Book(this.arguments.get(1), this.arguments.get(2), Integer.parseInt(this.arguments.get(3)), this.arguments.get(4));
            this.catalog.itemsList.add(b1);
        }
        else if (itemType.equals("movie"))
        {
            Movie m1 = new Movie(this.arguments.get(1), this.arguments.get(2), Integer.parseInt(this.arguments.get(3)), this.arguments.get(4));
            this.catalog.itemsList.add(m1);
        }
        else if (itemType.equals("song"))
        {
            Song s1 = new Song(this.arguments.get(1), this.arguments.get(2), Integer.parseInt(this.arguments.get(3)), this.arguments.get(4));
            this.catalog.itemsList.add(s1);
        }
    }
}
