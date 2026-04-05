package service;

import model.Budget;
import model.Expense;
import storage.BudgetStorage;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    static List<Budget> budgetList = new ArrayList<>();

    public BudgetManager() {
        try {
            budgetList = BudgetStorage.load();
        } catch (NoSuchFileException e) {
            budgetList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data " + e.getMessage());
        }
    }

    public  void addBudget(double value, LocalDate date) throws IOException {
        budgetList.add(new Budget(value, date));
        System.out.println("Budget added successfully");
        BudgetStorage.save(budgetList);
    }

    public  void updateBudget(double newValue, LocalDate date) throws IOException {
        for (Budget budget : budgetList) {
            if (budget.getDate().getMonth().equals(date.getMonth())) {
                budget.setValue(newValue);
                budget.setDate(date);
                System.out.println("Budget update successfully");
                BudgetStorage.save(budgetList);
                return;
            }
        }
        throw new IllegalArgumentException("budget  not found");
    }

    public  void deleteBudget(LocalDate date) throws IOException {
        for (Budget budget : budgetList) {
            if (budget.getDate().getMonth().equals(date.getMonth())) {
                budgetList.remove(budget);
                System.out.println("budget removed successfully");
                BudgetStorage.save(budgetList);
                return;
            }
        }
        throw new IllegalArgumentException("Expense not found");
    }

    public  void listBudget(LocalDate date) throws IOException {
        for (Budget budget : budgetList) {
            if (budget.getDate().getMonth().name().equals(date.getMonth().toString())) {
                System.out.printf("%-12s %-10s %s%n", "Month", "Year", "Amount");
                System.out.println("--------------------------------");
                System.out.println(budget);
                return;
            }
        }
        System.out.println("Not found");
    }

    public static boolean checkBudget(LocalDate date, List<Expense> expenseList) {
        double total = 0;
        for (Expense expense : expenseList) {
            if (expense.getDate().getMonth().equals(date.getMonth())) {
                total += expense.getAmount();
            }
        }
        for (Budget budget : budgetList) {
            if (budget.getDate().getMonth().equals(date.getMonth())) {
                if (budget.getValue() > total) {
                    System.out.println("The budget exceeded the limit.");
                    return false;
                } else if (budget.getValue() >= (total * 0.90)) {
                    System.out.println("The budget exceeded 90 percent of the limit..");
                    return true;
                }
            }
        }
        System.out.println("Budget within the limit.");
        return true;
    }
}
