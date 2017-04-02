import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws CustomException, IOException {
        Catalog catalog = new Catalog();
//		Book Audi_Book = new Book("Audi Manual", "C:\\Faculty\\Sem2\\Java\\Manual_utilizare_audi_A4.pdf", 2006, "Audi");
//		Movie Photo_of_me = new Movie("Me", "C:\\Faculty\\Sem2\\Java\\me.jpg", 2015, "Unknown");
//		catalog.add (Audi_Book);
//		catalog.add (Photo_of_me);
//		catalog.save("C:\\Faculty\\Sem2\\Java\\catalog.dat");
//        catalog.load("C:\\Faculty\\Sem2\\Java\\catalog.dat");
//        catalog.list();
		//catalog.play(catalog.getClass());
        String command;
        Scanner scan = new Scanner(System.in);
        String path;
        String name;
        String type;
        Command inputCommand;
        while (true)
        {
           command = scan.next();
           switch(command)
           {
               case "add":
                   type = scan.next();
                   name = scan.next();
                   path = scan.next();
                   String year = scan.next();
                   String author = scan.next();
                   inputCommand = new AddCommand(catalog, type, name, path, year, author);
                   inputCommand.execute();
                   break;
               case "save":
                   path = scan.next();
                   inputCommand = new SaveCommand(catalog, path);
                   inputCommand.execute();
                   break;
               case "load":
                   path = scan.next();
                   inputCommand = new LoadCommand(catalog, path);
                   inputCommand.execute();
                   break;
               case "list":
                   inputCommand = new ListCommand(catalog);
                   inputCommand.execute();
                   break;
               case "play":
                   name = scan.next();
                   inputCommand = new PlayCommand(catalog, name);
                   inputCommand.execute();
                   break;
               case "report":
                   type = scan.next();
                   inputCommand = new ReportCommand(catalog, type);
                   inputCommand.execute();
                   break;

           }
        }
    }
}
