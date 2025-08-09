package datastructures;

/**
 * MaintenanceRecord class - represents a completed maintenance service record
 * Stores details of maintenance work done on vehicles
 */
public class MaintenanceRecord {
    // Maintenance record attributes
    public String date;
    public String partReplaced;
    public double cost;

    // Constructor to create a maintenance record
    public MaintenanceRecord(String date, String partReplaced, double cost) {
        this.date = date;
        this.partReplaced = partReplaced;
        this.cost = cost;
    }

    // Display maintenance record information in table format
    public void displayInfo() {
        System.out.printf("| %-12s | %-25s | %-10.2f |%n", 
            date, partReplaced, cost);
    }

    // Static method to display table header for maintenance records
    public static void displayTableHeader() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(27) + "+" + "-".repeat(12) + "+");
        System.out.printf("| %-12s | %-25s | %-10s |%n", 
            "Date", "Service/Part", "Cost (GH₵)");
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(27) + "+" + "-".repeat(12) + "+");
    }

    // Static method to display table footer
    public static void displayTableFooter() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(27) + "+" + "-".repeat(12) + "+");
    }
}
