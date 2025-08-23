package datastructures;

/**
 * Custom Binary Search implementation for Vehicle Tracking System
 * Provides efficient O(log n) search capabilities for sorted arrays
 */
public class BinarySearch {
    
    /**
     * Binary search for vehicles by registration number
     * Requires the vehicle array to be sorted by registration number first
     * @param vehicles - sorted array of vehicles
     * @param targetRegistration - registration number to search for
     * @return Vehicle object if found, null if not found
     */
    public static Vehicle searchByRegistration(Vehicle[] vehicles, String targetRegistration) {
        if (vehicles == null || vehicles.length == 0 || targetRegistration == null) {
            return null;
        }
        
        // Sort the array first by registration number
        sortVehiclesByRegistration(vehicles);
        
        int left = 0;
        int right = vehicles.length - 1;
        
        while (left <= right) {
            int middle = left + (right - left) / 2;
            
            // Compare registration numbers
            int comparison = vehicles[middle].registrationNumber.compareToIgnoreCase(targetRegistration);
            
            if (comparison == 0) {
                // Found the vehicle
                return vehicles[middle];
            } else if (comparison < 0) {
                // Target is in the right half
                left = middle + 1;
            } else {
                // Target is in the left half
                right = middle - 1;
            }
        }
        
        // Vehicle not found
        return null;
    }
    
    /**
     * Binary search for vehicles by mileage
     * Requires the vehicle array to be sorted by mileage first
     * @param vehicles - sorted array of vehicles
     * @param targetMileage - mileage to search for
     * @return Vehicle object if found, null if not found
     */
    public static Vehicle searchByMileage(Vehicle[] vehicles, int targetMileage) {
        if (vehicles == null || vehicles.length == 0) {
            return null;
        }
        
        // Sort the array first by mileage
        sortVehiclesByMileage(vehicles);
        
        int left = 0;
        int right = vehicles.length - 1;
        
        while (left <= right) {
            int middle = left + (right - left) / 2;
            
            if (vehicles[middle].mileage == targetMileage) {
                // Found the vehicle
                return vehicles[middle];
            } else if (vehicles[middle].mileage < targetMileage) {
                // Target is in the right half
                left = middle + 1;
            } else {
                // Target is in the left half
                right = middle - 1;
            }
        }
        
        // Vehicle not found
        return null;
    }
    
    /**
     * Binary search for a specific index in an integer array
     * General purpose binary search for integers
     * @param array - sorted array of integers
     * @param target - value to search for
     * @return index of the target if found, -1 if not found
     */
    public static int searchInteger(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = array.length - 1;
        
        while (left <= right) {
            int middle = left + (right - left) / 2;
            
            if (array[middle] == target) {
                return middle; // Found at index middle
            } else if (array[middle] < target) {
                left = middle + 1; // Search right half
            } else {
                right = middle - 1; // Search left half
            }
        }
        
        return -1; // Not found
    }
    
    /**
     * Binary search with performance tracking
     * Returns search result along with performance metrics
     * @param vehicles - array of vehicles to search
     * @param targetRegistration - registration to search for
     * @return BinarySearchResult with vehicle and performance data
     */
    public static BinarySearchResult searchWithPerformanceTracking(Vehicle[] vehicles, String targetRegistration) {
        long startTime = System.nanoTime();
        int comparisons = 0;
        
        if (vehicles == null || vehicles.length == 0 || targetRegistration == null) {
            long endTime = System.nanoTime();
            return new BinarySearchResult(null, (endTime - startTime) / 1_000_000.0, comparisons);
        }
        
        // Sort the array first
        sortVehiclesByRegistration(vehicles);
        
        int left = 0;
        int right = vehicles.length - 1;
        Vehicle foundVehicle = null;
        
        while (left <= right) {
            comparisons++;
            int middle = left + (right - left) / 2;
            
            int comparison = vehicles[middle].registrationNumber.compareToIgnoreCase(targetRegistration);
            
            if (comparison == 0) {
                foundVehicle = vehicles[middle];
                break;
            } else if (comparison < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        
        long endTime = System.nanoTime();
        double searchTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new BinarySearchResult(foundVehicle, searchTimeMs, comparisons);
    }
    
    // Helper method to sort vehicles by registration number (using simple bubble sort for demonstration)
    private static void sortVehiclesByRegistration(Vehicle[] vehicles) {
        int n = vehicles.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (vehicles[j].registrationNumber.compareToIgnoreCase(vehicles[j + 1].registrationNumber) > 0) {
                    // Swap vehicles
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }
    }
    
    // Helper method to sort vehicles by mileage
    private static void sortVehiclesByMileage(Vehicle[] vehicles) {
        int n = vehicles.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (vehicles[j].mileage > vehicles[j + 1].mileage) {
                    // Swap vehicles
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }
    }
    
    /**
     * Inner class to hold binary search results with performance metrics
     */
    public static class BinarySearchResult {
        public Vehicle vehicle;
        public double searchTimeMs;
        public int comparisons;
        
        public BinarySearchResult(Vehicle vehicle, double searchTimeMs, int comparisons) {
            this.vehicle = vehicle;
            this.searchTimeMs = searchTimeMs;
            this.comparisons = comparisons;
        }
        
        public boolean isFound() {
            return vehicle != null;
        }
    }
}
