public class VehicleTree {
    private VehicleNode root;

    public VehicleTree() {
        this.root = null;
    }

    // Insert vehicle by mileage
    public void insert(Vehicle vehicle) {
        root = insertRecursive(root, vehicle);
    }

    // Recursive function to insert a node
    private VehicleNode insertRecursive(VehicleNode current, Vehicle vehicle) {
        if (current == null) {
            return new VehicleNode(vehicle); // found the spot
        }

        if (vehicle.mileage < current.vehicle.mileage) {
            current.left = insertRecursive(current.left, vehicle);
        } else {
            current.right = insertRecursive(current.right, vehicle);
        }

        return current;
    }

    // Search vehicle by mileage
    public Vehicle searchByMileage(int mileage) {
        return searchRecursive(root, mileage);
    }

    private Vehicle searchRecursive(VehicleNode current, int mileage) {
        if (current == null) return null;

        if (mileage == current.vehicle.mileage) {
            return current.vehicle;
        }

        if (mileage < current.vehicle.mileage) {
            return searchRecursive(current.left, mileage);
        } else {
            return searchRecursive(current.right, mileage);
        }
    }

    // Remove vehicle by registration number
    public boolean remove(String registrationNumber) {
        Vehicle toRemove = searchByRegistration(registrationNumber);
        if (toRemove == null) {
            return false; // Vehicle not found
        }
        root = removeRecursive(root, toRemove.mileage, registrationNumber);
        return true;
    }

    private VehicleNode removeRecursive(VehicleNode current, int mileage, String registrationNumber) {
        if (current == null) {
            return null;
        }

        if (mileage < current.vehicle.mileage) {
            current.left = removeRecursive(current.left, mileage, registrationNumber);
        } else if (mileage > current.vehicle.mileage) {
            current.right = removeRecursive(current.right, mileage, registrationNumber);
        } else {
            // Found the mileage, but check if it's the exact vehicle (by registration)
            if (current.vehicle.registrationNumber.equals(registrationNumber)) {
                // This is the node to delete
                if (current.left == null) {
                    return current.right;
                } else if (current.right == null) {
                    return current.left;
                }

                // Node with two children: Get the inorder successor
                current.vehicle = findMinValue(current.right);
                current.right = removeRecursive(current.right, current.vehicle.mileage, current.vehicle.registrationNumber);
            } else {
                // Same mileage but different vehicle, continue searching
                current.left = removeRecursive(current.left, mileage, registrationNumber);
                current.right = removeRecursive(current.right, mileage, registrationNumber);
            }
        }
        return current;
    }

    private Vehicle findMinValue(VehicleNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.vehicle;
    }

    // Search vehicle by registration number
    public Vehicle searchByRegistration(String registrationNumber) {
        return searchByRegistrationRecursive(root, registrationNumber);
    }

    private Vehicle searchByRegistrationRecursive(VehicleNode current, String registrationNumber) {
        if (current == null) return null;

        if (current.vehicle.registrationNumber.equals(registrationNumber)) {
            return current.vehicle;
        }

        Vehicle leftResult = searchByRegistrationRecursive(current.left, registrationNumber);
        if (leftResult != null) return leftResult;

        return searchByRegistrationRecursive(current.right, registrationNumber);
    }

    // In-order traversal (sorted order)
    public void displayAllVehicles() {
        if (root == null) {
            System.out.println("No vehicles in the system.");
            return;
        }
        System.out.println("\n=== All Vehicles (sorted by mileage) ===");
        inOrderTraversal(root);
    }

    private void inOrderTraversal(VehicleNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            node.vehicle.displayInfo();
            System.out.println("---------------");
            inOrderTraversal(node.right);
        }
    }
}
