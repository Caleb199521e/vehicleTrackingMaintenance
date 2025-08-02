import java.util.Scanner;
import java.io.*;

/**
 * Main class - Vehicle Tracking & Maintenance System
 * Central application that manages vehicles, drivers, deliveries, and maintenance
 * Uses various data structures: BST for vehicles, queues for drivers/deliveries, priority queue for maintenance
 */
public class Main {
    // Core data structures for the system
    private static VehicleTree vehicleTree = new VehicleTree();           // BST for vehicle management
    private static DriverQueue driverQueue = new DriverQueue();           // Queue for driver assignments
    private static DeliveryQueue deliveryQueue = new DeliveryQueue();     // Queue for delivery management
    private static MaintenanceScheduler maintenanceScheduler = new MaintenanceScheduler(); // Priority queue for maintenance
    private static Scanner scanner = new Scanner(System.in);              // Input scanner

    // Main method - entry point of the application
    public static void main(String[] args) {
        displayWelcomeMessage(); // Show welcome screen
        showMenu();              // Start interactive menu system
    }

    // Display welcome message and system overview
    private static void displayWelcomeMessage() {
        System.out.println("=".repeat(60));
        System.out.println("    WELCOME TO VEHICLE TRACKING & MAINTENANCE SYSTEM");
        System.out.println("=".repeat(60));
        System.out.println("This system allows you to:");
        System.out.println("• Manage vehicles (trucks and vans)");
        System.out.println("• Track drivers and their assignments");
        System.out.println("• Monitor maintenance records");
        System.out.println("• Handle delivery assignments");
        System.out.println("=".repeat(60));
        System.out.println("Start by adding vehicles and drivers to get started!");
        System.out.println();
    }

