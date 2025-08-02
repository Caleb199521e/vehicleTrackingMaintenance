/**
 * Delivery class - represents a delivery record in the system
 * Contains package information and assignment details
 */
public class Delivery {
    // Delivery attributes
    String packageId;
    String origin;
    String destination;
    String assignedVehicle;
    String assignedDriver;
    String eta; // estimated time of arrival

    // Constructor to create a new delivery record
    public Delivery(String packageId, String origin, String destination, String assignedVehicle, String assignedDriver, String eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.assignedVehicle = assignedVehicle;
        this.assignedDriver = assignedDriver;
        this.eta = eta;
    }

    // Display delivery information
    public void displayInfo() {
        System.out.println("Package ID: " + packageId);
        System.out.println("From: " + origin + " To: " + destination);
        System.out.println("Vehicle: " + assignedVehicle);
        System.out.println("Driver: " + assignedDriver);
        System.out.println("ETA: " + eta);
    }
}
