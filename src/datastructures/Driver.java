package datastructures;

/**
 * Driver class - represents a driver in the vehicle tracking system
 */
public class Driver {
    // Driver attributes
    public String driverId;
    public String name;
    public int experienceYears;
    public String currentLocation;

    // Constructor to create a new driver
    public Driver(String driverId, String name, int experienceYears, String currentLocation) {
        this.driverId = driverId;
        this.name = name;
        this.experienceYears = experienceYears;
        this.currentLocation = currentLocation;
    }
    
    // Display driver information in table format
    public void displayInfo() {
        System.out.printf("| %-8s | %-20s | %-4d | %-15s |%n", 
            driverId, name, experienceYears, currentLocation);
    }

    // Static method to display table header for drivers
    public static void displayTableHeader() {
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+" + "-".repeat(17) + "+");
        System.out.printf("| %-8s | %-20s | %-4s | %-15s |%n", 
            "ID", "Name", "Exp", "Location");
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+" + "-".repeat(17) + "+");
    }

    // Static method to display table footer
    public static void displayTableFooter() {
        System.out.println("+" + "-".repeat(10) + "+" + "-".repeat(22) + "+" + "-".repeat(6) + "+" + "-".repeat(17) + "+");
    }
}
