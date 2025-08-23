package datastructures;

/**
 * Custom Merge Sort implementation for Vehicle Tracking System
 * Provides stable O(n log n) sorting capabilities for various data types
 */
public class MergeSort {
    
    /**
     * Merge sort vehicles by driver name (alphabetical order)
     * @param vehicles - array of vehicles to sort
     * @return sorted array (creates new array, doesn't modify original)
     */
    public static Vehicle[] sortByDriverName(Vehicle[] vehicles) {
        if (vehicles == null || vehicles.length <= 1) {
            return vehicles;
        }
        
        // Create a copy to avoid modifying the original array
        Vehicle[] sortedVehicles = new Vehicle[vehicles.length];
        System.arraycopy(vehicles, 0, sortedVehicles, 0, vehicles.length);
        
        // Perform merge sort
        mergeSortByDriverName(sortedVehicles, 0, sortedVehicles.length - 1);
        
        return sortedVehicles;
    }
    
    /**
     * Merge sort vehicles by mileage (ascending order)
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
        
        // Perform merge sort
        mergeSortByMileage(sortedVehicles, 0, sortedVehicles.length - 1);
        
        return sortedVehicles;
    }
    
    /**
     * Merge sort vehicles by fuel usage (ascending order - most efficient first)
     * @param vehicles - array of vehicles to sort
     * @return sorted array (creates new array, doesn't modify original)
     */
    public static Vehicle[] sortByFuelUsage(Vehicle[] vehicles) {
        if (vehicles == null || vehicles.length <= 1) {
            return vehicles;
        }
        
        // Create a copy to avoid modifying the original array
        Vehicle[] sortedVehicles = new Vehicle[vehicles.length];
        System.arraycopy(vehicles, 0, sortedVehicles, 0, vehicles.length);
        
        // Perform merge sort
        mergeSortByFuelUsage(sortedVehicles, 0, sortedVehicles.length - 1);
        
        return sortedVehicles;
    }
    
    /**
     * Merge sort with performance tracking
     * @param vehicles - array of vehicles to sort
     * @param sortType - "driverName", "mileage", or "fuelUsage"
     * @return MergeSortResult with sorted array and performance metrics
     */
    public static MergeSortResult sortWithPerformanceTracking(Vehicle[] vehicles, String sortType) {
        long startTime = System.nanoTime();
        
        Vehicle[] sortedVehicles;
        
        switch (sortType.toLowerCase()) {
            case "drivername":
                sortedVehicles = sortByDriverName(vehicles);
                break;
            case "mileage":
                sortedVehicles = sortByMileage(vehicles);
                break;
            case "fuelusage":
                sortedVehicles = sortByFuelUsage(vehicles);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort type: " + sortType);
        }
        
        long endTime = System.nanoTime();
        double sortTimeMs = (endTime - startTime) / 1_000_000.0;
        
        return new MergeSortResult(sortedVehicles, sortTimeMs, sortType);
    }
    
    // ===== PRIVATE MERGE SORT IMPLEMENTATIONS =====
    
    /**
     * Recursive merge sort by driver name
     */
    private static void mergeSortByDriverName(Vehicle[] vehicles, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            
            // Recursively sort both halves
            mergeSortByDriverName(vehicles, left, middle);
            mergeSortByDriverName(vehicles, middle + 1, right);
            
            // Merge the sorted halves
            mergeByDriverName(vehicles, left, middle, right);
        }
    }
    
    /**
     * Merge method for driver name sorting
     */
    private static void mergeByDriverName(Vehicle[] vehicles, int left, int middle, int right) {
        // Calculate sizes of sub-arrays
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        // Create temporary arrays
        Vehicle[] leftArray = new Vehicle[leftSize];
        Vehicle[] rightArray = new Vehicle[rightSize];
        
        // Copy data to temporary arrays
        System.arraycopy(vehicles, left, leftArray, 0, leftSize);
        System.arraycopy(vehicles, middle + 1, rightArray, 0, rightSize);
        
        // Merge the temporary arrays back
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].driverId.compareToIgnoreCase(rightArray[j].driverId) <= 0) {
                vehicles[k] = leftArray[i];
                i++;
            } else {
                vehicles[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftSize) {
            vehicles[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            vehicles[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * Recursive merge sort by mileage
     */
    private static void mergeSortByMileage(Vehicle[] vehicles, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            
            // Recursively sort both halves
            mergeSortByMileage(vehicles, left, middle);
            mergeSortByMileage(vehicles, middle + 1, right);
            
            // Merge the sorted halves
            mergeByMileage(vehicles, left, middle, right);
        }
    }
    
    /**
     * Merge method for mileage sorting
     */
    private static void mergeByMileage(Vehicle[] vehicles, int left, int middle, int right) {
        // Calculate sizes of sub-arrays
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        // Create temporary arrays
        Vehicle[] leftArray = new Vehicle[leftSize];
        Vehicle[] rightArray = new Vehicle[rightSize];
        
        // Copy data to temporary arrays
        System.arraycopy(vehicles, left, leftArray, 0, leftSize);
        System.arraycopy(vehicles, middle + 1, rightArray, 0, rightSize);
        
        // Merge the temporary arrays back
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].mileage <= rightArray[j].mileage) {
                vehicles[k] = leftArray[i];
                i++;
            } else {
                vehicles[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftSize) {
            vehicles[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            vehicles[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * Recursive merge sort by fuel usage
     */
    private static void mergeSortByFuelUsage(Vehicle[] vehicles, int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            
            // Recursively sort both halves
            mergeSortByFuelUsage(vehicles, left, middle);
            mergeSortByFuelUsage(vehicles, middle + 1, right);
            
            // Merge the sorted halves
            mergeByFuelUsage(vehicles, left, middle, right);
        }
    }
    
    /**
     * Merge method for fuel usage sorting
     */
    private static void mergeByFuelUsage(Vehicle[] vehicles, int left, int middle, int right) {
        // Calculate sizes of sub-arrays
        int leftSize = middle - left + 1;
        int rightSize = right - middle;
        
        // Create temporary arrays
        Vehicle[] leftArray = new Vehicle[leftSize];
        Vehicle[] rightArray = new Vehicle[rightSize];
        
        // Copy data to temporary arrays
        System.arraycopy(vehicles, left, leftArray, 0, leftSize);
        System.arraycopy(vehicles, middle + 1, rightArray, 0, rightSize);
        
        // Merge the temporary arrays back
        int i = 0, j = 0, k = left;
        
        while (i < leftSize && j < rightSize) {
            if (leftArray[i].fuelUsage <= rightArray[j].fuelUsage) {
                vehicles[k] = leftArray[i];
                i++;
            } else {
                vehicles[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftSize) {
            vehicles[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < rightSize) {
            vehicles[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    /**
     * Inner class to hold merge sort results with performance metrics
     */
    public static class MergeSortResult {
        public Vehicle[] sortedVehicles;
        public double sortTimeMs;
        public String sortType;
        
        public MergeSortResult(Vehicle[] sortedVehicles, double sortTimeMs, String sortType) {
            this.sortedVehicles = sortedVehicles;
            this.sortTimeMs = sortTimeMs;
            this.sortType = sortType;
        }
    }
}
