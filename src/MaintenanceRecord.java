public class MaintenanceRecord {
    String date;
    String partReplaced;
    double cost;

    public MaintenanceRecord(String date, String partReplaced, double cost) {
        this.date = date;
        this.partReplaced = partReplaced;
        this.cost = cost;
    }

    public void displayInfo() {
        System.out.println("Date: " + date);
        System.out.println("Part Replaced: " + partReplaced);
        System.out.println("Cost: GHS " + cost);
    }
}
