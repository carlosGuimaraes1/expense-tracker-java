package storage;

import model.Budget;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BudgetStorage {
    public static void save(List<Budget> budgetList) throws IOException {
        Path path = Paths.get("expense/budget.ser");
        if (Files.notExists(path)){
            Files.createDirectories(path.getParent());
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
            oos.writeObject(budgetList);
            oos.flush();
            System.out.println("\nbudget salved");
        }
    }
    @SuppressWarnings("unchecked")
    public static List<Budget> load() throws ClassNotFoundException, IOException {
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("expense/budget.ser")))) {
            return (List<Budget>) ois.readObject();
        }
    }
}
