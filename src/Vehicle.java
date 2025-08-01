public class Vehicle {
    String registrationNumber;
    String type; // truck or van
    int mileage;
    double fuelUsage; // Litres per 100km
    String driverId;

    // Constructor to create a vehicle
    public Vehicle(String registrationNumber, String type, int mileage, double fuelUsage, String driverId) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverId = driverId;
    }

    public void displayInfo() {
        System.out.println("Reg No: " + registrationNumber);
        System.out.println("Type: " + type);
        System.out.println("Mileage: " + mileage);
        System.out.println("Fuel Usage: " + fuelUsage);
        System.out.println("Driver ID: " + driverId);
    }
}
