package investWise;

import java.util.Scanner;

/**
 * Main class to interact with the user, display financial reports and export options (PDF/Excel).
 */
public class Main {

    /**
     * Main method to run the application and handle user interactions.
     * This method displays options for the user to view or export reports.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Creating a portfolio for testing purposes
        Portfolio portfolio = new Portfolio();
        portfolio.addAsset(new Stock(1, "Stock1", 100, 50.0f, "2024-01-01"));
        portfolio.addAsset(new Gold(2, "Gold1", 2, 1800.0f, "2023-12-01"));
        portfolio.addAsset(new Cryptocurrency(3, "BTC", 10, 30000.0f, "2025-04-01"));

        // Report generator and user interface
        ReportGenerator reportGenerator = new ReportGenerator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display options for the user
            System.out.println("Choose an option:");
            System.out.println("1. Reports");
            System.out.println("2. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    // Reports option
                    System.out.println("You have selected Reports.");
                    
                    // Generate and display the report
                    reportGenerator.generateReport(portfolio.getAssets());

                    // Ask if the user wants to export
                    System.out.println("Do you want to export the report? (y/n)");
                    String exportChoice = scanner.nextLine();

                    if (exportChoice.equalsIgnoreCase("y")) {
                        // Ask for export format (PDF or Excel)
                        System.out.println("Choose report format:");
                        System.out.println("1. PDF");
                        System.out.println("2. Excel");

                        int formatChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume the newline

                        // No need to ask for file path anymore
                        if (formatChoice == 1) {
                            // Export as PDF (it uses the fixed path inside the ReportGenerator)
                            reportGenerator.exportReportToPDF(portfolio.getAssets());
                        } /*else if (formatChoice == 2) {
                            // Export as Excel
                            reportGenerator.exportReportToExcel(portfolio.getAssets(), "C:\\Users\\Lenovo\\Documents\\ZakatReport.xlsx");
                        }*/ else {
                            System.out.println("Invalid format.");
                        }
                    }
                    break;

                case 2:
                    // Exit the application
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }
}
