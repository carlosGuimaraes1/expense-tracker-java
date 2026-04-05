# Expense Tracker Java

A command-line expense tracker application built in Java to manage personal finances.

Project URL: https://roadmap.sh/projects/expense-tracker

## Features

- Add, update, and delete expenses
- List all expenses
- Filter expenses by category
- View a summary of all expenses
- View a summary of expenses for a specific month
- Set a monthly budget with alerts when exceeded or near the limit
- Export expenses to a CSV file

## Technologies

- Java 21
- NIO.2 for file handling
- Java Serialization for data persistence

## Project Structure

```
src/
└── main/
    └── java/
        ├── controller/
        │   └── ExpenseController.java
        ├── model/
        │   ├── Expense.java
        │   └── Budget.java
        ├── service/
        │   ├── ExpenseManager.java
        │   └── BudgetManager.java
        ├── storage/
        │   ├── ExpenseStorage.java
        │   └── BudgetStorage.java
        └── Main.java
```

## Commands

### Expenses

```bash
# Add an expense
$ expense-tracker add --description "Lunch" --amount 20
# Expense added successfully (ID: 1)

# Add an expense with category
$ expense-tracker add --description "Lunch" --amount 20 --category "Food"
# Expense added successfully (ID: 1)

# Update an expense
$ expense-tracker update --id 1 --description "Lunch" --amount 25
# Expense updated successfully

# Delete an expense
$ expense-tracker delete --id 1
# Expense deleted successfully

# List all expenses
$ expense-tracker list
# ID    Date         Description     Category   Amount
# -------------------------------------------
# 1     2026-04-03   Lunch           Food       $20.00

# List expenses by category
$ expense-tracker list --category "Food"

# Summary of all expenses
$ expense-tracker summary
# Total expenses: $20.00

# Summary by month
$ expense-tracker summary --month 4
# Total expenses for April: $20.00

# Export to CSV
$ expense-tracker export
# Expense exported successfully to /path/to/expense/expense.csv
```

### Budget

```bash
# Add a monthly budget
$ expense-tracker budget add --month 4 --amount 500
# Budget added successfully

# Update a monthly budget
$ expense-tracker budget update --month 4 --amount 600
# Budget updated successfully

# Delete a monthly budget
$ expense-tracker budget delete --month 4
# Budget deleted successfully

# List budget for a specific month
$ expense-tracker budget list --month 4
```

## Data Storage

All data is persisted using Java Serialization:

```
expense/
├── expense.ser   → expenses data
├── budget.ser    → budget data
└── expense.csv   → exported CSV file
```

## Error Handling

- Invalid number format
- Negative amounts
- Non-existent expense or budget IDs
- Missing required arguments
- Budget exceeded warning
- Budget at 90% warning
