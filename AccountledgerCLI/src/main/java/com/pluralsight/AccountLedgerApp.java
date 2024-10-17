package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner; // import for scanner use


public class AccountLedgerApp {

    public static void main(String[] args) {
        // start the application by displaying the home screen menu
        homeScreenMenu();
    }

    public static void homeScreenMenu() {
        // create a scanner class for user input
        Scanner scanner = new Scanner(System.in);
        boolean run = true; // created a variable to control the loop

        // loop until user decides to exit
        while (run) {
            // provide home screen options
            //
            System.out.println(" Welcome to the Account Ledger!");
            System.out.println(" Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("E) Exit");


            // depending on the users choice (uppercase)
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
                displayLedgerMenu();// call the method that displays the Ledger screen
            } else {
                // user input is invalid it will show error message
                System.out.println(" Sorry, Thats not a Valid option, Please try again");
            }
        }
    }

    // Add Method for adding a deposit
    public static void addDeposit(Scanner scanner) {

        // prompt for dates of the deposit
        System.out.println(" Lets add a new deposit!");

        // Get the date
        System.out.println("Enter the date of deposit (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        // Get the Time
        System.out.println(" Enter the time of the Deposit");
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

    public static void displayLedgerMenu() {
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
            } else if (choose.equals("P")) {
                displayNegativeEntries();
            } else if (choose.equals("R")) {
                displayReportMenu();
            }
        }

        // depending on the users choice
        String choice = scanner.nextLine().toUpperCase().trim();


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

    // Method to display negative entries or payments
    public static void displayNegativeEntries() {
        // print a message that indicates the users action

        // Now create a buffered reader thing to  read the csv file
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Check if there are 5 parts
                if (parts.length == 5) {
                    String amountStr = parts[4].trim(); // Get the amount part

                    try {
                        // Convert the amount to a double
                        double amount = Double.parseDouble(amountStr);

                        // If the amount is negative, print the line
                        if (amount < 0) {
                            System.out.println(line); // Show negative entry
                        }
                    } catch (NumberFormatException e) {
                        // Print the problematic amount for debugging
                        System.out.println("Invalid amount format: '" + amountStr + "' in line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the transactions: " + e.getMessage());
        }
    }


    public static void displayReportMenu() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        boolean run = true; // Variable to control the loop

        // Loop until the user decides to go back
        while (run) {
            // Display options for reports
            System.out.println(" Welcome to the Reports Menu!");
            System.out.println(" Choose an option:");
            System.out.println("1) View Month to Date ");
            System.out.println("2) View Previous Month ");
            System.out.println("3) View Year to date ");
            System.out.println("4) View Previous Year");
            System.out.println("5) Search By Vendor");

            // Get the user's choice
            String choice = scanner.nextLine();

            //Handle the users choice with if - else statements again
            if (choice.equals("1")) {
                viewMonthToDate(); // call the method to view month to date
            } else if (choice.equals("2")) {
                viewPreviousMonth(); // call method to view previous month
            } else if (choice.equals("3")) {
                viewYearToDate(); // call method to view year to date
            } else if (choice.equals("4")) {
                viewPreviousYear(); // call method to view previous year
            } else if (choice.equals("5")) {
                searchByVendor(); //call method to search by vendor
            } else if (choice.equals("6")) { // if "6" selected it will exit back to the main menu
                run = false;
            }
        }
    }

    // Method to display transactions for the current month
    public static void viewMonthToDate() {
        System.out.println("Displaying Month-to-Date transactions..."); // Inform the user about the action

        // Try to read from the transactions file
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line; // Variable to hold each line of the file
            String currentMonth = "2024-10"; // Set this to the current month (YYYY-MM)

            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                // Split the line into parts using the '|' character
                String[] parts = line.split("\\|");
                String date = parts[0]; // Get the date from the first part

                // Check if the date starts with the current month
                if (date.startsWith(currentMonth)) {
                    System.out.println(line); // Print the matching transaction
                }
            }
        } catch (IOException e) {
            // Handle any errors that occur while reading the file
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
    // Method to display transactions for the previous month
    public static void viewPreviousMonth() {
        System.out.println("Displaying Previous Month transactions...");

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the previous month
        LocalDate previousMonthDate = currentDate.minusMonths(1);
        String previousMonth = previousMonthDate.toString().substring(0, 7); // Get YYYY-MM format

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line; // Variable to hold each line of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the line by '|'
                String date = parts[0]; // Get the date from the first part

                if (date.startsWith(previousMonth)) { // Check if the date starts with the previous month
                    System.out.println(line); // Print the matching transaction
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage()); // Handle errors
        }
    }
    // Method to display transactions for the current year
    public static void viewYearToDate() {
        System.out.println("Displaying Year-to-Date transactions...");

        // Get the current year
        LocalDate currentDate = LocalDate.now();
        String currentYear = String.valueOf(currentDate.getYear()); // Get the current year as a string

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line; // Variable to hold each line of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the line by '|'
                String date = parts[0]; // Get the date from the first part

                if (date.startsWith(currentYear)) { // Check if the date starts with the current year
                    System.out.println(line); // Print the matching transaction
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage()); // Handle errors
        }
    }
    // Method to display transactions for the previous year
    public static void viewPreviousYear() {
        System.out.println("Displaying Previous Year transactions...");

        // Get the current year and calculate the previous year
        LocalDate currentDate = LocalDate.now();
        int previousYear = currentDate.getYear() - 1; // Calculate the previous year

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line; // Variable to hold each line of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the line by '|'
                String date = parts[0]; // Get the date from the first part

                if (date.startsWith(String.valueOf(previousYear))) { // Check if the date starts with the previous year
                    System.out.println(line); // Print the matching transaction
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage()); // Handle errors
        }
    }
    // Method to search transactions by vendor
    public static void searchByVendor() {
        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input
        System.out.println("Enter the vendor name you want to search for: "); // Prompt user
        String vendorName = scanner.nextLine().trim(); // Read vendor name and remove extra spaces

        System.out.println("Searching for transactions by vendor: " + vendorName);

        // Try to open and read the transactions file
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line; // Variable to hold each line of the file
            boolean found = false; // Flag to check if we found any transactions

            // Read each line of the file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Split the line into parts using '|'

                // Check if we have enough parts to get the vendor
                if (parts.length >= 4) {
                    String vendor = parts[3]; // Get the vendor from the fourth part

                    // Check if the vendor matches the user input
                    if (vendor.equalsIgnoreCase(vendorName)) {
                        System.out.println(line); // Print the matching transaction
                        found = true; // We found at least one match
                    }
                }
            }

            // If we didn't find any transactions, tell the user
            if (!found) {
                System.out.println("No transactions found for vendor: " + vendorName);
            }
        } catch (IOException e) {
            // Handle any errors that occur while reading the file
            System.out.println("Error reading transactions: " + e.getMessage());
        }
    }
}






