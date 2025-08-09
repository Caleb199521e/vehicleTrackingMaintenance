package datastructures;

/**
 * MaintenanceTask class - represents a scheduled maintenance task
 * Uses custom comparison for priority ordering (lower mileage = higher priority)
 */
public class MaintenanceTask {
    // Task attributes
    public String vehicleNumber;
    public int mileage; // Lower mileage = higher priority (needs service sooner)

    // Constructor to create a maintenance task
    public MaintenanceTask(String vehicleNumber, int mileage) {
        this.vehicleNumber = vehicleNumber;
        this.mileage = mileage;
    }

    // Compare method for priority queue - smaller mileage gets higher priority
    public int compareTo(MaintenanceTask other) {
        return this.mileage - other.mileage; // Min-heap behavior
    }

    // Display task information in table format
    public void displayInfo() {
        String priority = "LOW";
        if (mileage <= 500) priority = "CRITICAL";
        else if (mileage <= 1000) priority = "HIGH";
        else if (mileage <= 2000) priority = "MEDIUM";
        
        System.out.printf("| %-12s | %-8d | %-8s |%n", 
            vehicleNumber, mileage, priority);
    }

    // Static method to display table header for maintenance tasks
    public static void displayTableHeader() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(10) + "+");
        System.out.printf("| %-12s | %-8s | %-8s |%n", 
            "Vehicle", "Mileage", "Priority");
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(10) + "+");
    }

    // Static method to display table footer
    public static void displayTableFooter() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(10) + "+");
    }
}
