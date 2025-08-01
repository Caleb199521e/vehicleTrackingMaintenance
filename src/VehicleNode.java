public class VehicleNode {
    Vehicle vehicle; // holds the actual vehicle
    VehicleNode left; // left child
    VehicleNode right; // right child

    public VehicleNode(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.left = null;
        this.right = null;
    }
}
