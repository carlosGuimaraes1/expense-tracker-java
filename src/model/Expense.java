package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class Expense implements Serializable {
    @Serial
    private static final long serialVersionUID = 2735066815938557770L;
    private int id;
    private double amount;
    private String description;
    private String category;
    private LocalDate date;

    public Expense(int id, double amount, String description, LocalDate date) {
        this.id = id;
        setAmount(amount);
        this.description = description;
        this.date = date;
    }

    public Expense(int id, double amount, String description, String category, LocalDate date) {
        this.id = id;
        setAmount(amount);;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws IllegalArgumentException {
        if (amount < 0) {
            throw new IllegalArgumentException("Invalid number");
        }
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%-5s %-12s %-15s %-10s $%.2f", "# " + id, date, description, category, amount);    }

}
