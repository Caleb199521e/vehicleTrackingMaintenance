package datastructures;

/**
 * Delivery class - represents a delivery record in the system
 * Contains package information and assignment details
 */
public class Delivery {
    // Delivery attributes
    public String packageId;
    public String origin;
    public String destination;
    public String assignedVehicle;
    public String assignedDriver;
    public String eta; // estimated time of arrival

    // Constructor to create a new delivery record
    public Delivery(String packageId, String origin, String destination, String assignedVehicle, String assignedDriver, String eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.assignedVehicle = assignedVehicle;
        this.assignedDriver = assignedDriver;
        this.eta = eta;
    }

    // Display delivery information in table format
    public void displayInfo() {
        // Truncate ETA if too long to fit in table
        String displayEta = eta.length() > 16 ? eta.substring(0, 16) : eta;
        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-8s | %-16s |%n", 
            packageId, origin, destination, assignedVehicle, assignedDriver, displayEta);
    }

    // Static method to display table header for deliveries
    public static void displayTableHeader() {
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(18) + "+");
        System.out.printf("| %-8s | %-12s | %-12s | %-12s | %-8s | %-16s |%n", 
            "Pkg ID", "Origin", "Destination", "Vehicle", "Driver", "ETA");
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(18) + "+");
    }

    // Static method to display table footer
    public static void displayTableFooter() {
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(14) + "+" + "-".repeat(10) + "+" + "-".repeat(18) + "+");
    }
}
