/**
 * Driver class - represents a driver in the vehicle tracking system
 */
public class Driver {
    // Driver attributes
    String driverId;
    String name;
    int experienceYears;
    String currentLocation;

    // Constructor to create a new driver
    public Driver(String driverId, String name, int experienceYears, String currentLocation) {
        this.driverId = driverId;
        this.name = name;
        this.experienceYears = experienceYears;
        this.currentLocation = currentLocation;
    }
    
    // Display driver information
    public void displayInfo() {
        System.out.println("Driver ID: " + driverId);
        System.out.println("Name: " + name);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Location: " + currentLocation);
    }
}
