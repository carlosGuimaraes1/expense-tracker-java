import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManeger {

    List<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, double amount) {
        expenses.add(new Expense(getNextId(), amount, description, LocalDate.now()));
        System.out.println("Added expense");
    }

    public int getNextId() {
        if (expenses == null) {
            return 1;
        }
        int maiorID = 0;
        for (Expense expense : expenses) {
            if (maiorID < expense.getId()) {
                maiorID = expense.getId();
            }
        }
        return maiorID + 1;
    }

    public void updateExpense(int id, double amount, String description) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setAmount(amount);
                expense.setDescription(description);
                System.out.println("Updated expense");
                break;
            }
        }
        System.err.println("This expense does not exist");
    }

    public void deleteExpense(int id) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId() == id) {
                expenses.remove(i);
                System.out.println("Expense was excluded");
                break;
            }
        }
        System.err.println("Expense not found");
    }
}
