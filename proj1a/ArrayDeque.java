public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
    }
// resize to add one item at the beginning of the array
    private void resizeFirst(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 1, size);
        items = a;
    }
// resize to add one item at the end of array//
    private void resizedLast(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

// check if R (size/items.length) is smaller than 0.25
    private boolean is_r_small() {
        if (((float) size / items.length) < 0.25) {
            return true;
        }
        return false;
    }
//  resize to remove the first item
    private void resizedFirstRemove(int capacity) {
        if (is_r_small()) {
            capacity = capacity / 2;
        }
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 1, a, 0, size - 1);
        items = a;
    }

    private void resizedLastRemove(int capacity) {
        if (is_r_small()) {
            capacity = capacity / 2;
        }
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size - 1);
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeFirst(size * 4);
        } else {
            resizeFirst(items.length);
        }
        items[0] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resizedLast(size * 4);
        } else {
            resizedLast(items.length);
        }
        items[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (T i : items) {
            System.out.print(i + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = get(0);
        items[0] = null;
        resizedFirstRemove(items.length);
        size -= 1;
        return returnItem;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = get(size - 1);
        items[size - 1] = null;
        resizedLastRemove(items.length);
        size -= 1;
        return returnItem;
    }

    public T get(int index) {
        if (size <= index) {
            return null;
        }
        return items[index];
    }
}
