package model;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Budget implements Serializable {
    @Serial
    private static final long serialVersionUID = -3422141467269330242L;
    private double value;
    private LocalDate date;

    public Budget(double value, LocalDate date) {
        this.value = value;
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%-12s %-10s $%.2f",
                date.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                date.getYear(),
                value);
    }
}
