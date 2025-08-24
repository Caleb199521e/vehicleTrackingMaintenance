package datastructures;

/**
 * DeliveryQueue class - implements circular queue for managing delivery orders
 * Processes deliveries in FIFO order to ensure fair scheduling
 */
public class DeliveryQueue {
    private static final int MAX = 100; // Maximum queue capacity
    private Delivery[] queue = new Delivery[MAX]; // Array to store deliveries
    private int front = 0;  // Points to first delivery
    private int rear = -1;  // Points to last delivery
    private int count = 0;  // Current number of deliveries in queue

    // Add delivery to the back of the queue
    public void enqueue(Delivery delivery) {
        if (count == MAX) {
            System.out.println("Delivery queue is full!");
            return;
        }
        rear = (rear + 1) % MAX; // Circular increment
        queue[rear] = delivery;
        count++;
    }

    // Remove and return next delivery to process
    public Delivery dequeue() {
        if (count == 0) {
            System.out.println("No deliveries to process!");
            return null;
        }

        Delivery processed = queue[front];
        front = (front + 1) % MAX; // Circular increment
        count--;
        return processed;
    }

    // Display all pending deliveries in queue
    public void displayPendingDeliveries() {
        if (count == 0) {
            System.out.println("No pending deliveries.");
            return;
        }
        System.out.println("Pending Deliveries:");
        Delivery.displayTableHeader();
        // Traverse queue from front to rear
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            queue[index].displayInfo();
        }
        Delivery.displayTableFooter();
    }

    // Get all deliveries as an array (for file storage)
    public Delivery[] getAllDeliveries() {
        Delivery[] allDeliveries = new Delivery[count];
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            allDeliveries[i] = queue[index];
        }
        return allDeliveries;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Check if a delivery with the given package ID already exists
    public boolean deliveryExists(String packageId) {
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            if (queue[index].packageId.equals(packageId)) {
                return true;
            }
        }
        return false;
    }

    // Find a delivery by package ID
    public Delivery findDeliveryById(String packageId) {
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            if (queue[index].packageId.equals(packageId)) {
                return queue[index];
            }
        }
        return null;
    }

    // Clear all deliveries from queue
    public void clear() {
        front = 0;
        rear = -1;
        count = 0;
    }
}
