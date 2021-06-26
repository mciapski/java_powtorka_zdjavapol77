package com.expenses;

import java.io.IOException;

public class AppRunner {
  public static void main(String[] args) throws IOException, InterruptedException {
    ExpenseApp expenseApp = new ExpenseApp();

    expenseApp.run("data.csv");
  }
}
