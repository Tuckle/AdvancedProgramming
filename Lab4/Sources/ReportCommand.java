package AdvancedProgramming.Lab4.Sources;
import java.io.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class ReportCommand extends Command {
    public ReportCommand(Catalog inputCatalog, String inputString)
    {
        this.catalog = inputCatalog;
        this.setNumberOfArguments(1);
        this.addArgument(inputString);
    }
    private JRDataSource createDataSource()
    {
        DRDataSource dataSource = new DRDataSource("name", "path", "year", "author");
        for (AbstractItem item : this.catalog.itemsList)
        {
            dataSource.add(new StringBuilder(item.getName()), new StringBuilder(item.getPath()), new StringBuilder(item.getYear()), new StringBuilder(item.getAuthor()));
        }
        return dataSource;
    }
    public void execute() throws IOException, CustomException {
        String type = this.arguments.get(0);
        switch(type)
        {
            case "html":
                System.out.println("Printez html...");
                String output = "<DOCTYPE HTML>\n<html>\n<head>\n<title>Report</title>\n<p>Catalog list</p>\n</br></head>\n<body>\n";
                output += "<table>\n<tr>\n<th>Name</th>\n<th>Path</th>\n<th>Year</th>\n<th>Author</th>\n</tr>\n";
                for (AbstractItem item : this.catalog.itemsList)
                {
                    output += "<tr>";
                    output += "<td>" + item.getName() + "</td>";
                    output += "<td>" + item.getPath() + "</td>";
                    output += "<td>" + item.getYear() + "</td>";
                    output += "<td>" + item.getAuthor() + "</td>";
                    output += "</tr>";
                }
                output += "\n</table>\n</body>\n</html>";
                OutputStream fis = new FileOutputStream("report.html");
                PrintStream printStream = new PrintStream(fis);
                printStream.print(output);
                printStream.close();
                break;
            case "pdf":
                try {
                    report()
                            .columns(
                                    col.column("Name", "name", StringBuilder.class),
                                    col.column("Path", "path", StringBuilder.class),
                                    col.column("Year", "year", StringBuilder.class),
                                    col.column("Author", "author", StringBuilder.class)
                            )
                            .setDataSource(createDataSource())
                            .show();
                }
                catch (DRException e){
                    e.printStackTrace();
                }
                System.out.println("Not implemented yet.");
                break;
            case "word":
                System.out.println("Not implemented yet.");
                break;
        }

    }
}
