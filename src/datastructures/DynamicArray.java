package datastructures;

/**
 * DynamicArray class - custom implementation to replace Java ArrayList
 * Provides dynamic resizing capability without using Java collections
 */
public class DynamicArray<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] array;
    private int size;
    private int capacity;

    // Constructor
    public DynamicArray() {
        this.capacity = INITIAL_CAPACITY;
        this.array = new Object[capacity];
        this.size = 0;
    }

    // Add element to the end of array
    public void add(T element) {
        if (size >= capacity) {
            resize();
        }
        array[size] = element;
        size++;
    }

    // Get element at specific index
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }

    // Set element at specific index
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        array[index] = element;
    }

    // Remove element at specific index
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        T removedElement = (T) array[index];
        
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        
        size--;
        array[size] = null; // Clear reference
        return removedElement;
    }

    // Get current size
    public int size() {
        return size;
    }

    // Check if array is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Convert to regular array
    @SuppressWarnings("unchecked")
    public T[] toArray(T[] result) {
        for (int i = 0; i < size; i++) {
            result[i] = (T) array[i];
        }
        return result;
    }

    // Clear all elements
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    // Private method to resize array when capacity is reached
    private void resize() {
        capacity *= 2;
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
}
