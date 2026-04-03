package storage;

import model.Expense;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExpenseStorage {
    public static void save(List<Expense> expenseList) throws IOException {
        Path path = Paths.get("expense/expense.ser");
        if (Files.notExists(path)){
            Files.createDirectories(path.getParent());
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
           oos.writeObject(expenseList);
           oos.flush();
           System.out.println("expense salved");
       }
    }
    @SuppressWarnings("unchecked")
    public static List<Expense> load() throws ClassNotFoundException, IOException {
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("expense/expense.ser")))) {
            return (List<Expense>) ois.readObject();
        }
    }
}
