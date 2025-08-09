package datastructures;

/**
 * VehicleNode class - represents a node in the Binary Search Tree
 * Each node contains a vehicle and pointers to left and right children
 */
public class VehicleNode {
    Vehicle vehicle; // holds the actual vehicle data
    VehicleNode left; // left child (smaller mileage)
    VehicleNode right; // right child (larger mileage)

    // Constructor to create a new node
    public VehicleNode(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.left = null;
        this.right = null;
    }
}
