package controller;

import service.ExpenseManager;

import java.time.LocalDate;

public class ExpenseController {
    ExpenseManager manager = new ExpenseManager();

    public void handleArgs(String[] args) {
        if (args.length == 0) {
            return;
        }
        switch (args[0]) {
            case "add":
                handleAdd(args);
                break;
            case "delete":
                handleDelete(args);
                break;
            case "list":
                manager.listExpenses();
                break;
            case "summary":
                handleSummaryOrMonth(args);
                break;
        }
    }

    private void handleAdd(String[] args) {
        String  description = "";
        String stringAmout = "";
        double amount;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--description")) {
                if (i + 1 < args.length) {
                    description = args[i + 1];
                }
            }
            if (args[i].equals("--amount")) {
                if (i + 1 < args.length) {
                    stringAmout = args[i + 1];
                }
            }
        }

        if (stringAmout == null || description == null) {
            System.out.println("ERRO: Values not defined");
            return;
        }
        try {
            amount = Double.parseDouble(stringAmout);
            manager.addExpense(description, amount);
        } catch (NumberFormatException e) {
            System.out.println("ERRO: The value provided must be a valid number " + e);
        }
    }

    private void handleDelete(String[] args) {
        String stringId = "";
        int id;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--id")) {
                if (i + 1 < args.length) {
                    stringId = args[i + 1];
                }
            }
        }
        if (stringId == null) {
            System.out.println("ERROR: Value not defined");
            return;
        }
        try {
            id = Integer.parseInt(stringId);
            manager.deleteExpense(id);
        } catch (NumberFormatException e) {
            System.out.println("ERRO: The value provided must be a valid number " + e);
        }
    }
    private void handleSummaryOrMonth(String[] args){
        LocalDate date = null;
        String sMonth = null;
        int month;
        for (int i = 0; i <args.length; i++) {
            if (args[i].equals("--month")){
                if (i+1<args.length){
                    sMonth = args[i+1];
                }else{
                    manager.summaryExpenses();
                }
            }
        }
        try {
            month = Integer.parseInt(sMonth);
            date.withMonth(month);
        }catch (NumberFormatException | NullPointerException e){
            System.out.println("ERRO: The value provied must be a valid number " + e);
        }
    }
}
