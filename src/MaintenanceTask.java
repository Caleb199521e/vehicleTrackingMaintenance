public class MaintenanceTask implements Comparable<MaintenanceTask> {
    String vehicleNumber;
    int mileage; // The smaller this value, the sooner it needs maintenance

    public MaintenanceTask(String vehicleNumber, int mileage) {
        this.vehicleNumber = vehicleNumber;
        this.mileage = mileage;
    }

    // For min-heap: smaller mileage = higher priority
    @Override
    public int compareTo(MaintenanceTask other) {
        return this.mileage - other.mileage;
    }

    public void displayInfo() {
        System.out.println("Vehicle: " + vehicleNumber);
        System.out.println("Mileage until service: " + mileage + " km");
    }
}
