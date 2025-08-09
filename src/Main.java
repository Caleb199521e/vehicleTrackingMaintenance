import datastructures.*;
import java.util.Scanner;
import java.io.*;

/**
 * Main class - Vehicle Tracking & Maintenance System
 * Central application that manages vehicles, drivers, deliveries, and maintenance
 * Uses various data structures: BST for vehicles, queues for drivers/deliveries, priority queue for maintenance
 */
public class Main {
    // Core data structures for the system - all from custom datastructures package
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
        System.out.println("* Manage vehicles (trucks and vans)");
        System.out.println("* Track drivers and their assignments");
        System.out.println("* Monitor maintenance records");
        System.out.println("* Handle delivery assignments");
        System.out.println("=".repeat(60));
        System.out.println("Start by adding vehicles and drivers to get started!");
        System.out.println();
    }

    // Main menu system - displays main categories and handles navigation
    private static void showMenu() {
        while (true) { // Continuous loop until user exits
            try {
                // Display main menu with categories
                System.out.println("\n" + "=".repeat(60));
                System.out.println("           VEHICLE TRACKING SYSTEM - MAIN MENU");
                System.out.println("=".repeat(60));
                System.out.println("Please select a category:");
                System.out.println();
                System.out.println("  1. Vehicle Management");
                System.out.println("  2. Driver Management");
                System.out.println("  3. Delivery Operations");
                System.out.println("  4. Maintenance Management");
                System.out.println("  5. Fuel Efficiency Reports");
                System.out.println("  6. Search & Sort Features");
                System.out.println("  7. File Storage");
                System.out.println("  8. Exit");
                System.out.println();
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-8): ");

                int choice = getIntInputSafe(); // Get user input with validation
                
                // Route to appropriate sub-menu based on user choice
                switch (choice) {
                    case 1:
                        showVehicleManagementMenu();
                        break;
                    case 2:
                        showDriverManagementMenu();
                        break;
                    case 3:
                        showDeliveryOperationsMenu();
                        break;
                    case 4:
                        showMaintenanceManagementMenu();
                        break;
                    case 5:
                        showFuelEfficiencyMenu();
                        break;
                    case 6:
                        showSearchSortMenu();
                        break;
                    case 7:
                        showFileStorageMenu();
                        break;
                    case 8:
                        System.out.println("\n" + "=".repeat(60));
                        System.out.println("Thank you for using Vehicle Tracking System!");
                        System.out.println("Have a great day!");
                        System.out.println("=".repeat(60));
                        return;
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-8.");
                }
            } catch (Exception e) {
                System.out.println("❌ An unexpected error occurred: " + e.getMessage());
                System.out.println("Returning to main menu...");
            }
        }
    }

    // ===== SUB-MENU METHODS =====

    // Vehicle Management Sub-Menu
    private static void showVehicleManagementMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("VEHICLE MANAGEMENT MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Add New Vehicle (Truck/Van)");
                System.out.println("  2. Remove Vehicle");
                System.out.println("  3. Search Vehicle by Registration");
                System.out.println("  4. Search Vehicle by Mileage");
                System.out.println("  5. Display All Vehicles");
                System.out.println("  6. <- Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-6): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        addVehicle();
                        break;
                    case 2:
                        removeVehicle();
                        break;
                    case 3:
                        searchByRegistration();
                        break;
                    case 4:
                        searchByMileage();
                        break;
                    case 5:
                        displayAllVehicles();
                        break;
                    case 6:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-6.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in vehicle management: " + e.getMessage());
            }
        }
    }

    // Driver Management Sub-Menu
    private static void showDriverManagementMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("👨‍💼           DRIVER MANAGEMENT MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Add New Driver");
                System.out.println("  2. Display Available Drivers");
                System.out.println("  3. Assign Next Available Driver");
                System.out.println("  4. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-4): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        addDriver();
                        break;
                    case 2:
                        displayAvailableDrivers();
                        break;
                    case 3:
                        assignDriver();
                        break;
                    case 4:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in driver management: " + e.getMessage());
            }
        }
    }

    // Delivery Operations Sub-Menu
    private static void showDeliveryOperationsMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("📦           DELIVERY OPERATIONS MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Create New Delivery Record");
                System.out.println("  2. View Pending Deliveries");
                System.out.println("  3. Process Next Delivery");
                System.out.println("  4. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-4): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        createDeliveryRecord();
                        break;
                    case 2:
                        viewPendingDeliveries();
                        break;
                    case 3:
                        processNextDelivery();
                        break;
                    case 4:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in delivery operations: " + e.getMessage());
            }
        }
    }

    // Maintenance Management Sub-Menu
    private static void showMaintenanceManagementMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("🔧           MAINTENANCE MANAGEMENT MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Create Maintenance Record");
                System.out.println("  2. Schedule Maintenance Task");
                System.out.println("  3. View Scheduled Maintenance");
                System.out.println("  4. Process Next Maintenance Task");
                System.out.println("  5. Check Vehicle Maintenance Due");
                System.out.println("  6. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-6): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        createMaintenanceRecord();
                        break;
                    case 2:
                        scheduleMaintenanceTask();
                        break;
                    case 3:
                        viewScheduledMaintenance();
                        break;
                    case 4:
                        processNextMaintenanceTask();
                        break;
                    case 5:
                        checkVehicleMaintenanceDue();
                        break;
                    case 6:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-6.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in maintenance management: " + e.getMessage());
            }
        }
    }

    // Fuel Efficiency Reports Sub-Menu
    private static void showFuelEfficiencyMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("⛽           FUEL EFFICIENCY REPORTS MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Generate Fuel Efficiency Report");
                System.out.println("  2. View Fuel Outliers");
                System.out.println("  3. Filter Vehicles by Fuel Performance");
                System.out.println("  4. Sort Vehicles by Fuel Efficiency");
                System.out.println("  5. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-5): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        generateFuelEfficiencyReport();
                        break;
                    case 2:
                        viewFuelOutliers();
                        break;
                    case 3:
                        filterVehiclesByFuelPerformance();
                        break;
                    case 4:
                        sortVehiclesByFuelEfficiency();
                        break;
                    case 5:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-5.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in fuel efficiency reports: " + e.getMessage());
            }
        }
    }

    // Search & Sort Features Sub-Menu
    private static void showSearchSortMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("🔍           SEARCH & SORT FEATURES MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Binary Search by Registration Number");
                System.out.println("  2. Quick Sort Vehicles by Mileage");
                System.out.println("  3. Merge Sort Vehicles by Driver Name");
                System.out.println("  4. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-4): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        binarySearchByRegistration();
                        break;
                    case 2:
                        quickSortVehiclesByMileage();
                        break;
                    case 3:
                        mergeSortVehiclesByDriverName();
                        break;
                    case 4:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in search & sort features: " + e.getMessage());
            }
        }
    }

    // File Storage Sub-Menu
    private static void showFileStorageMenu() {
        while (true) {
            try {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("💾           FILE STORAGE MENU");
                System.out.println("=".repeat(60));
                System.out.println("  1. Save All Data to Files");
                System.out.println("  2. Load All Data from Files");
                System.out.println("  3. Export System Report");
                System.out.println("  4. ← Back to Main Menu");
                System.out.println("=".repeat(60));
                System.out.print("Enter your choice (1-4): ");

                int choice = getIntInputSafe();
                
                switch (choice) {
                    case 1:
                        saveAllDataToFiles();
                        break;
                    case 2:
                        loadAllDataFromFiles();
                        break;
                    case 3:
                        exportSystemReport();
                        break;
                    case 4:
                        return; // Go back to main menu
                    default:
                        System.out.println("❌ Invalid option. Please enter a number between 1-4.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error in file storage operations: " + e.getMessage());
            }
        }
    }

    // ===== VEHICLE MANAGEMENT METHODS (Binary Search Tree Operations) =====
    
    // Add new vehicle to BST - demonstrates BST insertion with validation
    private static void addVehicle() {
        try {
            System.out.println("\n=== Add New Vehicle ===");
            
            String regNumber = getStringInputSafe("Enter Registration Number (e.g., GT1234-22): ");
            
            // Check for duplicates using BST search - O(log n) average case
            if (vehicleTree.searchByRegistration(regNumber) != null) {
                System.out.println("❌ Error: Vehicle with registration " + regNumber + " already exists!");
                pauseForUser();
                return;
            }
            
            String type = getStringInputSafe("Enter Vehicle Type (Truck/Van): ");
            
            // Input validation - ensure data integrity
            if (!type.equalsIgnoreCase("Truck") && !type.equalsIgnoreCase("Van")) {
                System.out.println("❌ Error: Vehicle type must be either 'Truck' or 'Van'");
                pauseForUser();
                return;
            }
            
            System.out.print("Enter Current Mileage: ");
            int mileage = getIntInput();
            
            if (mileage < 0) {
                System.out.println("❌ Error: Mileage cannot be negative!");
                pauseForUser();
                return;
            }
            
            System.out.print("Enter Fuel Usage (Litres per 100km): ");
            double fuelUsage = getDoubleInput();
            
            if (fuelUsage <= 0) {
                System.out.println("❌ Error: Fuel usage must be greater than 0!");
                pauseForUser();
                return;
            }
            
            System.out.print("Enter Assigned Driver ID (or press Enter if unassigned): ");
            String driverId = scanner.nextLine().trim();
            if (driverId.isEmpty()) {
                driverId = "UNASSIGNED";
            }
            
            Vehicle newVehicle = new Vehicle(regNumber, type, mileage, fuelUsage, driverId);
            vehicleTree.insert(newVehicle);
            
            System.out.println("\n✅ Vehicle added successfully!");
            newVehicle.displayInfo();
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error adding vehicle: " + e.getMessage());
            System.out.println("Please try again.");
            pauseForUser();
        }
    }

    private static void removeVehicle() {
        try {
            System.out.println("\n=== Remove Vehicle ===");
            String regNumber = getStringInputSafe("Enter Registration Number to remove: ");
            
            Vehicle vehicle = vehicleTree.searchByRegistration(regNumber);
            if (vehicle == null) {
                System.out.println("❌ Error: Vehicle with registration " + regNumber + " not found!");
                pauseForUser();
                return;
            }
            
            System.out.println("\nVehicle found:");
            Vehicle.displayTableHeader();
            vehicle.displayInfo();
            Vehicle.displayTableFooter();
            
            if (getConfirmation("\nAre you sure you want to remove this vehicle?")) {
                if (vehicleTree.remove(regNumber)) {
                    System.out.println("✅ Vehicle removed successfully!");
                } else {
                    System.out.println("❌ Error: Failed to remove vehicle.");
                }
            } else {
                System.out.println("❌ Vehicle removal cancelled.");
            }
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error removing vehicle: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void searchByRegistration() {
        try {
            System.out.println("\n=== Search by Registration ===");
            String regNumber = getStringInputSafe("Enter Registration Number: ");
            
            Vehicle found = vehicleTree.searchByRegistration(regNumber);
            if (found != null) {
                System.out.println("\n✅ Vehicle found:");
                Vehicle.displayTableHeader();
                found.displayInfo();
                Vehicle.displayTableFooter();
            } else {
                System.out.println("❌ Vehicle with registration " + regNumber + " not found.");
            }
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error searching vehicle: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void searchByMileage() {
        try {
            System.out.println("\n=== Search by Mileage ===");
            System.out.print("Enter Mileage: ");
            int targetMileage = getIntInput();
            
            Vehicle found = vehicleTree.searchByMileage(targetMileage);
            if (found != null) {
                System.out.println("\n✅ Vehicle found with mileage " + targetMileage + ":");
                Vehicle.displayTableHeader();
                found.displayInfo();
                Vehicle.displayTableFooter();
            } else {
                System.out.println("❌ Vehicle with mileage " + targetMileage + " not found.");
            }
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error searching vehicle by mileage: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void displayAllVehicles() {
        try {
            vehicleTree.displayAllVehicles();
            pauseForUser();
        } catch (Exception e) {
            System.out.println("❌ Error displaying vehicles: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void addDriver() {
        try {
            System.out.println("\n=== Add New Driver ===");
            
            String driverId = getStringInputSafe("Enter Driver ID (e.g., D01): ");
            String name = getStringInputSafe("Enter Driver Name: ");
            
            System.out.print("Enter Years of Experience: ");
            int experience = getIntInput();
            
            String location = getStringInputSafe("Enter Location/Base: ");
            
            Driver newDriver = new Driver(driverId, name, experience, location);
            driverQueue.enqueue(newDriver);
            
            System.out.println("\n✅ Driver added successfully!");
            Driver.displayTableHeader();
            newDriver.displayInfo();
            Driver.displayTableFooter();
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error adding driver: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void displayAvailableDrivers() {
        try {
            System.out.println("\n=== Available Drivers ===");
            driverQueue.displayAvailableDrivers();
            pauseForUser();
        } catch (Exception e) {
            System.out.println("❌ Error displaying drivers: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void assignDriver() {
        try {
            System.out.println("\n=== Assign Next Available Driver ===");
            Driver assignedDriver = driverQueue.dequeue();
            if (assignedDriver != null) {
                System.out.println("✅ Driver assigned:");
                Driver.displayTableHeader();
                assignedDriver.displayInfo();
                Driver.displayTableFooter();
                System.out.println("Note: This driver has been removed from the available queue.");
            } else {
                System.out.println("❌ No drivers available for assignment.");
            }
            pauseForUser();
        } catch (Exception e) {
            System.out.println("❌ Error assigning driver: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void createDeliveryRecord() {
        try {
            System.out.println("\n=== Create Delivery Record ===");
            
            String packageId = getStringInputSafe("Enter Package ID: ");
            String origin = getStringInputSafe("Enter Origin Location: ");
            String destination = getStringInputSafe("Enter Destination: ");
            String vehicleReg = getStringInputSafe("Enter Vehicle Registration Number: ");
            
            // Verify vehicle exists
            Vehicle vehicle = vehicleTree.searchByRegistration(vehicleReg);
            if (vehicle == null) {
                System.out.println("❌ Error: Vehicle with registration " + vehicleReg + " not found!");
                System.out.println("Please add the vehicle first or use an existing vehicle.");
                pauseForUser();
                return;
            }
            
            String driverId = getStringInputSafe("Enter Driver ID: ");
            String deliveryTime = getStringInputSafe("Enter Delivery Time: ");
            
            Delivery delivery = new Delivery(packageId, origin, destination, vehicleReg, driverId, deliveryTime);
            
            // Add delivery to queue
            deliveryQueue.enqueue(delivery);
            
            System.out.println("\n✅ Delivery record created and added to queue successfully!");
            Delivery.displayTableHeader();
            delivery.displayInfo();
            Delivery.displayTableFooter();
            System.out.println("Status: PENDING - Added to delivery queue");
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error creating delivery record: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void viewPendingDeliveries() {
        try {
            System.out.println("\n=== Pending Deliveries ===");
            deliveryQueue.displayPendingDeliveries();
            
            if (!deliveryQueue.isEmpty()) {
                System.out.println("\nNote: Deliveries are processed in First-In-First-Out (FIFO) order.");
            }
            pauseForUser();
        } catch (Exception e) {
            System.out.println("❌ Error viewing pending deliveries: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void processNextDelivery() {
        try {
            System.out.println("\n=== Process Next Delivery ===");
            
            Delivery nextDelivery = deliveryQueue.dequeue();
            if (nextDelivery != null) {
                System.out.println("✅ Processing delivery:");
                Delivery.displayTableHeader();
                nextDelivery.displayInfo();
                Delivery.displayTableFooter();
                System.out.println("Status: COMPLETED - Delivery has been processed and removed from queue");
                
                // Optional: Update vehicle mileage after delivery
                updateVehicleMileageAfterDelivery(nextDelivery);
            } else {
                System.out.println("❌ No pending deliveries to process.");
            }
            pauseForUser();
        } catch (Exception e) {
            System.out.println("❌ Error processing delivery: " + e.getMessage());
            pauseForUser();
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
        try {
            System.out.println("\n=== Create Maintenance Record ===");
            
            String date = getStringInputSafe("Enter Maintenance Date (YYYY-MM-DD): ");
            String serviceType = getStringInputSafe("Enter Service Type (e.g., Oil Change, Brake Pads, etc.): ");
            
            System.out.print("Enter Cost: GH₵");
            double cost = getDoubleInput();
            
            MaintenanceRecord maintenance = new MaintenanceRecord(date, serviceType, cost);
            
            System.out.println("\n✅ Maintenance record created successfully!");
            MaintenanceRecord.displayTableHeader();
            maintenance.displayInfo();
            MaintenanceRecord.displayTableFooter();
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error creating maintenance record: " + e.getMessage());
            pauseForUser();
        }
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
        MaintenanceTask.displayTableHeader();
        task.displayInfo();
        MaintenanceTask.displayTableFooter();
        
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

    // Enhanced input validation method with exception handling for menu navigation
    private static int getIntInputSafe() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("❌ Please enter a number: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("❌ Invalid input. Please enter a valid number: ");
            } catch (Exception e) {
                System.out.println("❌ Unexpected error reading input: " + e.getMessage());
                System.out.print("Please try again: ");
            }
        }
    }

    // Input validation method for decimal numbers - prevents crashes from invalid input
    private static double getDoubleInput() {
        while (true) { // Keep asking until valid input
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("❌ Please enter a number: ");
                    continue;
                }
                return Double.parseDouble(input); // Try to parse as double
            } catch (NumberFormatException e) {
                // Handle invalid input gracefully
                System.out.print("❌ Invalid input. Please enter a valid decimal number: ");
            } catch (Exception e) {
                System.out.println("❌ Unexpected error reading input: " + e.getMessage());
                System.out.print("Please try again: ");
            }
        }
    }

    // Safe string input method with validation
    private static String getStringInputSafe(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("❌ Input cannot be empty. Please try again.");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("❌ Error reading input: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    // Pause and wait for user before returning to menu
    private static void pauseForUser() {
        try {
            System.out.println("\n" + "=".repeat(60));
            System.out.print("Press Enter to continue...");
            scanner.nextLine();
        } catch (Exception e) {
            // Handle any input errors gracefully
        }
    }

    // Confirmation method for important operations
    private static boolean getConfirmation(String message) {
        try {
            System.out.print(message + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            return input.equals("y") || input.equals("yes");
        } catch (Exception e) {
            System.out.println("❌ Error reading confirmation: " + e.getMessage());
            return false;
        }
    }

    // ===== FUEL EFFICIENCY ANALYTICS METHODS =====
    
    // Generate comprehensive fuel efficiency report with statistics
    private static void generateFuelEfficiencyReport() {
        try {
            System.out.println("\n===== FUEL EFFICIENCY REPORT =====");
            if (vehicleTree.isEmpty()) {
                System.out.println("❌ No vehicles in the system.");
                pauseForUser();
                return;
            }

            Vehicle[] vehicles = vehicleTree.getAllVehicles();
            if (vehicles.length == 0) {
                System.out.println("❌ No vehicles found.");
                pauseForUser();
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
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error generating fuel efficiency report: " + e.getMessage());
            pauseForUser();
        }
    }

    private static void viewFuelOutliers() {
        try {
            System.out.println("\n===== FUEL EFFICIENCY OUTLIERS =====");
            if (vehicleTree.isEmpty()) {
                System.out.println("❌ No vehicles in the system.");
                pauseForUser();
                return;
            }

            Vehicle[] vehicles = vehicleTree.getAllVehicles();
            if (vehicles.length == 0) {
                System.out.println("❌ No vehicles found.");
                pauseForUser();
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
                System.out.println("✅ No fuel efficiency outliers found.");
            }
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error viewing fuel outliers: " + e.getMessage());
            pauseForUser();
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
            Vehicle.displayTableHeader();
            found.displayInfo();
            Vehicle.displayTableFooter();
        } else {
            System.out.println("✗ Vehicle with registration '" + regNumber + "' not found.");
            System.out.println("Search completed in: " + String.format("%.3f", searchTime) + " ms");
        }
    }

    // Quick sort vehicles by mileage
    private static void quickSortVehiclesByMileage() {
        try {
            System.out.println("\n===== QUICK SORT BY MILEAGE =====");
            if (vehicleTree.isEmpty()) {
                System.out.println("❌ No vehicles in the system.");
                pauseForUser();
                return;
            }

            Vehicle[] vehicles = vehicleTree.getAllVehicles();
            if (vehicles.length == 0) {
                System.out.println("❌ No vehicles found.");
                pauseForUser();
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

            System.out.println("\n✅ Quick Sort completed in: " + String.format("%.3f", sortTime) + " ms");
            System.out.println("Vehicles sorted by mileage (ascending):");
            displayVehicleArray(vehicles, "Sorted by Mileage");
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error sorting vehicles by mileage: " + e.getMessage());
            pauseForUser();
        }
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
        try {
            System.out.println("\n===== MERGE SORT BY DRIVER NAME =====");
            if (vehicleTree.isEmpty()) {
                System.out.println("❌ No vehicles in the system.");
                pauseForUser();
                return;
            }

            Vehicle[] vehicles = vehicleTree.getAllVehicles();
            if (vehicles.length == 0) {
                System.out.println("❌ No vehicles found.");
                pauseForUser();
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

            System.out.println("\n✅ Merge Sort completed in: " + String.format("%.3f", sortTime) + " ms");
            System.out.println("Vehicles sorted by driver name (alphabetical):");
            displayVehicleArray(vehicles, "Sorted by Driver Name");
            pauseForUser();
            
        } catch (Exception e) {
            System.out.println("❌ Error sorting vehicles by driver name: " + e.getMessage());
            pauseForUser();
        }
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
            // Save vehicles to data/vehicles.txt
            saveVehiclesToFile();
            
            // Save drivers to data/drivers.txt
            saveDriversToFile();
            
            // Save deliveries to data/deliveries.txt
            saveDeliveriesToFile();
            
            // Save maintenance tasks to data/maintenance.txt
            saveMaintenanceToFile();
            
            System.out.println("✅ All data saved successfully!");
            
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    private static void saveVehiclesToFile() throws IOException {
        FileWriter writer = new FileWriter("data/vehicles.txt");
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
        System.out.println("Vehicles saved to data/vehicles.txt");
    }

    private static void saveDriversToFile() throws IOException {
        FileWriter writer = new FileWriter("data/drivers.txt");
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
        System.out.println("Drivers saved to data/drivers.txt");
    }

    private static void saveDeliveriesToFile() throws IOException {
        FileWriter writer = new FileWriter("data/deliveries.txt");
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
        System.out.println("Deliveries saved to data/deliveries.txt");
    }

    private static void saveMaintenanceToFile() throws IOException {
        FileWriter writer = new FileWriter("data/maintenance.txt");
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
        System.out.println("Maintenance tasks saved to data/maintenance.txt");
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
        BufferedReader reader = new BufferedReader(new FileReader("data/vehicles.txt"));
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
        System.out.println("Loaded " + count + " vehicles from data/vehicles.txt");
    }

    private static void loadDriversFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/drivers.txt"));
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
        System.out.println("Loaded " + count + " drivers from data/drivers.txt");
    }

    private static void loadDeliveriesFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/deliveries.txt"));
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
        System.out.println("Loaded " + count + " deliveries from data/deliveries.txt");
    }

    private static void loadMaintenanceFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("data/maintenance.txt"));
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
        System.out.println("Loaded " + count + " maintenance tasks from data/maintenance.txt");
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
