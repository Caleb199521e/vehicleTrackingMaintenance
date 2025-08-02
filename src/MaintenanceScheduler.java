import java.util.PriorityQueue;

/**
 * MaintenanceScheduler class - manages maintenance tasks using priority queue
 * Tasks are prioritized by mileage (lower mileage = higher priority)
 */
public class MaintenanceScheduler {
    // Priority queue to store maintenance tasks (min-heap)
    private PriorityQueue<MaintenanceTask> taskHeap = new PriorityQueue<>();

    // Add a maintenance task to the priority queue
    public void addTask(MaintenanceTask task) {
        taskHeap.add(task);
        System.out.println("Scheduled maintenance for: " + task.vehicleNumber);
    }

    // Process the highest priority maintenance task
    public void processNextTask() {
        if (taskHeap.isEmpty()) {
            System.out.println("No maintenance tasks available.");
            return;
        }

        // Remove and return the highest priority task
        MaintenanceTask task = taskHeap.poll();
        System.out.println("\n🔧 Servicing vehicle with highest priority:");
        task.displayInfo();
    }

    // Display all scheduled tasks (without removing them)
    public void showAllTasks() {
        if (taskHeap.isEmpty()) {
            System.out.println("No pending maintenance tasks.");
            return;
        }

        System.out.println("\n📋 Pending Maintenance Tasks:");
        // Iterate through all tasks in the queue
        for (MaintenanceTask task : taskHeap) {
            task.displayInfo();
            System.out.println("-----");
        }
    }

    // Check if scheduler has any pending tasks
    public boolean isEmpty() {
        return taskHeap.isEmpty();
    }

    // Get all maintenance tasks as an array (for file storage)
    public MaintenanceTask[] getAllTasks() {
        return taskHeap.toArray(new MaintenanceTask[0]);
    }

    // Clear all tasks from scheduler
    public void clear() {
        taskHeap.clear();
    }

    // Get the number of pending tasks
    public int getTaskCount() {
        return taskHeap.size();
    }
}
