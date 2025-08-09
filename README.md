# Vehicle Tracking & Maintenance System - Data Structures Organization

## 📁 Project Structure

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
│   │   ├── MaintenanceScheduler.java # Custom Priority Queue (Min-Heap)
│   │   └── DynamicArray.java         # Custom dynamic array implementation
│   └── Main.java                 # Main application class
├── vehicles.txt                  # Vehicle data storage
├── drivers.txt                   # Driver data storage  
├── deliveries.txt               # Delivery data storage
└── maintenance.txt              # Maintenance data storage
```

## 🛠️ Custom Data Structures Implemented

### 1. **Binary Search Tree (VehicleTree.java)**
- **Purpose**: Manages vehicles efficiently with O(log n) search/insert/delete
- **Key Features**:
  - Organizes vehicles by mileage for fast searching
  - In-order traversal for sorted vehicle display
  - Binary search for registration numbers
  - No Java Collections used - pure array-based implementation

### 2. **Circular Queue (DriverQueue.java & DeliveryQueue.java)**
- **Purpose**: FIFO management for drivers and deliveries
- **Key Features**:
  - Fixed-size array with circular indexing
  - Efficient enqueue/dequeue operations
  - Prevents queue overflow with proper size management
  - No Java Collections used - custom array implementation

### 3. **Priority Queue - Min Heap (MaintenanceScheduler.java)**
- **Purpose**: Prioritizes maintenance tasks by urgency (mileage)
- **Key Features**:
  - Custom min-heap implementation using arrays
  - Lower mileage = higher priority
  - Efficient heapify operations (up/down)
  - No Java PriorityQueue used - built from scratch

### 4. **Dynamic Array (DynamicArray.java)**
- **Purpose**: Resizable array to replace ArrayList functionality
- **Key Features**:
  - Automatic resizing when capacity is reached
  - Generic type support
  - Add, remove, get, set operations
  - No Java ArrayList used - custom implementation

## 🎯 Data Structure Usage in Main.java

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

## 📊 Algorithm Complexities

| Data Structure | Operation | Time Complexity | Space Complexity |
|----------------|-----------|----------------|------------------|
| **VehicleTree (BST)** | Insert/Search/Delete | O(log n) avg, O(n) worst | O(n) |
| **DriverQueue** | Enqueue/Dequeue | O(1) | O(n) |
| **DeliveryQueue** | Enqueue/Dequeue | O(1) | O(n) |
| **MaintenanceScheduler** | Insert/Extract-Min | O(log n) | O(n) |
| **DynamicArray** | Add/Access | O(1) amortized | O(n) |

## 🔧 Compilation & Execution

```bash
# Compile data structures first
javac -cp src src/datastructures/*.java

# Compile main application
javac -cp src src/Main.java

# Run the application
java -cp src Main
```

## ✅ No Java Collections Used

This project demonstrates **pure custom data structure implementations**:

- ❌ No `java.util.ArrayList`
- ❌ No `java.util.LinkedList`  
- ❌ No `java.util.Queue`
- ❌ No `java.util.PriorityQueue`
- ❌ No `java.util.HashMap`
- ❌ No `java.util.TreeMap`

- ✅ Custom Binary Search Tree
- ✅ Custom Circular Queues
- ✅ Custom Priority Queue (Min-Heap)
- ✅ Custom Dynamic Array
- ✅ Array-based implementations only

## 🚀 Key Features Implemented

1. **Vehicle Management**: BST for efficient vehicle organization
2. **Driver Assignment**: Queue-based FIFO assignment system
3. **Delivery Tracking**: Queue-based delivery processing
4. **Maintenance Scheduling**: Priority-based task management
5. **File Storage**: Persistent data storage without databases
6. **Search & Sort**: Binary search, QuickSort, MergeSort algorithms
7. **Analytics**: Fuel efficiency reports and outlier detection
8. **Smart Assignment**: Proximity and experience-based driver assignment

## 📝 Academic Requirements Met

- **Custom Data Structures**: All major data structures coded from scratch
- **Algorithm Implementation**: Search, sort, and heap algorithms implemented
- **Object-Oriented Design**: Proper encapsulation and modularity
- **Package Organization**: Clean separation of concerns
- **No Collections Framework**: Pure algorithmic implementations
- **Real-world Application**: Practical vehicle tracking system

This organization demonstrates mastery of fundamental data structures and algorithms without relying on built-in Java collections, making it perfect for academic computer science coursework.
