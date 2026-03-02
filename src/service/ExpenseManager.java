package service;

import model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    List<Expense> expenses = new ArrayList<>();

    /*
    IMPLEMENTAR AS EXCEPTION NOS METODOS.
     */

    // 1 adiciona despesas
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

    // 2 atualiza despesas
    public void updateExpense(int id, double amount, String description) {
        for (Expense expense : expenses) {
            if (expense.getId() == id) {
                expense.setAmount(amount);
                expense.setDescription(description);
                System.out.println("Updated expense");
                break;
            }
        }
        throw new IllegalArgumentException("Expense not found");
    }

    // 3 deleta despesas
    public void deleteExpense(int id)throws IllegalArgumentException{
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId() == id) {
                expenses.remove(i);
                System.out.println("model.Expense was excluded");
                break;
            }
        }
        throw new IllegalArgumentException("Expense not found");
    }

    public void listExpenses() {
        System.out.println(expenses);
    }

    public void summaryExpenses(){
        for (Expense expense : expenses) {
            System.out.println(expense.getAmount());
            System.out.println(expense.getDescription());
        }
    }

    public void summaryMonthExpense(LocalDate date)throws IllegalArgumentException{
        for (Expense expense : expenses) {
            if (expense.getDate().getMonth()==date.getMonth()){
                System.out.println(expense);
                return;
            }
        }
        throw new IllegalArgumentException("Invalid month");
    }
}
