/**
 * MaintenanceTask class - represents a scheduled maintenance task
 * Implements Comparable for priority queue ordering (lower mileage = higher priority)
 */
public class MaintenanceTask implements Comparable<MaintenanceTask> {
    // Task attributes
    String vehicleNumber;
    int mileage; // Lower mileage = higher priority (needs service sooner)

    // Constructor to create a maintenance task
    public MaintenanceTask(String vehicleNumber, int mileage) {
        this.vehicleNumber = vehicleNumber;
        this.mileage = mileage;
    }

    // Compare method for priority queue - smaller mileage gets higher priority
    @Override
    public int compareTo(MaintenanceTask other) {
        return this.mileage - other.mileage; // Min-heap behavior
    }

    // Display task information
    public void displayInfo() {
        System.out.println("Vehicle: " + vehicleNumber);
        System.out.println("Mileage until service: " + mileage + " km");
    }
}
