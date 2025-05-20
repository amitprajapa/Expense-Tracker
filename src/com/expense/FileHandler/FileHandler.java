package com.expense.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.expense.entity.Transaction;

public class FileHandler {

    public static List<Transaction> loadFromFile(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(Transaction.fromCSV(line));
            }
            System.out.println("Loaded " + transactions.size() + " transactions.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return transactions;
    }

    public static void saveToFile(String filePath, List<Transaction> transactions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Transaction t : transactions) {
                bw.write(t.toCSV());
                bw.newLine();
            }
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}