    // Main menu system - displays options and handles user selections
    private static void showMenu() {
        while (true) { // Continuous loop until user exits
            // Display main menu with all available options
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           VEHICLE TRACKING SYSTEM - MAIN MENU");
            System.out.println("=".repeat(60));
            System.out.println("VEHICLE MANAGEMENT:");
            System.out.println("  1. Add Vehicle (Truck/Van)");
            System.out.println("  2. Remove Vehicle");
            System.out.println("  3. Search Vehicle by Registration");
            System.out.println("  4. Search Vehicle by Mileage");
            System.out.println("  5. Display All Vehicles");
            System.out.println();
            System.out.println("DRIVER MANAGEMENT:");
            System.out.println("  6. Add Driver");
            System.out.println("  7. Display Available Drivers");
            System.out.println("  8. Assign Next Available Driver");
            System.out.println();
            System.out.println("DELIVERY OPERATIONS:");
            System.out.println("  9. Create Delivery Record");
            System.out.println(" 10. View Pending Deliveries");
            System.out.println(" 11. Process Next Delivery");
            System.out.println();
            System.out.println("MAINTENANCE MANAGEMENT:");
            System.out.println(" 12. Create Maintenance Record");
            System.out.println(" 13. Schedule Maintenance Task");
            System.out.println(" 14. View Scheduled Maintenance");
            System.out.println(" 15. Process Next Maintenance Task");
            System.out.println(" 16. Check Vehicle Maintenance Due");
            System.out.println();
            System.out.println("FUEL EFFICIENCY REPORTS:");
            System.out.println(" 17. Generate Fuel Efficiency Report");
            System.out.println(" 18. View Fuel Outliers");
            System.out.println(" 19. Organize Vehicles by Fuel Performance");
            System.out.println(" 20. Organize Vehicles by Fuel Efficiency");
            System.out.println();
            System.out.println("SEARCH & SORT FEATURES:");
            System.out.println(" 21. Binary Search by Registration Number");
            System.out.println(" 22. Organize Vehicles by Mileage");
            System.out.println(" 23. Organize Vehicles by Driver Name");
            System.out.println();
            System.out.println("FILE STORAGE:");
            System.out.println(" 24. Save All Data to Files");
            System.out.println(" 25. Load All Data from Files");
            System.out.println(" 26. Export System Report");
            System.out.println(" 27. Exit");
            System.out.println("=".repeat(60));
            System.out.print("Please select an option (1-27): ");

            int choice = getIntInput(); // Get user input with validation
            
            // Route to appropriate method based on user choice
            switch (choice) {
                case 1:
                    addVehicle(); // BST insertion
                    break;
                case 2:
                    removeVehicle(); // BST deletion
                    break;
                case 3:
                    searchByRegistration(); // BST search by key
                    break;
                case 4:
                    searchByMileage(); // BST search by value
                    break;
                case 5:
                    displayAllVehicles(); // BST in-order traversal
                    break;
                case 6:
                    addDriver(); // Queue enqueue operation
                    break;
                case 7:
                    displayAvailableDrivers(); // Queue display
                    break;
                case 8:
                    assignDriver(); // Queue dequeue operation
                    break;
                case 9:
                    createDeliveryRecord(); // Delivery queue enqueue
                    break;
                case 10:
                    viewPendingDeliveries(); // Delivery queue display
                    break;
                case 11:
                    processNextDelivery(); // Delivery queue dequeue
                    break;
                case 12:
                    createMaintenanceRecord(); // Create maintenance record
                    break;
                case 13:
                    scheduleMaintenanceTask(); // Priority queue insertion
                    break;
                case 14:
                    viewScheduledMaintenance(); // Priority queue display
                    break;
                case 15:
                    processNextMaintenanceTask(); // Priority queue extraction
                    break;
                case 16:
                    checkVehicleMaintenanceDue(); // Priority analysis
                    break;
                case 17:
                    generateFuelEfficiencyReport(); // Analytics - averages and statistics
                    break;
                case 18:
                    viewFuelOutliers(); // Analytics - outlier detection
                    break;
                case 19:
                    filterVehiclesByFuelPerformance(); // Analytics - filtering
                    break;
                case 20:
                    sortVehiclesByFuelEfficiency(); // Analytics - sorting algorithms
                    break;
                case 21:
                    binarySearchByRegistration(); // Binary search implementation
                    break;
                case 22:
                    quickSortVehiclesByMileage(); // Quick sort implementation
                    break;
                case 23:
                    mergeSortVehiclesByDriverName(); // Merge sort implementation
                    break;
                case 24:
                    saveAllDataToFiles(); // Save all data to text files
                    break;
                case 25:
                    loadAllDataFromFiles(); // Load all data from text files
                    break;
                case 26:
                    exportSystemReport(); // Export comprehensive system report
                    break;
                case 27:
                    System.out.println("Thank you for using Vehicle Tracking System!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ===== VEHICLE MANAGEMENT METHODS (Binary Search Tree Operations) =====
    
    // Add new vehicle to BST - demonstrates BST insertion with validation
    private static void addVehicle() {
        System.out.println("\n=== Add New Vehicle ===");
        
        System.out.print("Enter Registration Number (e.g., GT1234-22): ");
        String regNumber = scanner.nextLine().trim();
        
        // Check for duplicates using BST search - O(log n) average case
        if (vehicleTree.searchByRegistration(regNumber) != null) {
            System.out.println("Error: Vehicle with registration " + regNumber + " already exists!");
            return;
        }
        
        System.out.print("Enter Vehicle Type (Truck/Van): ");
        String type = scanner.nextLine().trim();
        
        // Input validation - ensure data integrity
        if (!type.equalsIgnoreCase("Truck") && !type.equalsIgnoreCase("Van")) {
            System.out.println("Error: Vehicle type must be either 'Truck' or 'Van'");
            return;
        }
        
        System.out.print("Enter Current Mileage: ");
        int mileage = getIntInput();
        
        if (mileage < 0) {
            System.out.println("Error: Mileage cannot be negative!");
            return;
        }
        
        System.out.print("Enter Fuel Usage (Litres per 100km): ");
        double fuelUsage = getDoubleInput();
        
        if (fuelUsage <= 0) {
            System.out.println("Error: Fuel usage must be greater than 0!");
            return;
        }
        
        System.out.print("Enter Assigned Driver ID (or press Enter if unassigned): ");
        String driverId = scanner.nextLine().trim();
        if (driverId.isEmpty()) {
            driverId = "UNASSIGNED";
        }
        
        Vehicle newVehicle = new Vehicle(regNumber, type, mileage, fuelUsage, driverId);
        vehicleTree.insert(newVehicle);
        
        System.out.println("\n✓ Vehicle added successfully!");
        newVehicle.displayInfo();
    }

    private static void removeVehicle() {
        System.out.println("\n=== Remove Vehicle ===");
        System.out.print("Enter Registration Number to remove: ");
        String regNumber = scanner.nextLine();
        
        Vehicle vehicle = vehicleTree.searchByRegistration(regNumber);
        if (vehicle == null) {
            System.out.println("Error: Vehicle with registration " + regNumber + " not found!");
            return;
        }
        
        System.out.println("\nVehicle found:");
        vehicle.displayInfo();
        
        System.out.print("\nAre you sure you want to remove this vehicle? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
            if (vehicleTree.remove(regNumber)) {
                System.out.println("✓ Vehicle removed successfully!");
            } else {
                System.out.println("Error: Failed to remove vehicle.");
            }
        } else {
            System.out.println("Vehicle removal cancelled.");
        }
    }

    private static void searchByRegistration() {
        System.out.println("\n=== Search by Registration ===");
        System.out.print("Enter Registration Number: ");
        String regNumber = scanner.nextLine();
        
        Vehicle found = vehicleTree.searchByRegistration(regNumber);
        if (found != null) {
            System.out.println("\n✓ Vehicle found:");
            found.displayInfo();
        } else {
            System.out.println("Vehicle with registration " + regNumber + " not found.");
        }
    }

    private static void searchByMileage() {
        System.out.println("\n=== Search by Mileage ===");
        System.out.print("Enter Mileage: ");
        int targetMileage = getIntInput();
        
        Vehicle found = vehicleTree.searchByMileage(targetMileage);
        if (found != null) {
            System.out.println("\n✓ Vehicle found with mileage " + targetMileage + ":");
            found.displayInfo();
        } else {
            System.out.println("Vehicle with mileage " + targetMileage + " not found.");
        }
    }

    private static void displayAllVehicles() {
        vehicleTree.displayAllVehicles();
    }

    private static void addDriver() {
        System.out.println("\n=== Add New Driver ===");
        
        System.out.print("Enter Driver ID (e.g., D01): ");
        String driverId = scanner.nextLine();
        
        System.out.print("Enter Driver Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Years of Experience: ");
        int experience = getIntInput();
        
        System.out.print("Enter Location/Base: ");
        String location = scanner.nextLine();
        
        Driver newDriver = new Driver(driverId, name, experience, location);
        driverQueue.enqueue(newDriver);
        
        System.out.println("\n✓ Driver added successfully!");
        newDriver.displayInfo();
    }

    private static void displayAvailableDrivers() {
        System.out.println("\n=== Available Drivers ===");
        driverQueue.displayAvailableDrivers();
    }

    private static void assignDriver() {
        System.out.println("\n=== Assign Next Available Driver ===");
        Driver assignedDriver = driverQueue.dequeue();
        if (assignedDriver != null) {
            System.out.println("✓ Driver assigned:");
            assignedDriver.displayInfo();
            System.out.println("Note: This driver has been removed from the available queue.");
        }
    }

    private static void createDeliveryRecord() {
        System.out.println("\n=== Create Delivery Record ===");
        
        System.out.print("Enter Package ID: ");
        String packageId = scanner.nextLine();
        
        System.out.print("Enter Origin Location: ");
        String origin = scanner.nextLine();
        
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter Vehicle Registration Number: ");
        String vehicleReg = scanner.nextLine();
        
        // Verify vehicle exists
        Vehicle vehicle = vehicleTree.searchByRegistration(vehicleReg);
        if (vehicle == null) {
            System.out.println("Error: Vehicle with registration " + vehicleReg + " not found!");
            System.out.println("Please add the vehicle first or use an existing vehicle.");
            return;
        }
        
        System.out.print("Enter Driver ID: ");
        String driverId = scanner.nextLine();
        
        System.out.print("Enter Delivery Time: ");
        String deliveryTime = scanner.nextLine();
        
        Delivery delivery = new Delivery(packageId, origin, destination, vehicleReg, driverId, deliveryTime);
        
        // Add delivery to queue
        deliveryQueue.enqueue(delivery);
        
        System.out.println("\n✓ Delivery record created and added to queue successfully!");
        delivery.displayInfo();
        System.out.println("Status: PENDING - Added to delivery queue");
    }

    private static void viewPendingDeliveries() {
        System.out.println("\n=== Pending Deliveries ===");
        deliveryQueue.displayPendingDeliveries();
        
        if (!deliveryQueue.isEmpty()) {
            System.out.println("\nNote: Deliveries are processed in First-In-First-Out (FIFO) order.");
        }
    }

    private static void processNextDelivery() {
        System.out.println("\n=== Process Next Delivery ===");
        
        Delivery nextDelivery = deliveryQueue.dequeue();
        if (nextDelivery != null) {
            System.out.println("✓ Processing delivery:");
            nextDelivery.displayInfo();
            System.out.println("Status: COMPLETED - Delivery has been processed and removed from queue");
            
            // Optional: Update vehicle mileage after delivery
            updateVehicleMileageAfterDelivery(nextDelivery);
        }
    }

    private static void updateVehicleMileageAfterDelivery(Delivery delivery) {
        System.out.print("\nWould you like to update vehicle mileage after this delivery? (y/n): ");
        String updateChoice = scanner.nextLine();
        
        if (updateChoice.equalsIgnoreCase("y") || updateChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter additional mileage for this delivery: ");
            int additionalMileage = getIntInput();
            
            if (additionalMileage > 0) {
                // Find the vehicle and update its mileage
                Vehicle vehicle = vehicleTree.searchByRegistration(delivery.assignedVehicle);
                if (vehicle != null) {
                    int currentMileage = vehicle.mileage;
                    vehicle.mileage = currentMileage + additionalMileage;
                    System.out.println("✓ Vehicle mileage updated:");
                    System.out.println("  Vehicle: " + delivery.assignedVehicle);
                    System.out.println("  Previous mileage: " + currentMileage + " km");
                    System.out.println("  Additional mileage: " + additionalMileage + " km");
                    System.out.println("  New mileage: " + vehicle.mileage + " km");
                } else {
                    System.out.println("Error: Vehicle not found for mileage update.");
                }
            }
        }
    }

    private static void createMaintenanceRecord() {
        System.out.println("\n=== Create Maintenance Record ===");
        
        System.out.print("Enter Maintenance Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Service Type (e.g., Oil Change, Brake Pads, etc.): ");
        String serviceType = scanner.nextLine();
        
        System.out.print("Enter Cost: GH₵");
        double cost = getDoubleInput();
        
        MaintenanceRecord maintenance = new MaintenanceRecord(date, serviceType, cost);
        
        System.out.println("\n✓ Maintenance record created successfully!");
        maintenance.displayInfo();
    }

    private static void scheduleMaintenanceTask() {
        System.out.println("\n=== Schedule Maintenance Task ===");
        
        System.out.print("Enter Vehicle Registration Number: ");
        String vehicleReg = scanner.nextLine().trim();
        
        // Verify vehicle exists
        Vehicle vehicle = vehicleTree.searchByRegistration(vehicleReg);
        if (vehicle == null) {
            System.out.println("Error: Vehicle with registration " + vehicleReg + " not found!");
            return;
        }
        
        System.out.print("Enter Mileage Until Service (lower = higher priority): ");
        int mileage = getIntInput();
        
        if (mileage < 0) {
            System.out.println("Error: Mileage cannot be negative!");
            return;
        }
        
        MaintenanceTask task = new MaintenanceTask(vehicleReg, mileage);
        maintenanceScheduler.addTask(task);
        
        System.out.println("\n✓ Maintenance task scheduled successfully!");
        task.displayInfo();
        
        if (mileage <= 1000) {
            System.out.println("⚠️  WARNING: This vehicle needs urgent maintenance!");
        } else if (mileage <= 2000) {
            System.out.println("🔔 NOTICE: This vehicle will need maintenance soon.");
        }
    }

    private static void viewScheduledMaintenance() {
        System.out.println("\n=== Scheduled Maintenance Tasks ===");
        maintenanceScheduler.showAllTasks();
    }

    private static void processNextMaintenanceTask() {
        System.out.println("\n=== Process Next Maintenance Task ===");
        maintenanceScheduler.processNextTask();
        
        if (!maintenanceScheduler.isEmpty()) {
            System.out.print("\nWould you like to create a maintenance record for this service? (y/n): ");
            String createRecord = scanner.nextLine();
            
            if (createRecord.equalsIgnoreCase("y") || createRecord.equalsIgnoreCase("yes")) {
                createMaintenanceRecordAfterService();
            }
        }
    }

    private static void checkVehicleMaintenanceDue() {
        System.out.println("\n=== Vehicle Maintenance Due Check ===");
        
        if (maintenanceScheduler.isEmpty()) {
            System.out.println("No maintenance tasks scheduled.");
            return;
        }
        
        System.out.println("Current maintenance priorities (lower mileage = higher priority):");
        System.out.println("=".repeat(60));
        maintenanceScheduler.showAllTasks();
        
        System.out.println("\n🚨 Priority Levels:");
        System.out.println("   0-500 km:    CRITICAL - Immediate service required");
        System.out.println("   501-1000 km: HIGH - Service needed soon");
        System.out.println("   1001-2000 km: MEDIUM - Schedule service");
        System.out.println("   2000+ km:    LOW - Monitor for future service");
    }

    private static void createMaintenanceRecordAfterService() {
        System.out.println("\n=== Create Maintenance Record After Service ===");
        
        System.out.print("Enter Service Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter Service Type (e.g., Oil Change, Brake Service, etc.): ");
        String serviceType = scanner.nextLine();
        
        System.out.print("Enter Cost: GH₵");
        double cost = getDoubleInput();
        
        MaintenanceRecord maintenance = new MaintenanceRecord(date, serviceType, cost);
        
        System.out.println("\n✓ Maintenance record created successfully!");
        maintenance.displayInfo();
    }

    // Input validation method for integers - prevents crashes from invalid input
    private static int getIntInput() {
        while (true) { // Keep asking until valid input
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input); // Try to parse as integer
            } catch (NumberFormatException e) {
                // Handle invalid input gracefully
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    // Input validation method for decimal numbers - prevents crashes from invalid input
    private static double getDoubleInput() {
        while (true) { // Keep asking until valid input
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input); // Try to parse as double
            } catch (NumberFormatException e) {
                // Handle invalid input gracefully
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    // ===== FUEL EFFICIENCY ANALYTICS METHODS =====
    
    // Generate comprehensive fuel efficiency report with statistics
    private static void generateFuelEfficiencyReport() {
        System.out.println("\n===== FUEL EFFICIENCY REPORT =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        if (vehicles.length == 0) {
            System.out.println("No vehicles found.");
            return;
        }

        double totalFuelUsage = 0;
        int count = 0;

        System.out.println("\nVehicle Fuel Efficiency Details:");
        System.out.println("Vehicle ID | Type  | Fuel Usage (L/100km) | Status");
        System.out.println("-----------|-------|---------------------|--------");

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-10s | %-5s | %-19.2f | %s%n", 
                vehicle.registrationNumber, 
                vehicle.type, 
                vehicle.fuelUsage,
                vehicle.fuelUsage > 15 ? "High Usage" : "Normal");
            totalFuelUsage += vehicle.fuelUsage;
            count++;
        }

        double averageFuelUsage = totalFuelUsage / count;
        System.out.printf("\nTotal Vehicles: %d%n", count);
        System.out.printf("Average Fuel Usage: %.2f L/100km%n", averageFuelUsage);
        
        // Find best and worst performers
        Vehicle mostEfficient = vehicles[0];
        Vehicle leastEfficient = vehicles[0];
        
        for (Vehicle vehicle : vehicles) {
            if (vehicle.fuelUsage < mostEfficient.fuelUsage) {
                mostEfficient = vehicle;
            }
            if (vehicle.fuelUsage > leastEfficient.fuelUsage) {
                leastEfficient = vehicle;
            }
        }
        
        System.out.printf("\nMost Efficient: %s (%.2f L/100km)%n", 
            mostEfficient.registrationNumber, mostEfficient.fuelUsage);
        System.out.printf("Least Efficient: %s (%.2f L/100km)%n", 
            leastEfficient.registrationNumber, leastEfficient.fuelUsage);
    }

    private static void viewFuelOutliers() {
        System.out.println("\n===== FUEL EFFICIENCY OUTLIERS =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        if (vehicles.length == 0) {
            System.out.println("No vehicles found.");
            return;
        }

        // Calculate average
        double totalFuelUsage = 0;
        for (Vehicle vehicle : vehicles) {
            totalFuelUsage += vehicle.fuelUsage;
        }
        double averageFuelUsage = totalFuelUsage / vehicles.length;

        // Define outlier threshold (vehicles using 50% more than average)
        double outlierThreshold = averageFuelUsage * 1.5;

        System.out.printf("Average Fuel Usage: %.2f L/100km%n", averageFuelUsage);
        System.out.printf("Outlier Threshold: %.2f L/100km%n", outlierThreshold);
        System.out.println("\nVehicles with High Fuel Usage (Outliers):");
        System.out.println("Vehicle ID | Type  | Fuel Usage | Excess Usage");
        System.out.println("-----------|-------|------------|-------------");

        boolean foundOutliers = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.fuelUsage > outlierThreshold) {
                double excess = vehicle.fuelUsage - averageFuelUsage;
                System.out.printf("%-10s | %-5s | %-10.2f | +%.2f L/100km%n", 
                    vehicle.registrationNumber, 
                    vehicle.type, 
                    vehicle.fuelUsage,
                    excess);
                foundOutliers = true;
            }
        }

        if (!foundOutliers) {
            System.out.println("No fuel efficiency outliers found.");
        }
    }

    private static void filterVehiclesByFuelPerformance() {
        System.out.println("\n===== FILTER VEHICLES BY FUEL PERFORMANCE =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        System.out.println("Filter Options:");
        System.out.println("1. High Efficiency (< 8 L/100km)");
        System.out.println("2. Medium Efficiency (8-12 L/100km)");
        System.out.println("3. Low Efficiency (> 12 L/100km)");
        System.out.println("4. Custom Range");
        System.out.print("Choose filter option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        boolean foundVehicles = false;

        System.out.println("\nFiltered Results:");
        System.out.println("Vehicle ID | Type  | Fuel Usage | Efficiency");
        System.out.println("-----------|-------|------------|----------");

        switch (choice) {
            case 1: // High Efficiency
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.fuelUsage < 8) {
                        System.out.printf("%-10s | %-5s | %-10.2f | High%n", 
                            vehicle.registrationNumber, vehicle.type, vehicle.fuelUsage);
                        foundVehicles = true;
                    }
                }
                break;
            case 2: // Medium Efficiency
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.fuelUsage >= 8 && vehicle.fuelUsage <= 12) {
                        System.out.printf("%-10s | %-5s | %-10.2f | Medium%n", 
                            vehicle.registrationNumber, vehicle.type, vehicle.fuelUsage);
                        foundVehicles = true;
                    }
                }
                break;
            case 3: // Low Efficiency
                for (Vehicle vehicle : vehicles) {
                    if (vehicle.fuelUsage > 12) {
                        System.out.printf("%-10s | %-5s | %-10.2f | Low%n", 
                            vehicle.registrationNumber, vehicle.type, vehicle.fuelUsage);
                        foundVehicles = true;
                    }
                }
                break;
            case 4: // Custom Range
                System.out.print("Enter minimum fuel usage (L/100km): ");
                double minUsage = scanner.nextDouble();
                System.out.print("Enter maximum fuel usage (L/100km): ");
                double maxUsage = scanner.nextDouble();
                scanner.nextLine(); // consume newline

                for (Vehicle vehicle : vehicles) {
                    if (vehicle.fuelUsage >= minUsage && vehicle.fuelUsage <= maxUsage) {
                        System.out.printf("%-10s | %-5s | %-10.2f | Custom%n", 
                            vehicle.registrationNumber, vehicle.type, vehicle.fuelUsage);
                        foundVehicles = true;
                    }
                }
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        if (!foundVehicles) {
            System.out.println("No vehicles found matching the filter criteria.");
        }
    }

    private static void sortVehiclesByFuelEfficiency() {
        System.out.println("\n===== SORT VEHICLES BY FUEL EFFICIENCY =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        if (vehicles.length == 0) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("Sort Options:");
        System.out.println("1. Most Efficient First (Low to High fuel usage)");
        System.out.println("2. Least Efficient First (High to Low fuel usage)");
        System.out.print("Choose sort option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        // Simple bubble sort for demonstration
        for (int i = 0; i < vehicles.length - 1; i++) {
            for (int j = 0; j < vehicles.length - 1 - i; j++) {
                boolean shouldSwap = false;
                
                if (choice == 1) { // Most efficient first (ascending)
                    shouldSwap = vehicles[j].fuelUsage > vehicles[j + 1].fuelUsage;
                } else if (choice == 2) { // Least efficient first (descending)
                    shouldSwap = vehicles[j].fuelUsage < vehicles[j + 1].fuelUsage;
                }
                
                if (shouldSwap) {
                    Vehicle temp = vehicles[j];
                    vehicles[j] = vehicles[j + 1];
                    vehicles[j + 1] = temp;
                }
            }
        }

        System.out.println("\nSorted Vehicle List:");
        System.out.println("Rank | Vehicle ID | Type  | Fuel Usage | Efficiency Rating");
        System.out.println("-----|-----------|-------|------------|------------------");

        for (int i = 0; i < vehicles.length; i++) {
            String efficiency;
            if (vehicles[i].fuelUsage < 8) {
                efficiency = "Excellent";
            } else if (vehicles[i].fuelUsage <= 12) {
                efficiency = "Good";
            } else if (vehicles[i].fuelUsage <= 15) {
                efficiency = "Fair";
            } else {
                efficiency = "Poor";
            }

            System.out.printf("%-4d | %-10s | %-5s | %-10.2f | %s%n", 
                i + 1, 
                vehicles[i].registrationNumber, 
                vehicles[i].type, 
                vehicles[i].fuelUsage,
                efficiency);
        }
    }

    // ===== SEARCH & SORT FEATURES =====
    
    // Binary search for vehicle by registration number
    private static void binarySearchByRegistration() {
        System.out.println("\n===== BINARY SEARCH BY REGISTRATION =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        System.out.print("Enter Registration Number to search: ");
        String regNumber = scanner.nextLine().trim();

        // Record start time for performance analysis
        long startTime = System.nanoTime();
        
        // Perform binary search
        Vehicle found = vehicleTree.binarySearchByRegistration(regNumber);
        
        long endTime = System.nanoTime();
        double searchTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        if (found != null) {
            System.out.println("\n✓ Vehicle found using Binary Search:");
            System.out.println("Search completed in: " + String.format("%.3f", searchTime) + " ms");
            System.out.println("=".repeat(40));
            found.displayInfo();
        } else {
            System.out.println("✗ Vehicle with registration '" + regNumber + "' not found.");
            System.out.println("Search completed in: " + String.format("%.3f", searchTime) + " ms");
        }
    }

    // Quick sort vehicles by mileage
    private static void quickSortVehiclesByMileage() {
        System.out.println("\n===== QUICK SORT BY MILEAGE =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        if (vehicles.length == 0) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("Vehicles before sorting:");
        displayVehicleArray(vehicles, "Current Order");

        // Record start time for performance analysis
        long startTime = System.nanoTime();
        
        // Perform quick sort
        quickSortByMileage(vehicles, 0, vehicles.length - 1);
        
        long endTime = System.nanoTime();
        double sortTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("\n✓ Quick Sort completed in: " + String.format("%.3f", sortTime) + " ms");
        System.out.println("Vehicles sorted by mileage (ascending):");
        displayVehicleArray(vehicles, "Sorted by Mileage");
    }

    // Quick sort recursive implementation
    private static void quickSortByMileage(Vehicle[] vehicles, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int partitionIndex = partitionByMileage(vehicles, low, high);
            
            // Recursively sort elements before and after partition
            quickSortByMileage(vehicles, low, partitionIndex - 1);  // Sort left sub-array
            quickSortByMileage(vehicles, partitionIndex + 1, high); // Sort right sub-array
        }
    }

    // Partition method for quick sort (using last element as pivot)
    private static int partitionByMileage(Vehicle[] vehicles, int low, int high) {
        int pivot = vehicles[high].mileage; // Choose last element as pivot
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (vehicles[j].mileage <= pivot) {
                i++;
                swapVehicles(vehicles, i, j);
            }
        }
        swapVehicles(vehicles, i + 1, high); // Place pivot in correct position
        return i + 1; // Return partition index
    }

    // Merge sort vehicles by driver name
    private static void mergeSortVehiclesByDriverName() {
        System.out.println("\n===== MERGE SORT BY DRIVER NAME =====");
        if (vehicleTree.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }

        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        if (vehicles.length == 0) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("Vehicles before sorting:");
        displayVehicleArray(vehicles, "Current Order");

        // Record start time for performance analysis
        long startTime = System.nanoTime();
        
        // Perform merge sort
        mergeSortByDriverName(vehicles, 0, vehicles.length - 1);
        
        long endTime = System.nanoTime();
        double sortTime = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds

        System.out.println("\n✓ Merge Sort completed in: " + String.format("%.3f", sortTime) + " ms");
        System.out.println("Vehicles sorted by driver name (alphabetical):");
        displayVehicleArray(vehicles, "Sorted by Driver Name");
    }

    // Merge sort recursive implementation
    private static void mergeSortByDriverName(Vehicle[] vehicles, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int middle = left + (right - left) / 2;

            // Recursively sort first and second halves
            mergeSortByDriverName(vehicles, left, middle);      // Sort left half
            mergeSortByDriverName(vehicles, middle + 1, right); // Sort right half

            // Merge the sorted halves
            mergeByDriverName(vehicles, left, middle, right);
        }
    }

    // Merge method for merge sort
    private static void mergeByDriverName(Vehicle[] vehicles, int left, int middle, int right) {
        // Calculate sizes of two sub-arrays to be merged
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary arrays
        Vehicle[] leftArray = new Vehicle[leftSize];
        Vehicle[] rightArray = new Vehicle[rightSize];

        // Copy data to temporary arrays
        for (int i = 0; i < leftSize; i++)
            leftArray[i] = vehicles[left + i];
        for (int j = 0; j < rightSize; j++)
            rightArray[j] = vehicles[middle + 1 + j];

        // Merge the temporary arrays back into vehicles[left..right]
        int i = 0, j = 0, k = left; // Initial indexes of left, right, and merged arrays

        while (i < leftSize && j < rightSize) {
            // Compare driver names lexicographically (alphabetical order)
            if (leftArray[i].driverId.compareTo(rightArray[j].driverId) <= 0) {
                vehicles[k] = leftArray[i];
                i++;
            } else {
                vehicles[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftArray[], if any
        while (i < leftSize) {
            vehicles[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of rightArray[], if any
        while (j < rightSize) {
            vehicles[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // Helper method to swap two vehicles in array
    private static void swapVehicles(Vehicle[] vehicles, int i, int j) {
        Vehicle temp = vehicles[i];
        vehicles[i] = vehicles[j];
        vehicles[j] = temp;
    }

    // Helper method to display vehicle array in formatted table
    private static void displayVehicleArray(Vehicle[] vehicles, String title) {
        System.out.println("\n" + title + ":");
        System.out.println("Rank | Registration | Type  | Mileage | Driver ID | Fuel Usage");
        System.out.println("-----|-------------|-------|---------|-----------|------------");
        
        for (int i = 0; i < vehicles.length; i++) {
            System.out.printf("%-4d | %-11s | %-5s | %-7d | %-9s | %.2f L/100km%n",
                i + 1,
                vehicles[i].registrationNumber,
                vehicles[i].type,
                vehicles[i].mileage,
                vehicles[i].driverId,
                vehicles[i].fuelUsage);
        }
    }

    // File Storage Methods
    private static void saveAllDataToFiles() {
        System.out.println("\n💾 Saving all data to files...");
        
        try {
            // Save vehicles to vehicles.txt
            saveVehiclesToFile();
            
            // Save drivers to drivers.txt
            saveDriversToFile();
            
            // Save deliveries to deliveries.txt
            saveDeliveriesToFile();
            
            // Save maintenance tasks to maintenance.txt
            saveMaintenanceToFile();
            
            System.out.println("✅ All data saved successfully!");
            
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    private static void saveVehiclesToFile() throws IOException {
        FileWriter writer = new FileWriter("vehicles.txt");
        PrintWriter pw = new PrintWriter(writer);
        
        Vehicle[] vehicles = vehicleTree.getAllVehicles();
        pw.println("# Vehicle Data - Registration,Type,Mileage,FuelUsage,DriverId");
        
        for (Vehicle vehicle : vehicles) {
            pw.printf("%s,%s,%d,%.2f,%s%n",
                vehicle.registrationNumber,
                vehicle.type,
                vehicle.mileage,
                vehicle.fuelUsage,
                vehicle.driverId
            );
        }
        
        pw.close();
        writer.close();
        System.out.println("📄 Vehicles saved to vehicles.txt");
    }

    private static void saveDriversToFile() throws IOException {
        FileWriter writer = new FileWriter("drivers.txt");
        PrintWriter pw = new PrintWriter(writer);
        
        Driver[] drivers = driverQueue.getAllDrivers();
        pw.println("# Driver Data - DriverId,Name,ExperienceYears,CurrentLocation");
        
        for (Driver driver : drivers) {
            pw.printf("%s,%s,%d,%s%n",
                driver.driverId,
                driver.name,
                driver.experienceYears,
                driver.currentLocation
            );
        }
        
        pw.close();
        writer.close();
        System.out.println("📄 Drivers saved to drivers.txt");
    }

    private static void saveDeliveriesToFile() throws IOException {
        FileWriter writer = new FileWriter("deliveries.txt");
        PrintWriter pw = new PrintWriter(writer);
        
        Delivery[] deliveries = deliveryQueue.getAllDeliveries();
        pw.println("# Delivery Data - PackageId,Origin,Destination,AssignedVehicle,AssignedDriver,ETA");
        
        for (Delivery delivery : deliveries) {
            pw.printf("%s,%s,%s,%s,%s,%s%n",
                delivery.packageId,
                delivery.origin,
                delivery.destination,
                delivery.assignedVehicle,
                delivery.assignedDriver,
                delivery.eta
            );
        }
        
        pw.close();
        writer.close();
        System.out.println("📄 Deliveries saved to deliveries.txt");
    }

    private static void saveMaintenanceToFile() throws IOException {
        FileWriter writer = new FileWriter("maintenance.txt");
        PrintWriter pw = new PrintWriter(writer);
        
        MaintenanceTask[] tasks = maintenanceScheduler.getAllTasks();
        pw.println("# Maintenance Data - VehicleNumber,Mileage");
        
        for (MaintenanceTask task : tasks) {
            pw.printf("%s,%d%n",
                task.vehicleNumber,
                task.mileage
            );
        }
        
        pw.close();
        writer.close();
        System.out.println("📄 Maintenance tasks saved to maintenance.txt");
    }

    private static void loadAllDataFromFiles() {
        System.out.println("\n📂 Loading all data from files...");
        
        try {
            // Clear existing data
            vehicleTree = new VehicleTree();
            driverQueue = new DriverQueue();
            deliveryQueue = new DeliveryQueue();
            maintenanceScheduler = new MaintenanceScheduler();
            
            // Load data from files
            loadVehiclesFromFile();
            loadDriversFromFile();
            loadDeliveriesFromFile();
            loadMaintenanceFromFile();
            
            System.out.println("✅ All data loaded successfully!");
            
        } catch (IOException e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error parsing data: " + e.getMessage());
        }
    }

    private static void loadVehiclesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("vehicles.txt"));
        String line;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip comment lines
            if (line.startsWith("#") || line.trim().isEmpty()) {
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length == 5) {
                String registration = parts[0].trim();
                String type = parts[1].trim();
                int mileage = Integer.parseInt(parts[2].trim());
                double fuelUsage = Double.parseDouble(parts[3].trim());
                String driverId = parts[4].trim();
                
                Vehicle vehicle = new Vehicle(registration, type, mileage, fuelUsage, driverId);
                vehicleTree.insert(vehicle);
                count++;
            }
        }
        
        reader.close();
        System.out.println("📄 Loaded " + count + " vehicles from vehicles.txt");
    }

    private static void loadDriversFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("drivers.txt"));
        String line;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip comment lines
            if (line.startsWith("#") || line.trim().isEmpty()) {
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length == 4) {
                String driverId = parts[0].trim();
                String name = parts[1].trim();
                int experienceYears = Integer.parseInt(parts[2].trim());
                String currentLocation = parts[3].trim();
                
                Driver driver = new Driver(driverId, name, experienceYears, currentLocation);
                driverQueue.enqueue(driver);
                count++;
            }
        }
        
        reader.close();
        System.out.println("📄 Loaded " + count + " drivers from drivers.txt");
    }

    private static void loadDeliveriesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("deliveries.txt"));
        String line;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip comment lines
            if (line.startsWith("#") || line.trim().isEmpty()) {
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String packageId = parts[0].trim();
                String origin = parts[1].trim();
                String destination = parts[2].trim();
                String assignedVehicle = parts[3].trim();
                String assignedDriver = parts[4].trim();
                String eta = parts[5].trim();
                
                Delivery delivery = new Delivery(packageId, origin, destination, assignedVehicle, assignedDriver, eta);
                deliveryQueue.enqueue(delivery);
                count++;
            }
        }
        
        reader.close();
        System.out.println("📄 Loaded " + count + " deliveries from deliveries.txt");
    }

    private static void loadMaintenanceFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("maintenance.txt"));
        String line;
        int count = 0;
        
        while ((line = reader.readLine()) != null) {
            // Skip comment lines
            if (line.startsWith("#") || line.trim().isEmpty()) {
                continue;
            }
            
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String vehicleNumber = parts[0].trim();
                int mileage = Integer.parseInt(parts[1].trim());
                
                MaintenanceTask task = new MaintenanceTask(vehicleNumber, mileage);
                maintenanceScheduler.addTask(task);
                count++;
            }
        }
        
        reader.close();
        System.out.println("📄 Loaded " + count + " maintenance tasks from maintenance.txt");
    }

