# Vehicle Tracking & Maintenance System - Data Structures Organization

## Project Structure

```
vehicleTrackingMaintenance/
├── src/
│   ├── datastructures/           # Custom Data Structures Package
│   │   ├── Vehicle.java          # Vehicle entity class
│   │   ├── VehicleNode.java      # BST node for vehicles
│   │   ├── VehicleTree.java      # Binary Search Tree implementation
│   │   ├── Driver.java           # Driver entity class  
│   │   ├── DriverQueue.java      # Circular Queue for drivers
│   │   ├── Delivery.java         # Delivery entity class
│   │   ├── DeliveryQueue.java    # Circular Queue for deliveries
│   │   ├── MaintenanceRecord.java    # Maintenance record entity
│   │   ├── MaintenanceTask.java      # Maintenance task entity
│   │   └── MaintenanceScheduler.java # Custom Priority Queue (Min-Heap)
│   └── Main.java                 # Main application class
├── data/                         # Data Files Directory
│   ├── vehicles.txt              # Vehicle data storage (50 vehicles)
│   ├── drivers.txt               # Driver data storage (50 Ghanaian drivers)
│   ├── deliveries.txt            # Delivery data storage
│   └── maintenance.txt           # Maintenance data storage
└── vehicleTrackingMaintenance.iml # IntelliJ project configuration
```

## Custom Data Structures Implemented

### 1. **Binary Search Tree (VehicleTree.java)**
- **Purpose**: Manages vehicles efficiently with O(log n) search/insert/delete
- **Key Features**:
  - Organizes vehicles by mileage for fast searching
  - In-order traversal for sorted vehicle display
  - Binary search for registration numbers
  - Professional table-style output formatting
  - No Java Collections used - pure array-based implementation

**Algorithmic Justification**:
- **Efficient Search**: O(log n) vs O(n) linear search - 50 vehicles require max 6 comparisons vs 25 average
- **Natural Ordering**: Mileage-based organization enables quick maintenance scheduling
- **Range Queries**: Perfect for finding vehicles needing service within mileage ranges
- **Scalability**: Handles fleet growth from 10 to 1000+ vehicles efficiently

### 2. **Circular Queue (DriverQueue.java & DeliveryQueue.java)**
- **Purpose**: FIFO management for drivers and deliveries
- **Key Features**:
  - Fixed-size array with circular indexing
  - Efficient enqueue/dequeue operations
  - Prevents queue overflow with proper size management
  - Table-formatted display for professional output
  - No Java Collections used - custom array implementation

**Algorithmic Justification**:
- **FIFO Fairness**: Essential for equitable driver assignment and legal compliance
- **Memory Efficiency**: Circular reuse prevents wasted array space
- **Constant Time**: O(1) operations maintain responsiveness during peak periods
- **Bounded Capacity**: Prevents system overload with manageable queue limits

### 3. **Priority Queue - Min Heap (MaintenanceScheduler.java)**
- **Purpose**: Prioritizes maintenance tasks by urgency (mileage)
- **Key Features**:
  - Custom min-heap implementation using arrays
  - Lower mileage = higher priority (CRITICAL, HIGH, MEDIUM, LOW)
  - Efficient heapify operations (up/down)
  - Priority-based task scheduling
  - No Java PriorityQueue used - built from scratch

**Algorithmic Justification**:
- **Safety Critical**: CRITICAL priority (≤500km) processed before MEDIUM (≤2000km)
- **Efficient Priority Management**: O(log n) insertion/extraction vs O(n) sorted arrays
- **Always Access Most Urgent**: O(1) peek operation for workshop planning
- **Dynamic Priorities**: Handles changing vehicle conditions without restructuring

## Data Structure Usage in Main.java

```java
// Import custom data structures package
import datastructures.*;

public class Main {
    // All data structures are from custom package
    private static VehicleTree vehicleTree = new VehicleTree();
    private static DriverQueue driverQueue = new DriverQueue();
    private static DeliveryQueue deliveryQueue = new DeliveryQueue();
    private static MaintenanceScheduler maintenanceScheduler = new MaintenanceScheduler();
    
    // Methods call data structure operations
    public static void addVehicle() {
        Vehicle vehicle = new Vehicle(...);
        vehicleTree.insert(vehicle);  // BST insertion
    }
    
    public static void addDriver() {
        Driver driver = new Driver(...);
        driverQueue.enqueue(driver);  // Queue insertion
    }
    
    public static void scheduleMaintenanceTask() {
        MaintenanceTask task = new MaintenanceTask(...);
        maintenanceScheduler.addTask(task);  // Heap insertion
    }
}
```

## Algorithm Complexities

| Data Structure | Operation | Time Complexity | Space Complexity | Justification |
|----------------|-----------|----------------|------------------|---------------|
| **VehicleTree (BST)** | Insert/Search/Delete | O(log n) avg, O(n) worst | O(n) | Efficient search + natural mileage ordering |
| **DriverQueue** | Enqueue/Dequeue | O(1) | O(n) | FIFO fairness + constant time operations |
| **DeliveryQueue** | Enqueue/Dequeue | O(1) | O(n) | Fair scheduling + memory efficiency |
| **MaintenanceScheduler** | Insert/Extract-Min | O(log n) | O(n) | Priority processing for safety-critical tasks |

## Search & Sort Algorithm Justifications

