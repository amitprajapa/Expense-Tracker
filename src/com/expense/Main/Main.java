package com.expense.Main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.expense.FileHandler.FileHandler;
import com.expense.TransactionManager.TransactionManager;
import com.expense.entity.Transaction;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);
	private static final TransactionManager TRANSACTION_MANAGER = new TransactionManager();
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) {
		while(true) {
			System.out.println("------------Expense Tracker------------");
			System.out.println("1. Add Income");
			System.out.println("2. Add Expense");
			System.out.println("3. View Monthly Summery");
			System.out.println("4. Load Transactions from File");
			System.out.println("5. Save Transactions to File");
			System.out.println("6. Exit");
			System.out.println("Choose an option: ");
			
			int option = Integer.parseInt(scanner.nextLine());
			
			switch (option) {
			case 1 -> addTransaction("income");
			case 2 -> addTransaction("expense");
			case 3 -> {
				System.out.println("Enter Month and year (yyyy-MM): ");
				String monthYear = scanner.nextLine();
				TRANSACTION_MANAGER.printMonthlySummery(monthYear);
			}
			case 4 ->{
				 System.out.print("Enter file path to load: ");
                 String loadPath = scanner.nextLine();
                 TRANSACTION_MANAGER.addTransaction(FileHandler.loadFromFile(loadPath));
			}
			case 5 -> {
                System.out.print("Enter file path to save: ");
                String savePath = scanner.nextLine();
                FileHandler.saveToFile(savePath, TRANSACTION_MANAGER.getAllTransactions());
            }
			case 6 -> {
                System.out.println("Exiting. Goodbye!");
                return;
            }
			default -> System.out.println("Oops! Invalid option. Try again.");
		}
			
		}
		

	}
	private static void addTransaction(String type) {
		System.out.println("Enter date (yyyy-MM-dd): ");
		LocalDate date = LocalDate.parse(scanner.nextLine(),DATE_TIME_FORMATTER);
		
        System.out.print("Enter category (e.g., " + (type.equals("income") ? "salary/business" : "food/rent/travel") + "): ");
        String category = scanner.nextLine().toLowerCase();
        
        System.out.println("Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        
        System.out.println("Enter description");
        String description = scanner.nextLine();
        
        TRANSACTION_MANAGER.addTransaction(new Transaction(type, date, category, amount, description));
	}

}