    private static void exportSystemReport() {
        System.out.println("\n📊 Generating comprehensive system report...");
        
        try {
            FileWriter writer = new FileWriter("system_report.txt");
            PrintWriter pw = new PrintWriter(writer);
            
            // Header
            pw.println("=== VEHICLE TRACKING & MAINTENANCE SYSTEM REPORT ===");
            pw.println("Generated on: " + java.time.LocalDateTime.now());
            pw.println("===============================================\n");
            
            // Vehicle Summary
            Vehicle[] vehicles = vehicleTree.getAllVehicles();
            pw.println("VEHICLE SUMMARY:");
            pw.println("Total Vehicles: " + vehicles.length);
            
            int trucks = 0, vans = 0;
            double totalMileage = 0, totalFuelUsage = 0;
            
            for (Vehicle vehicle : vehicles) {
                if (vehicle.type.equalsIgnoreCase("truck")) trucks++;
                else if (vehicle.type.equalsIgnoreCase("van")) vans++;
                totalMileage += vehicle.mileage;
                totalFuelUsage += vehicle.fuelUsage;
            }
            
            pw.println("- Trucks: " + trucks);
            pw.println("- Vans: " + vans);
            if (vehicles.length > 0) {
                pw.printf("- Average Mileage: %.2f km%n", totalMileage / vehicles.length);
                pw.printf("- Average Fuel Usage: %.2f L/100km%n", totalFuelUsage / vehicles.length);
            }
            pw.println();
            
            // Driver Summary
            Driver[] drivers = driverQueue.getAllDrivers();
            pw.println("DRIVER SUMMARY:");
            pw.println("Total Available Drivers: " + drivers.length);
            
            if (drivers.length > 0) {
                double totalExperience = 0;
                for (Driver driver : drivers) {
                    totalExperience += driver.experienceYears;
                }
                pw.printf("- Average Experience: %.1f years%n", totalExperience / drivers.length);
            }
            pw.println();
            
            // Delivery Summary
            Delivery[] deliveries = deliveryQueue.getAllDeliveries();
            pw.println("DELIVERY SUMMARY:");
            pw.println("Pending Deliveries: " + deliveries.length);
            pw.println();
            
            // Maintenance Summary
            MaintenanceTask[] tasks = maintenanceScheduler.getAllTasks();
            pw.println("MAINTENANCE SUMMARY:");
            pw.println("Pending Maintenance Tasks: " + tasks.length);
            
            if (tasks.length > 0) {
                // Find most urgent task (lowest mileage)
                MaintenanceTask mostUrgent = tasks[0];
                for (MaintenanceTask task : tasks) {
                    if (task.mileage < mostUrgent.mileage) {
                        mostUrgent = task;
                    }
                }
                pw.println("Most Urgent: Vehicle " + mostUrgent.vehicleNumber + " (" + mostUrgent.mileage + " km until service)");
            }
            pw.println();
            
            // Detailed Vehicle List
            pw.println("DETAILED VEHICLE LIST:");
            pw.println("Reg Number\tType\tMileage\tFuel Usage\tDriver ID");
            pw.println("--------------------------------------------------------");
            for (Vehicle vehicle : vehicles) {
                pw.printf("%s\t%s\t%d\t%.2f\t%s%n",
                    vehicle.registrationNumber,
                    vehicle.type,
                    vehicle.mileage,
                    vehicle.fuelUsage,
                    vehicle.driverId);
            }
            pw.println();
            
            // Detailed Driver List
            if (drivers.length > 0) {
                pw.println("DETAILED DRIVER LIST:");
                pw.println("Driver ID\tName\tExperience\tLocation");
                pw.println("-------------------------------------------");
                for (Driver driver : drivers) {
                    pw.printf("%s\t%s\t%d years\t%s%n",
                        driver.driverId,
                        driver.name,
                        driver.experienceYears,
                        driver.currentLocation);
                }
                pw.println();
            }
            
            pw.println("=== END OF REPORT ===");
            
            pw.close();
            writer.close();
            
            System.out.println("✅ System report exported to system_report.txt");
            System.out.println("📊 Report includes vehicle, driver, delivery, and maintenance summaries");
            
        } catch (IOException e) {
            System.out.println("❌ Error generating report: " + e.getMessage());
        }
    }
}
