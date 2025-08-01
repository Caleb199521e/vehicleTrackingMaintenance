import java.util.Scanner;

public class Main {
    private static VehicleTree vehicleTree = new VehicleTree();
    private static DriverQueue driverQueue = new DriverQueue();
    private static DeliveryQueue deliveryQueue = new DeliveryQueue();
    private static MaintenanceScheduler maintenanceScheduler = new MaintenanceScheduler();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Welcome message
        displayWelcomeMessage();
        
        // Start interactive menu
        showMenu();
    }

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

    private static void showMenu() {
        while (true) {
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
            System.out.println(" 17. Exit");
            System.out.println("=".repeat(60));
            System.out.print("Please select an option (1-17): ");

            int choice = getIntInput();
            
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
                    addDriver();
                    break;
                case 7:
                    displayAvailableDrivers();
                    break;
                case 8:
                    assignDriver();
                    break;
                case 9:
                    createDeliveryRecord();
                    break;
                case 10:
                    viewPendingDeliveries();
                    break;
                case 11:
                    processNextDelivery();
                    break;
                case 12:
                    createMaintenanceRecord();
                    break;
                case 13:
                    scheduleMaintenanceTask();
                    break;
                case 14:
                    viewScheduledMaintenance();
                    break;
                case 15:
                    processNextMaintenanceTask();
                    break;
                case 16:
                    checkVehicleMaintenanceDue();
                    break;
                case 17:
                    System.out.println("Thank you for using Vehicle Tracking System!");
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addVehicle() {
        System.out.println("\n=== Add New Vehicle ===");
        
        System.out.print("Enter Registration Number (e.g., GT1234-22): ");
        String regNumber = scanner.nextLine().trim();
        
        // Check if vehicle already exists
        if (vehicleTree.searchByRegistration(regNumber) != null) {
            System.out.println("Error: Vehicle with registration " + regNumber + " already exists!");
            return;
        }
        
        System.out.print("Enter Vehicle Type (Truck/Van): ");
        String type = scanner.nextLine().trim();
        
        // Validate vehicle type
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

    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
