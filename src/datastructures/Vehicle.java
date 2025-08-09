package datastructures;

/**
 * Vehicle class - represents a vehicle (truck or van) in the system
 */
public class Vehicle {
    // Vehicle attributes
    public String registrationNumber;
    public String type; // truck or van
    public int mileage;
    public double fuelUsage; // Litres per 100km
    public String driverId;

    // Constructor to create a vehicle
    public Vehicle(String registrationNumber, String type, int mileage, double fuelUsage, String driverId) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverId = driverId;
    }

    // Getter methods for accessing private fields from Main class
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getType() {
        return type;
    }

    public int getMileage() {
        return mileage;
    }

    public double getFuelUsage() {
        return fuelUsage;
    }

    public String getDriverName() {
        return driverId; // For now, using driverId as driver name
    }

    // Display vehicle information in table format
    public void displayInfo() {
        System.out.printf("| %-12s | %-5s | %-8d | %-10.2f | %-12s |%n", 
            registrationNumber, type, mileage, fuelUsage, driverId);
    }

    // Static method to display table header for vehicles
    public static void displayTableHeader() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(7) + "+" + "-".repeat(10) + "+" + "-".repeat(12) + "+" + "-".repeat(14) + "+");
        System.out.printf("| %-12s | %-5s | %-8s | %-10s | %-12s |%n", 
            "Registration", "Type", "Mileage", "Fuel Usage", "Driver ID");
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(7) + "+" + "-".repeat(10) + "+" + "-".repeat(12) + "+" + "-".repeat(14) + "+");
    }

    // Static method to display table footer
    public static void displayTableFooter() {
        System.out.println("+" + "-".repeat(14) + "+" + "-".repeat(7) + "+" + "-".repeat(10) + "+" + "-".repeat(12) + "+" + "-".repeat(14) + "+");
    }
}
