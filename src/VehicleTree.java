/**
 * VehicleTree class - implements Binary Search Tree for vehicle management
 * Vehicles are organized by mileage for efficient searching and sorting
 */
public class VehicleTree {
    private VehicleNode root; // root of the BST

    // Constructor - initialize empty tree
    public VehicleTree() {
        this.root = null;
    }

    // Public method to insert vehicle by mileage
    public void insert(Vehicle vehicle) {
        root = insertRecursive(root, vehicle);
    }

    // Recursive helper method to insert a node in correct position
    private VehicleNode insertRecursive(VehicleNode current, Vehicle vehicle) {
        if (current == null) {
            return new VehicleNode(vehicle); // found the insertion spot
        }

        // Insert left if mileage is smaller, right if larger
        if (vehicle.mileage < current.vehicle.mileage) {
            current.left = insertRecursive(current.left, vehicle);
        } else {
            current.right = insertRecursive(current.right, vehicle);
        }

        return current;
    }

    // Public method to search vehicle by mileage
    public Vehicle searchByMileage(int mileage) {
        return searchRecursive(root, mileage);
    }

    // Recursive helper method to search by mileage
    private Vehicle searchRecursive(VehicleNode current, int mileage) {
        if (current == null) return null;

        if (mileage == current.vehicle.mileage) {
            return current.vehicle;
        }

        // Search left if target is smaller, right if larger
        if (mileage < current.vehicle.mileage) {
            return searchRecursive(current.left, mileage);
        } else {
            return searchRecursive(current.right, mileage);
        }
    }

    // Public method to remove vehicle by registration number
    public boolean remove(String registrationNumber) {
        Vehicle toRemove = searchByRegistration(registrationNumber);
        if (toRemove == null) {
            return false; // Vehicle not found
        }
        root = removeRecursive(root, toRemove.mileage, registrationNumber);
        return true;
    }

    // Recursive helper method to remove a node
    private VehicleNode removeRecursive(VehicleNode current, int mileage, String registrationNumber) {
        if (current == null) {
            return null;
        }

        // Navigate to the correct node
        if (mileage < current.vehicle.mileage) {
            current.left = removeRecursive(current.left, mileage, registrationNumber);
        } else if (mileage > current.vehicle.mileage) {
            current.right = removeRecursive(current.right, mileage, registrationNumber);
        } else {
            // Found the mileage, check if it's the exact vehicle
            if (current.vehicle.registrationNumber.equals(registrationNumber)) {
                // Node to delete found - handle 3 cases
                if (current.left == null) {
                    return current.right; // Case 1: No left child
                } else if (current.right == null) {
                    return current.left; // Case 2: No right child
                }

                // Case 3: Node has two children - replace with inorder successor
                current.vehicle = findMinValue(current.right);
                current.right = removeRecursive(current.right, current.vehicle.mileage, current.vehicle.registrationNumber);
            } else {
                // Same mileage but different vehicle, search both subtrees
                current.left = removeRecursive(current.left, mileage, registrationNumber);
                current.right = removeRecursive(current.right, mileage, registrationNumber);
            }
        }
        return current;
    }

    // Helper method to find minimum value in subtree (leftmost node)
    private Vehicle findMinValue(VehicleNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root.vehicle;
    }

    // Public method to search vehicle by registration number
    public Vehicle searchByRegistration(String registrationNumber) {
        return searchByRegistrationRecursive(root, registrationNumber);
    }

    // Recursive helper to search by registration (searches entire tree)
    private Vehicle searchByRegistrationRecursive(VehicleNode current, String registrationNumber) {
        if (current == null) return null;

        if (current.vehicle.registrationNumber.equals(registrationNumber)) {
            return current.vehicle;
        }

        // Search left subtree first, then right if not found
        Vehicle leftResult = searchByRegistrationRecursive(current.left, registrationNumber);
        if (leftResult != null) return leftResult;

        return searchByRegistrationRecursive(current.right, registrationNumber);
    }

    // Display all vehicles in sorted order (by mileage)
    public void displayAllVehicles() {
        if (root == null) {
            System.out.println("No vehicles in the system.");
            return;
        }
        System.out.println("\n=== All Vehicles (sorted by mileage) ===");
        inOrderTraversal(root);
    }

    // In-order traversal - visits nodes in sorted order
    private void inOrderTraversal(VehicleNode node) {
        if (node != null) {
            inOrderTraversal(node.left);    // Visit left subtree
            node.vehicle.displayInfo();      // Process current node
            System.out.println("---------------");
            inOrderTraversal(node.right);    // Visit right subtree
        }
    }

    // Check if the tree is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Get all vehicles as an array (for fuel efficiency analysis)
    public Vehicle[] getAllVehicles() {
        if (root == null) {
            return new Vehicle[0];
        }
        
        // Count nodes first, then fill array
        int count = countNodes(root);
        Vehicle[] vehicles = new Vehicle[count];
        
        // Fill array using in-order traversal
        fillArray(root, vehicles, new int[]{0}); // using array for pass-by-reference
        
        return vehicles;
    }
    
    // Helper method to count total nodes in tree
    private int countNodes(VehicleNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    
    // Helper method to fill array with vehicles in sorted order
    private void fillArray(VehicleNode node, Vehicle[] array, int[] index) {
        if (node != null) {
            fillArray(node.left, array, index);      // Fill from left subtree
            array[index[0]++] = node.vehicle;        // Add current vehicle
            fillArray(node.right, array, index);     // Fill from right subtree
        }
    }

    // Binary search for vehicle by registration number (requires sorted array)
    public Vehicle binarySearchByRegistration(String registrationNumber) {
        // Get all vehicles and sort them by registration number first
        Vehicle[] vehicles = getAllVehicles();
        if (vehicles.length == 0) return null;
        
        // Sort by registration number for binary search
        sortVehiclesByRegistration(vehicles);
        
        // Perform binary search
        return binarySearchRecursive(vehicles, registrationNumber, 0, vehicles.length - 1);
    }
    
    // Recursive binary search implementation
    private Vehicle binarySearchRecursive(Vehicle[] vehicles, String target, int left, int right) {
        if (left > right) {
            return null; // Not found
        }
        
        int mid = left + (right - left) / 2;
        int comparison = target.compareTo(vehicles[mid].registrationNumber);
        
        if (comparison == 0) {
            return vehicles[mid]; // Found!
        } else if (comparison < 0) {
            return binarySearchRecursive(vehicles, target, left, mid - 1); // Search left half
        } else {
            return binarySearchRecursive(vehicles, target, mid + 1, right); // Search right half
        }
    }
    
    // Helper method to sort vehicles by registration number (for binary search)
    private void sortVehiclesByRegistration(Vehicle[] vehicles) {
        // Simple insertion sort for registration numbers
        for (int i = 1; i < vehicles.length; i++) {
            Vehicle key = vehicles[i];
            int j = i - 1;
            
            while (j >= 0 && vehicles[j].registrationNumber.compareTo(key.registrationNumber) > 0) {
                vehicles[j + 1] = vehicles[j];
                j = j - 1;
            }
            vehicles[j + 1] = key;
        }
    }
}
