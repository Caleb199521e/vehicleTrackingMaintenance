package datastructures;

/**
 * Custom Quick Sort implementation for Vehicle Tracking System
 * Provides efficient O(n log n) average-case sorting capabilities
 */
public class QuickSort {
    
    /**
     * Quick sort vehicles by mileage (ascending order)
     * @param vehicles - array of vehicles to sort
     * @return sorted array (creates new array, doesn't modify original)
     */
    public static Vehicle[] sortByMileage(Vehicle[] vehicles) {
        if (vehicles == null || vehicles.length <= 1) {
            return vehicles;
        }
        
        // Create a copy to avoid modifying the original array
        Vehicle[] sortedVehicles = new Vehicle[vehicles.length];
        System.arraycopy(vehicles, 0, sortedVehicles, 0, vehicles.length);
        
        // Perform quick sort
        quickSortByMileage(sortedVehicles, 0, sortedVehicles.length - 1);
        
        return sortedVehicles;
    }
    
    /**
     * Quick sort vehicles by registration number (alphabetical order)
     * @param vehicles - array of vehicles to sort
     * @return sorted array (creates new array, doesn't modify original)
     */
    public static Vehicle[] sortByRegistration(Vehicle[] vehicles) {
        if (vehicles == null || vehicles.length <= 1) {
            return vehicles;
        }
        
        // Create a copy to avoid modifying the original array
        Vehicle[] sortedVehicles = new Vehicle[vehicles.length];
        System.arraycopy(vehicles, 0, sortedVehicles, 0, vehicles.length);
        
        // Perform quick sort
        quickSortByRegistration(sortedVehicles, 0, sortedVehicles.length - 1);
        
        return sortedVehicles;
    }
    
    /**
     * Quick sort with performance tracking
     * @param vehicles - array of vehicles to sort
     * @param sortType - "mileage" or "registration"
     * @return QuickSortResult with sorted array and performance metrics
     */
    public static QuickSortResult sortWithPerformanceTracking(Vehicle[] vehicles, String sortType) {
        long startTime = System.nanoTime();
        
        Vehicle[] sortedVehicles;
        
        switch (sortType.toLowerCase()) {
            case "mileage":
                sortedVehicles = sortByMileage(vehicles);
                break;
            case "registration":
                sortedVehicles = sortByRegistration(vehicles);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort type: " + sortType);
        }
        
        long endTime = System.nanoTime();
        double sortTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new QuickSortResult(sortedVehicles, sortTimeMs, sortType);
    }
    
    // ===== PRIVATE QUICK SORT IMPLEMENTATIONS =====
    
    /**
     * Recursive quick sort by mileage
     */
    private static void quickSortByMileage(Vehicle[] vehicles, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int partitionIndex = partitionByMileage(vehicles, low, high);
            
            // Recursively sort elements before and after partition
            quickSortByMileage(vehicles, low, partitionIndex - 1);
            quickSortByMileage(vehicles, partitionIndex + 1, high);
        }
    }
    
    /**
     * Partition method for mileage sorting
     */
    private static int partitionByMileage(Vehicle[] vehicles, int low, int high) {
        int pivot = vehicles[high].mileage; // Choose last element as pivot
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (vehicles[j].mileage <= pivot) {
                i++;
                swap(vehicles, i, j);
            }
        }
        
        swap(vehicles, i + 1, high); // Place pivot in correct position
        return i + 1; // Return partition index
    }
    
    /**
     * Recursive quick sort by registration number
     */
    private static void quickSortByRegistration(Vehicle[] vehicles, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int partitionIndex = partitionByRegistration(vehicles, low, high);
            
            // Recursively sort elements before and after partition
            quickSortByRegistration(vehicles, low, partitionIndex - 1);
            quickSortByRegistration(vehicles, partitionIndex + 1, high);
        }
    }
    
    /**
     * Partition method for registration number sorting
     */
    private static int partitionByRegistration(Vehicle[] vehicles, int low, int high) {
        String pivot = vehicles[high].registrationNumber; // Choose last element as pivot
        int i = low - 1; // Index of smaller element
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (vehicles[j].registrationNumber.compareToIgnoreCase(pivot) <= 0) {
                i++;
                swap(vehicles, i, j);
            }
        }
        
        swap(vehicles, i + 1, high); // Place pivot in correct position
        return i + 1; // Return partition index
    }
    
    /**
     * Helper method to swap two vehicles in array
     */
    private static void swap(Vehicle[] vehicles, int i, int j) {
        Vehicle temp = vehicles[i];
        vehicles[i] = vehicles[j];
        vehicles[j] = temp;
    }
    
    /**
     * Inner class to hold quick sort results with performance metrics
     */
    public static class QuickSortResult {
        public Vehicle[] sortedVehicles;
        public double sortTimeMs;
        public String sortType;
        
        public QuickSortResult(Vehicle[] sortedVehicles, double sortTimeMs, String sortType) {
            this.sortedVehicles = sortedVehicles;
            this.sortTimeMs = sortTimeMs;
            this.sortType = sortType;
        }
    }
}
