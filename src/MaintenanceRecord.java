/**
 * MaintenanceRecord class - represents a completed maintenance service record
 * Stores details of maintenance work done on vehicles
 */
public class MaintenanceRecord {
    // Maintenance record attributes
    String date;
    String partReplaced;
    double cost;

    // Constructor to create a maintenance record
    public MaintenanceRecord(String date, String partReplaced, double cost) {
        this.date = date;
        this.partReplaced = partReplaced;
        this.cost = cost;
    }

    // Display maintenance record information
    public void displayInfo() {
        System.out.println("Date: " + date);
        System.out.println("Part Replaced: " + partReplaced);
        System.out.println("Cost: GHS " + cost); // Ghana Cedis currency
    }
}
