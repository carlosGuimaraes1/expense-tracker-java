import model.Expense;
import service.ExpenseManager;

public class main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        manager.addExpense("adadad", 30);
        manager.addExpense("sfsfs", 35);
        manager.deleteExpense(2);
        manager.allExpenses();
    }
}
