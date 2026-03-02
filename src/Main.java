import controller.ExpenseController;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ExpenseController controller = new ExpenseController();
        controller.handleArgs(args);
    }
}
