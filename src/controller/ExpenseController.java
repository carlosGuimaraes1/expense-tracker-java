package controller;

import service.BudgetManager;
import service.ExpenseManager;

import java.io.IOException;
import java.time.LocalDate;

public class ExpenseController {
    ExpenseManager manager = new ExpenseManager();
    BudgetManager budgetManager = new BudgetManager();

    public void handleArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Argument Invalid");
        }
        try {
            switch (args[0]) {
                case "add":
                    handleExpenseAdd(args);
                    break;
                case "delete":
                    handleExpenseDelete(args);
                    break;
                case "update":
                    handleExpenseUpdate(args);
                    break;
                case "list":
                    handleExpenseList(args);
                    break;
                case "summary":
                    handleExpenseSummaryOrMonth(args);
                    break;
                case "budget":
                    handleBudget(args);
                    break;
                case "export":
                    manager.exportCsv();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number format");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void handleBudget(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Argument Invalid");
        }
        try {
            switch (args[1]) {
                case "add":
                    handleBudgetAdd(args);
                    break;
                case "delete":
                    handleBudgetDelete(args);
                    break;
                case "update":
                    handleBudgetUpdate(args);
                    break;
                case "list":
                    handleBudgetList(args);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number format");
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void handleExpenseAdd(String[] args) throws IOException {
        String description = "";
        String stringAmount = "";
        String category = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--description")) {
                if (i + 1 < args.length) {
                    description = args[i + 1];
                }
            }
            if (args[i].equals("--amount")) {
                if (i + 1 < args.length) {
                    stringAmount = args[i + 1];
                }
            }
            if (args[i].equals("--category")) {
                if (i + 1 < args.length) {
                    category = args[i + 1];
                }
            }
        }

        if (description.isEmpty() || stringAmount.isEmpty()) {
            throw new IllegalArgumentException("id, description and amount are required");
        }
        double amount = Double.parseDouble(stringAmount);
        manager.addExpense(description, amount, category);
    }

    private void handleExpenseUpdate(String[] args) throws IOException {
        String description = "";
        String stringAmount = "";
        String stringId = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--id") && i + 1 < args.length) {
                stringId = args[i + 1];
            } else if (args[i].equals("--description") && i + 1 < args.length) {
                description = args[i + 1];
            } else if (args[i].equals("--amount") && i + 1 < args.length) {
                stringAmount = args[i + 1];
            }
        }
        if (description.isEmpty() || stringAmount.isEmpty() || stringId.isEmpty()) {
            throw new IllegalArgumentException("id, description and amount are required");
        }
        int id = Integer.parseInt(stringId);
        double amount = Integer.parseInt(stringAmount);
        manager.updateExpense(id, amount, description);
    }

    private void handleExpenseDelete(String[] args) throws IOException {
        String stringId = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--id") && i + 1 < args.length) {
                stringId = args[i + 1];
            }
        }
        if (stringId.isEmpty()) {
            throw new IllegalArgumentException("id, are required");
        }

        int id = Integer.parseInt(stringId);
        manager.deleteExpense(id);
    }

    private void handleExpenseList(String[] args) {
        String category = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--category") && i + 1 < args.length) {
                category = args[i + 1];
            }
        }
        if (category.isEmpty()) {
            manager.listExpenses();
        } else {
            manager.listExpenses(category);
        }
    }

    private void handleExpenseSummaryOrMonth(String[] args) {
        String sMonth = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--month") && i + 1 < args.length) {
                sMonth = args[i + 1];
            }
        }
        if (sMonth == null) {
            manager.summaryExpenses();
            return;
        }
        int month = Integer.parseInt(sMonth);
        LocalDate date = LocalDate.now();
        date = date.withMonth(month);
        manager.summaryMonthExpense(date);
    }

    public void handleBudgetAdd(String[] args) throws IOException {
        String sValue = "";
        String sMonth = "";
        double value = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--month") && i + 1 < args.length) {
                sMonth = args[i + 1];
            }
            if (args[i].equals("--amount") && i + 1 < args.length) {
                sValue = args[i + 1];
            }
        }
        if (sMonth.isEmpty() || sValue.isEmpty()) {
            throw new IllegalArgumentException("Month and amount are required");
        }
        value = Integer.parseInt(sValue);
        int monthInt = Integer.parseInt(sMonth);
        LocalDate month = LocalDate.now().withMonth(monthInt);
        budgetManager.addBudget(value, month);
    }

    public void handleBudgetDelete(String[] args) throws IOException {
        String sMonth = "";
        for (int i = 0; i < args.length; i++) {
            if (args.equals("--month") && i + 1 < args.length) {
                sMonth = args[i+1];
            }
        }
        if (sMonth.isEmpty()){
            throw new IllegalArgumentException("Month are required");
        }
        int monthInt = Integer.parseInt(sMonth);
        LocalDate month = LocalDate.now().withMonth(monthInt);
        budgetManager.deleteBudget(month);
    }

    public void handleBudgetUpdate(String[] args) throws IOException {
        String sValue = "";
        String sMonth = "";
        double value = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--month") && i + 1 < args.length) {
                sMonth = args[i + 1];
            }
            if (args[i].equals("--amount") && i + 1 < args.length) {
                sValue = args[i + 1];
            }
        }
        if (sMonth.isEmpty() || sValue.isEmpty()) {
            throw new IllegalArgumentException("Month and amount are required");
        }
        value = Integer.parseInt(sValue);
        int monthInt = Integer.parseInt(sMonth);
        LocalDate month = LocalDate.now().withMonth(monthInt);
        budgetManager.updateBudget(value, month);
    }

    public void handleBudgetList(String[] args) throws IOException {
        String sMonth = "";
        for (int i = 0; i <args.length ; i++) {
            if (args[i].equals("--month")&&i+1<args.length){
                sMonth = args[i+1];
            }
        }
        if (sMonth.isEmpty()){
            throw new IllegalArgumentException("Month are required");
        }
        int monthInt = Integer.parseInt(sMonth);
        LocalDate month = LocalDate.now().withMonth(monthInt);
        budgetManager.listBudget(month);
    }
}
