package com.pluralsight;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AccountLedgerApp {

    public static void main(String[] args) {
        homeScreenMenu();
    }

    public static void homeScreenMenu(){
        // create a scanner class
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        //Main app loop for prompts/ questions
        while (run) {
            // provide home screen options
            System.out.println(" Welcome to the Account Ledger!");
            System.out.println(" Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("E) Exit");


            // depending on the users choice
            String choice = scanner.nextLine().toUpperCase().trim();

            // if else statements to handle the users choice
            if (choice.equals("D")) {
                addDeposit(scanner); // call the method to add the deposit
            } else if (choice.equals("E")) { // If "E" is selected exit loop
                run = false; // Set run to false too exit loop
                System.out.println("Thank you for your Account Ledger");
            } else if (choice.equals("P")) {
                makePayment(scanner);// call method to make payment if selection is "P"
            } else if (choice.equals("L")) {
                displayLedgerMenu();
            } else {
                // user input is invalid
                System.out.println(" Sorry, Thats not a Valid option, Please try again");
            }
        }
    }

    // Add Method for adding a deposit
    public static void addDeposit(Scanner scanner) {

        // prompt for dates of the deposit
        System.out.println(" Lets add a new deposit!");
        System.out.println("Enter the date of deposit (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        //Prompt for deposit time
        System.out.println(" Enter the time of the deposit (HH.MM.SS)");
        String Time = scanner.nextLine();

        //Prompt for the deposit description
        System.out.println(" What is your deposit description?");
        String description = scanner.nextLine();

        //Prompt for the vendor name
        System.out.println(" Who is the vendor for this deposit?");
        String vendor = scanner.nextLine();

        //Prompt for the amount of the deposit
        System.out.println(" What is the amount of this deposit: ");
        String amount = scanner.nextLine();

        //Concatenate the transaction details into a single string
        String transaction = date + "|" + Time + "|" + description + "|" + vendor + "|" + amount + "\n";

// Save the transaction to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(transaction); // Write the transaction to the file
            System.out.println("Great! Your deposit has been added successfully."); // Confirm success
        } catch (IOException e) {
            // Handle any I/O exceptions that occur
            System.out.println("Oh no! There was an error saving your transaction: " + e.getMessage());
        }

    }

    // Add a method to make a payment
    public static void makePayment(Scanner scanner) {
        // prompt the user for payment info
        // prompt for payment date
        System.out.println(" Lets make a payment (Debit)!");
        System.out.println(" Enter the payment date (YYYY-MM-DD)");
        String date = scanner.nextLine();

        //prompt for payment time
        System.out.println(" Enter the payment time (HH:MM:SS): ");
        String time = scanner.nextLine();

        //prompt for description
        System.out.println(" What is the description for this payment?");
        String description = scanner.nextLine();

        //prompt for vendor
        System.out.println("Who is the vendor for this payment");
        String vendor = scanner.nextLine();

        // prompt for the amount of payment
        System.out.println(" Enter the amount for this payment( use - sign for debits ");
        String amount = scanner.nextLine();

        // Lets combine all payments info into a single transaction by concatenating
        String transaction = date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n";

// Save the payment transaction to the CSV file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            // Open the transactions.csv file in append mode
            writer.write(transaction); // Write the payment string to the file
            System.out.println("Great! Your payment has been added successfully.");
        } catch (IOException e) {
            // Handle any I/O errors that may occur
            System.out.println("ERROR! There was an issue saving your payment: " + e.getMessage());
        }
    }
    // Method to display Ledger Screen
    public static void displayLedgerMenu(){
        // create a scanner class
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        //Main app loop for prompts/ questions
        while (run) {
            // provide home screen options
            System.out.println(" Welcome to the Account Ledger!");
            System.out.println(" Choose an option:");
            System.out.println("A) Display All Deposits");
            System.out.println("P) Display negative entries or (payments) ");
            System.out.println("R) Reports");

            // Create a variable for the user to make a selection
            String choose = scanner.nextLine();

            // Use if and else statements to handle the users choice
            if (choose.equals("A")) {
                displayLedgerAllEntries();
            }




            // depending on the users choice
            String choice = scanner.nextLine().toUpperCase().trim();

            // if else statements to handle the users choice
            if (choice.equals("D")) {
                addDeposit(scanner); // call the method to add the deposit
            } else if (choice.equals("E")) { // If "E" is selected exit loop
                run = false; // Set run to false too exit loop
                System.out.println("Thank you for your Account Ledger");
            } else if (choice.equals("P")) {
                makePayment(scanner);// call method to make payment if selection is "P"
            } else if (choice.equals("L")) {
                displayLedgerMenu();
            } else {
                // user input is invalid
                System.out.println(" Sorry, Thats not a Valid option, Please try again");
            }
        }
       }

    // Method to display all entries in ledger
    public static void displayLedgerAllEntries() {
    // print message to let the user know we are displaying the ledger
        System.out.println("Here is your transaction ledger:");


        // try to read from the csv file
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            // Read each line of the file until the end
            while ((line = reader.readLine()) != null) {
                // Print each transaction to the console
                System.out.println(line);
            }
        } catch (IOException e) {
            // Handle any I/O errors that may occur
            System.out.println("Oh no! There was an error reading the ledger: " + e.getMessage());

        }

    }
}















