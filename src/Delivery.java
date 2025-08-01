public class Delivery {
    String packageId;
    String origin;
    String destination;
    String assignedVehicle;
    String assignedDriver;
    String eta; // estimated time of arrival

    public Delivery(String packageId, String origin, String destination, String assignedVehicle, String assignedDriver, String eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.assignedVehicle = assignedVehicle;
        this.assignedDriver = assignedDriver;
        this.eta = eta;
    }

    public void displayInfo() {
        System.out.println("Package ID: " + packageId);
        System.out.println("From: " + origin + " To: " + destination);
        System.out.println("Vehicle: " + assignedVehicle);
        System.out.println("Driver: " + assignedDriver);
        System.out.println("ETA: " + eta);
    }
}
