package datastructures;

/**
 * MaintenanceScheduler class - manages maintenance tasks using custom priority queue (min-heap)
 * Tasks are prioritized by mileage (lower mileage = higher priority)
 
 */
public class MaintenanceScheduler {
    private static final int MAX_TASKS = 100;
    private MaintenanceTask[] heap = new MaintenanceTask[MAX_TASKS];
    private int size = 0;

    // Add a maintenance task to the priority queue
    public void addTask(MaintenanceTask task) {
        if (size >= MAX_TASKS) {
            System.out.println("Maintenance scheduler is full!");
            return;
        }
        
        heap[size] = task;
        heapifyUp(size);
        size++;
        System.out.println("Scheduled maintenance for: " + task.vehicleNumber);
    }

    // Process the highest priority maintenance task (lowest mileage)
    public void processNextTask() {
        if (size == 0) {
            System.out.println("No maintenance tasks available.");
            return;
        }

        // Remove and return the highest priority task (root of min-heap)
        MaintenanceTask task = heap[0];
        System.out.println("\nServicing vehicle with highest priority:");
        MaintenanceTask.displayTableHeader();
        task.displayInfo();
        MaintenanceTask.displayTableFooter();
        
        // Replace root with last element and heapify down
        heap[0] = heap[size - 1];
        size--;
        if (size > 0) {
            heapifyDown(0);
        }
    }

    // Display all scheduled tasks (without removing them)
    public void showAllTasks() {
        if (size == 0) {
            System.out.println("No pending maintenance tasks.");
            return;
        }

        System.out.println("\nPending Maintenance Tasks (Priority Order):");
        // Create a copy and sort by priority to display in order
        MaintenanceTask[] sortedTasks = new MaintenanceTask[size];
        for (int i = 0; i < size; i++) {
            sortedTasks[i] = heap[i];
        }
        
        // Simple sorting for display purposes
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (sortedTasks[j].compareTo(sortedTasks[j + 1]) > 0) {
                    MaintenanceTask temp = sortedTasks[j];
                    sortedTasks[j] = sortedTasks[j + 1];
                    sortedTasks[j + 1] = temp;
                }
            }
        }
        
        MaintenanceTask.displayTableHeader();
        for (int i = 0; i < size; i++) {
            sortedTasks[i].displayInfo();
        }
        MaintenanceTask.displayTableFooter();
    }

    // Check if scheduler has any pending tasks
    public boolean isEmpty() {
        return size == 0;
    }

    // Get all maintenance tasks as an array (for file storage)
    public MaintenanceTask[] getAllTasks() {
        MaintenanceTask[] tasks = new MaintenanceTask[size];
        for (int i = 0; i < size; i++) {
            tasks[i] = heap[i];
        }
        return tasks;
    }

    // Check if an identical maintenance task already exists (same vehicle and same mileage)
    public boolean taskExists(String vehicleNumber, int mileage) {
        for (int i = 0; i < size; i++) {
            if (heap[i].vehicleNumber.equals(vehicleNumber) && heap[i].mileage == mileage) {
                return true;
            }
        }
        return false;
    }

    // Find a maintenance task by vehicle number and mileage
    public MaintenanceTask findTask(String vehicleNumber, int mileage) {
        for (int i = 0; i < size; i++) {
            if (heap[i].vehicleNumber.equals(vehicleNumber) && heap[i].mileage == mileage) {
                return heap[i];
            }
        }
        return null;
    }

    // Update all maintenance tasks for a vehicle when its mileage increases
    public void updateTasksForVehicle(String vehicleNumber, int additionalMileage) {
        boolean updated = false;
        for (int i = 0; i < size; i++) {
            if (heap[i].vehicleNumber.equals(vehicleNumber)) {
                int oldMileage = heap[i].mileage;
                heap[i].mileage = Math.max(0, oldMileage - additionalMileage);
                updated = true;
            }
        }
        
        if (updated) {
            // Re-heapify the entire heap to maintain priority order
            for (int i = size / 2 - 1; i >= 0; i--) {
                heapifyDown(i);
            }
            System.out.println("Maintenance schedules updated for vehicle: " + vehicleNumber);
        }
    }

    // Clear all tasks from scheduler
    public void clear() {
        size = 0;
    }

    // Get the number of pending tasks
    public int getTaskCount() {
        return size;
    }

    // Maintain min-heap property by moving element up
    private void heapifyUp(int index) {
        if (index == 0) return; // Root reached
        
        int parentIndex = (index - 1) / 2;
        if (heap[index].compareTo(heap[parentIndex]) < 0) {
            // Swap with parent if current element has higher priority (lower mileage)
            MaintenanceTask temp = heap[index];
            heap[index] = heap[parentIndex];
            heap[parentIndex] = temp;
            heapifyUp(parentIndex);
        }
    }

    // Maintain min-heap property by moving element down
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        // Find the smallest among current, left child, and right child
        if (leftChild < size && heap[leftChild].compareTo(heap[smallest]) < 0) {
            smallest = leftChild;
        }
        if (rightChild < size && heap[rightChild].compareTo(heap[smallest]) < 0) {
            smallest = rightChild;
        }

        // If smallest is not current index, swap and continue heapifying
        if (smallest != index) {
            MaintenanceTask temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(smallest);
        }
    }
}
