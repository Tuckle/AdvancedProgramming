import java.awt.Desktop;
import java.io.*;
import java.util.*;

public class Catalog implements  java.io.Serializable{
    List<AbstractItem> itemsList;

    Catalog() {
        itemsList = new ArrayList<AbstractItem>();
    }

    public void add(AbstractItem item) throws CustomException {
        if (item == null) {
            throw new CustomException("Given item is empty");
        }
        itemsList.add(item);
    }

    public void save(String path) throws CustomException, IOException {
//        File file = new File(path);
//        if(!file.exists()){
//        	file.createNewFile();
//        }
//        if(!file.isFile()){
//            throw new CustomException("The given path is not a file");
//        }

        FileOutputStream fos = new FileOutputStream(path);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(AbstractItem item : itemsList) {
                oos.writeObject(item);
            }
//            oos.writeObject(itemsList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("Item serialization: " + e.getMessage());
        }
        fos.close();
//      FileWriter fwr = new FileWriter(file);
//      fwr.write(itemsList.toString());
//      fwr.close();
    }

    public void load(String path) throws CustomException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new CustomException("Given file path does not exist.");
        }
        if (!file.isFile()) {
            throw new CustomException("The given path is not a file");
        }
        ObjectInputStream ois;
        FileInputStream fis = new FileInputStream(path);
        ois = new ObjectInputStream(fis);
        try {

            while (true) {
                itemsList.add((AbstractItem) ois.readObject());
            }
//            this.itemsList = (ArrayList<AbstractItem>) ois.readObject();
        } catch (EOFException eof) {
            ois.close();
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
//      FileReader fwr = new FileReader(file);
        // read file line by line and add to data if necessary
    }

    public void list() {
        System.out.println("Number of items in list: " + itemsList.size());
        for (AbstractItem item : itemsList) {
            System.out.println(item.getName());
        }
    }

    public void play(AbstractItem item) throws IOException {
        File file = new File(item.getPath());
        Desktop dItem = Desktop.getDesktop();
        dItem.open(file);

    }
}