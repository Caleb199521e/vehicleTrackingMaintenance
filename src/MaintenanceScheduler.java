import java.util.PriorityQueue;

public class MaintenanceScheduler {
    private PriorityQueue<MaintenanceTask> taskHeap = new PriorityQueue<>();

    // Add a vehicle to the maintenance queue
    public void addTask(MaintenanceTask task) {
        taskHeap.add(task);
        System.out.println("Scheduled maintenance for: " + task.vehicleNumber);
    }

    // Service the most urgent vehicle
    public void processNextTask() {
        if (taskHeap.isEmpty()) {
            System.out.println("No maintenance tasks available.");
            return;
        }

        MaintenanceTask task = taskHeap.poll();
        System.out.println("\n🔧 Servicing vehicle with highest priority:");
        task.displayInfo();
    }

    // Show all scheduled tasks
    public void showAllTasks() {
        if (taskHeap.isEmpty()) {
            System.out.println("No pending maintenance tasks.");
            return;
        }

        System.out.println("\n📋 Pending Maintenance Tasks:");
        for (MaintenanceTask task : taskHeap) {
            task.displayInfo();
            System.out.println("-----");
        }
    }

    public boolean isEmpty() {
        return taskHeap.isEmpty();
    }
}
