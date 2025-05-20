package com.expense.entity;

import java.time.LocalDate;

public class Transaction {
	private String type;
	private LocalDate date;
	private String category;
	private double amount;
	private String description;
	
	
	public Transaction(String type, LocalDate date, String category, double amount, String description) {
		this.type = type;
		this.date = date;
		this.category = category;
		this.amount = amount;
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	 public String toCSV() {
	        return String.format("%s,%s,%s,%.2f,%s", type, date, category, amount, description);
    }
	 
	public static Transaction fromCSV(String line) {
		String[] parts = line.split(",");
		return new Transaction(
			parts[0],
			LocalDate.parse(parts[1]),
			parts[2],
            Double.parseDouble(parts[3]),
            parts[4]
		);
	}
	
	

}
