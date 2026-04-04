package service;

import model.Expense;
import storage.ExpenseStorage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExpenseManager {

    private List<Expense> expenses = new ArrayList<>();

    public ExpenseManager() {
        try {
            expenses = ExpenseStorage.load();

        } catch (NoSuchFileException e) {
            expenses = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data " + e.getMessage());
        }
    }

    public void addExpense(String description, double amount, String category) throws IOException {
        int id = getNextId();
        expenses.add(new Expense(id, amount, description, category, LocalDate.now()));
        System.out.println("# Expense added successfully (ID: " + id + ")");
        ExpenseStorage.save(expenses);
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

    public void updateExpense(int id, double amount, String description) throws IOException {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setAmount(amount);
                expense.setDescription(description);
                System.out.println("# Expense updated successfully");
                ExpenseStorage.save(expenses);
                return;
            }
        }
        throw new IllegalArgumentException("Expense not found");
    }

    public void deleteExpense(int id) throws IOException {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId() == id) {
                expenses.remove(i);
                System.out.println("# Expense deleted successfully");
                ExpenseStorage.save(expenses);
                return;
            }
        }
        throw new IllegalArgumentException("Expense not found");
    }

    public void listExpenses() {
        System.out.printf("%-5s %-12s %-15s %-10s %s%n", "#ID", "Date", "Description", "Category", "Amount");
        System.out.println("-------------------------------------------");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }public void listExpenses(String category) {
        System.out.printf("%-5s %-12s %-15s %-15s %s%n", "#ID", "Date", "Description", "Category", "Amount");
        System.out.println("-------------------------------------------");
        for (Expense expense : expenses) {
            if (expense.getCategory().equals(category)){
                System.out.println(expense);
            }
        }
    }

    public void summaryExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.printf(Locale.US, "# Total expenses: $%.2f%n", total);
    }

    public void summaryMonthExpense(LocalDate date) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.getDate().getMonth() == date.getMonth()) {
                total += expense.getAmount();
            }
        }
        if (total == 0) throw new IllegalArgumentException("Invalid month");

        System.out.println(String.format(Locale.US, "# Total expenses for %s: $%.2f",
                date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH), total));
    }
}
