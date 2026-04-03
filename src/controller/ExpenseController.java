package controller;

import service.ExpenseManager;

import java.io.IOException;
import java.time.LocalDate;

public class ExpenseController {
    ExpenseManager manager = new ExpenseManager();

    public void handleArgs(String[] args) {
        if (args.length == 0) {
            return;
        }
        try {
            switch (args[0]) {
                case "add":
                    handleAdd(args);
                    break;
                case "delete":
                    handleDelete(args);
                    break;
                case "update":
                    handleUpdate(args);
                    break;
                case "list":
                    manager.listExpenses();
                    break;
                case "summary":
                    handleSummaryOrMonth(args);
                    break;
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("ERROR: action not be execute "+ e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    private void handleAdd(String[] args) throws IOException {
        String description = "";
        String stringAmount = "";
        double amount;
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
        }

        if (description.isEmpty() || stringAmount.isEmpty()) {
            throw new IllegalArgumentException("id, description and amount are required");
        }
        amount = Double.parseDouble(stringAmount);
        manager.addExpense(description, amount);
    }

    private void handleUpdate(String[] args) throws IOException {
        String description = "";
        String stringAmount = "";
        String stringId = "";
        int id = 0;
        int amount = 0;

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
        id = Integer.parseInt(stringId);
        amount = Integer.parseInt(stringAmount);
        manager.updateExpense(id, amount, description);
    }

    private void handleDelete(String[] args) throws IOException {
        String stringId = "";
        int id;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--id") && i + 1 < args.length) {
                stringId = args[i + 1];
            }
        }
        if (stringId.isEmpty()) {
            throw new IllegalArgumentException("id, are required");
        }

        id = Integer.parseInt(stringId);
        manager.deleteExpense(id);
    }

    private void handleSummaryOrMonth(String[] args) {

        String sMonth = null;
        int month;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--month")) {
                if (i + 1 < args.length) {
                    sMonth = args[i + 1];
                }
            }
        }
        if (sMonth == null){
            manager.summaryExpenses();
            return;
        }
        month = Integer.parseInt(sMonth);
        LocalDate date = LocalDate.now();
        date = date.withMonth(month);
        manager.summaryMonthExpense(date);
    }
}
