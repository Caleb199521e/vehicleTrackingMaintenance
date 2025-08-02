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
        // Traverse queue from front to rear
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            queue[index].displayInfo();
            System.out.println("-----");
        }
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return count == 0;
    }
}