### **Binary Search Implementation**
```java
// O(log n) vehicle lookup vs O(n) linear search
public Vehicle searchByRegistration(String registration) {
    return searchRecursive(root, registration);
}
```
**Why Binary Search**: Leverages existing BST structure for O(log n) performance. 50 vehicles = max 6 comparisons vs 25 average for linear search.

### **Quick Sort for Numeric Data (Vehicle Mileage)**
```java
// O(n log n) average case for integer comparisons
private static void quickSort(Vehicle[] vehicles, int low, int high)
```
**Justification**: Optimal for numeric data with good cache locality. In-place sorting minimizes memory overhead.

### **Merge Sort for String Data (Driver Names)**
```java
// O(n log n) guaranteed performance for string comparisons  
private static void mergeSort(Vehicle[] vehicles, int left, int right)
```
**Justification**: Stable sort maintains relative order. Predictable performance for lexicographic string ordering.

### **Weighted Scoring for Smart Driver Assignment**
```java
// Multi-criteria optimization: 60% experience + 40% proximity
double combinedScore = (experienceScore * 0.6) + (proximityScore * 0.4);
```
**Justification**: Balances service quality (experience) with operational efficiency (proximity). Configurable weights adapt to business priorities.

## Compilation & Execution

```bash
# Navigate to project directory
cd vehicleTrackingMaintenance

# Compile all Java files (data structures and main)
javac -cp src src/Main.java

# Run the application
java -cp src Main

# Data files are automatically loaded from data/ folder:
# - data/vehicles.txt (50 vehicles with realistic data)
# - data/drivers.txt (50 Ghanaian drivers) 
# - data/deliveries.txt (delivery records)
# - data/maintenance.txt (maintenance tasks)
```

## Data Management

### File Organization
- **Centralized Data Storage**: All data files organized in `data/` folder
- **Consistent File Communication**: Robust read/write operations
- **Realistic Sample Data**: 
  - 50 vehicles (trucks and vans) with some unassigned for realism
  - 50 drivers with authentic Ghanaian names and locations
  - Sample delivery and maintenance records
- **Ghana-Specific Features**:
  - Currency in Ghana Cedis (GH₵)
  - Local city names (Accra, Kumasi, Tamale, etc.)
  - "Mileage" terminology instead of "kilometers"

### Professional Display Format
All outputs use consistent table formatting:
```
+--------------+-------+----------+------------+--------------+
| Registration | Type  | Mileage  | Fuel Usage | Driver ID    |
+--------------+-------+----------+------------+--------------+
| GT1234-22    | Truck | 15000    | 12.50      | DRV001       |
| GR2456-22    | Van   | 8500     | 9.20       | DRV002       |
+--------------+-------+----------+------------+--------------+
```

## No Java Collections Used

This project demonstrates **pure custom data structure implementations**:

- Custom Binary Search Tree
- Custom Circular Queues
- Custom Priority Queue (Min-Heap)
- Custom Dynamic Array
- Array-based implementations only

## Key Features Implemented

### Core Functionality
1. **Vehicle Management**: BST for efficient vehicle organization
2. **Driver Assignment**: Queue-based FIFO assignment system  
3. **Delivery Tracking**: Queue-based delivery processing
4. **Maintenance Scheduling**: Priority-based task management with urgency levels

### Advanced Features
5. **File Storage**: Persistent data storage in organized `data/` folder
6. **Search & Sort**: Binary search, QuickSort, MergeSort algorithms
7. **Analytics**: Fuel efficiency reports and outlier detection
8. **Smart Assignment**: Proximity and experience-based driver assignment
9. **Professional UI**: Table-formatted displays throughout the system
10. **Error Handling**: Robust input validation and user-friendly error messages

### System Characteristics
- **Menu-Driven Interface**: Clean navigation with back options
- **Real-time Data Updates**: Changes immediately reflect in file storage
- **Comprehensive Reporting**: Detailed analytics and system reports


## System Menu Structure

```
MAIN MENU
├── 1. Vehicle Management
│   ├── Add Vehicle
│   ├── Remove Vehicle  
│   ├── Search by Registration
│   ├── Search by Mileage
│   └── Display All Vehicles
├── 2. Driver Management
│   ├── Add Driver
│   ├── Display Available Drivers
│   └── Assign Next Driver
├── 3. Delivery Operations
│   ├── Create Delivery Record
│   ├── View Pending Deliveries
│   └── Process Next Delivery
├── 4. Maintenance Management
│   ├── Create Maintenance Record
│   ├── Schedule Maintenance Task
│   ├── View Scheduled Maintenance
│   └── Process Next Maintenance
├── 5. Fuel Efficiency Reports
│   ├── Generate Fuel Efficiency Report
│   ├── View Fuel Outliers
│   ├── Filter by Fuel Performance
│   └── Sort by Fuel Efficiency
├── 6. Search & Sort Features
│   ├── Binary Search by Registration
│   ├── Quick Sort by Mileage
│   └── Merge Sort by Driver Name
├── 7. File Storage
│   ├── Save All Data
│   ├── Load All Data
│   └── Generate System Report
└── 8. Exit
```

Team Members 
Caleb Ampong (11354029) - Team Lead 
Sumaila Saida ()
Eric Adu Osei ()
Pamela Pearl Boamah() 
Prince Adofo()