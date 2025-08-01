public class Driver {
    String driverId;
    String name;
    int experienceYears;
    String currentLocation;

    public Driver(String driverId, String name, int experienceYears, String currentLocation) {
        this.driverId = driverId;
        this.name = name;
        this.experienceYears = experienceYears;
        this.currentLocation = currentLocation;
    }
    public void displayInfo() {
        System.out.println("Driver ID: " + driverId);
        System.out.println("Name: " + name);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Location: " + currentLocation);
    }
}
