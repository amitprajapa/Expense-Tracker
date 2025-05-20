package com.expense.TransactionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expense.entity.Transaction;

public class TransactionManager {
	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public void addTransaction(List<Transaction> list) {
		transactions.addAll(list);
	}
	
	public List<Transaction> getAllTransactions(){
		return transactions;
	}
	
	public void printMonthlySummery(String monthYear) {
		Map<String, Double> incomeMap = new HashMap<String, Double>();
		Map<String, Double> expenseMap = new HashMap<>();
		double totalIncome = 0;
		double totalExpense = 0;
		
		for(Transaction t : transactions) {
			String currentMonthYear = t.getDate().toString().substring(0, 7);
			if (currentMonthYear.equals(monthYear)) {
                if (t.getType().equalsIgnoreCase("income")) {
                    incomeMap.put(t.getCategory(), incomeMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                    totalIncome += t.getAmount();
                } else {
                    expenseMap.put(t.getCategory(), expenseMap.getOrDefault(t.getCategory(), 0.0) + t.getAmount());
                    totalExpense += t.getAmount();
                }
            }
        }
		System.out.println("\n╔════════════════════════════════════════╗");
	    System.out.printf("║     Monthly Summary: %s            ║%n",monthYear);
	    System.out.println("╠════════════════════════════════════════╣");

	    System.out.println("║ Income:                                 ║");
	    incomeMap.forEach((k, v) -> System.out.printf("║   %-15s : ₹%-10.2f       ║%n", k, v));
	    System.out.printf("║   %-15s : ₹%-10.2f       ║%n", "Total Income", totalIncome);

	    System.out.println("╠════════════════════════════════════════╣");

	    System.out.println("║ Expenses:                              ║");
	    expenseMap.forEach((k, v) -> System.out.printf("║   %-15s : ₹%-10.2f       ║%n", k, v));
	    System.out.printf("║   %-15s : ₹%-10.2f       ║%n", "Total Expense", totalExpense);

	    System.out.println("╠════════════════════════════════════════╣");

	    double savings = totalIncome - totalExpense;
	    String savingsStr = String.format("₹%.2f", savings);
	    String savingsLabel = savings >= 0 ? "Net Savings" : "Net Deficit";
	    System.out.printf("║ %-15s : %-16s ║%n", savingsLabel, savingsStr);

	    System.out.println("╚════════════════════════════════════════╝");
	}
}
