public class DriverQueue {
    private static final int MAX = 100;
    private Driver[] drivers = new Driver[MAX];
    private int front = 0;
    private int rear = -1;
    private int count = 0;

    // Add driver to the queue
    public void enqueue(Driver driver) {
        if (count == MAX) {
            System.out.println("Queue is full!");
            return;
        }
        rear = (rear + 1) % MAX;
        drivers[rear] = driver;
        count++;
    }

    // Assign (remove) driver from the queue
    public Driver dequeue() {
        if (count == 0) {
            System.out.println("No available drivers!");
            return null;
        }
        Driver assigned = drivers[front];
        front = (front + 1) % MAX;
        count--;
        return assigned;
    }

    // View all available drivers
    public void displayAvailableDrivers() {
        if (count == 0) {
            System.out.println("No drivers available.");
            return;
        }
        System.out.println("Available Drivers:");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX;
            drivers[index].displayInfo();
            System.out.println("-----");
        }
    }
}
