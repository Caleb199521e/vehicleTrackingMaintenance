public class DeliveryQueue {
    private static final int MAX = 100;
    private Delivery[] queue = new Delivery [MAX]; 
    private int front = 0;
    private int rear = -1;
    private int count = 0;

    // To add delivery to the queue
    public void enqueue(Delivery delivery) {
        if (count == MAX) {
            System.out.println("Delivery queue is full!");
            return;
        }
        rear = (rear + 1) % MAX;
        queue[rear] = delivery;
        count++;
    }

    // Process the next delivery (remove from queue)
    public Delivery dequeue() {
        if (count == 0) {
            System.out.println("No deliveries to process!");
            return null;
        }

        Delivery processed = queue[front];
        front = (front + 1) % MAX;
        count--;
        return processed;
    }

    // View all pending deliveries
    public void displayPendingDeliveries() {
        if (count == 0) {
            System.out.println("No pending deliveries.");
            return;
        }
        System.out.println("Pending Deliveries:");
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
