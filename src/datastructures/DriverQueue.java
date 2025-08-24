package datastructures;

/**
 * DriverQueue class - implements circular queue for managing available drivers
 * Uses FIFO (First In, First Out) principle for fair driver assignment
 */
public class DriverQueue {
    private static final int MAX = 100; // Maximum queue capacity
    private Driver[] drivers = new Driver[MAX]; // Array to store drivers
    private int front = 0;  // Points to first element
    private int rear = -1;  // Points to last element
    private int count = 0;  // Current number of drivers in queue

    // Add driver to the back of the queue
    public void enqueue(Driver driver) {
        if (count == MAX) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % MAX; // Circular increment
        drivers[rear] = driver;
        count++;
    }

    // Remove and return driver from front of queue (assign driver)
    public Driver dequeue() {
        if (count == 0) {
            System.out.println("No available drivers!");
            return null;
        }
        Driver assigned = drivers[front];
        front = (front + 1) % MAX; // Circular increment
        count--;
        return assigned;
    }

    // Display all drivers currently in the queue
    public void displayAvailableDrivers() {
        if (count == 0) {
            System.out.println("No drivers available.");
            return;
        }
        System.out.println("Available Drivers:");
        Driver.displayTableHeader();
        // Traverse queue from front to rear
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            drivers[index].displayInfo();
        }
        Driver.displayTableFooter();
    }

    // Get all drivers as an array (for file storage)
    public Driver[] getAllDrivers() {
        Driver[] allDrivers = new Driver[count];
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            allDrivers[i] = drivers[index];
        }
        return allDrivers;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if a driver with the given ID already exists
    public boolean driverExists(String driverId) {
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            if (drivers[index].driverId.equals(driverId)) {
                return true;
            }
        }
        return false;
    }

    // Find a driver by ID
    public Driver findDriverById(String driverId) {
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            if (drivers[index].driverId.equals(driverId)) {
                return drivers[index];
            }
        }
        return null;
    }

    // Clear all drivers from queue
    public void clear() {
        front = 0;
        rear = -1;
        count = 0;
    }
}
