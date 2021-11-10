public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int firstindex;
    private int lastindex;
    private static final int start_size = 8;
    private static final int Rfactor = 2;


    public ArrayDeque() {
        items = (T []) new Object[start_size];
        size = 0;
        firstindex = 0;
        lastindex = 0;
    }
@Override
    public void addFirst(T item) {
        if (size == 0) {
            firstindex = 0;
            lastindex = 0;
            items[0] = item;
            size += 1;
            return;
        }
        if (size == items.length) {
            upsize();
        }
        if (firstindex == 0) {
            firstindex = items.length - 1;
        } else {
            firstindex -= 1;
        }
        items[firstindex] = item;
        size += 1;
    }
    @Override
    public void addLast(T item) {
        if (size == 0) {
            firstindex = 0;
            lastindex = 0;
            items[size] = item;
            size += 1;
            return;
        }
        if (size == items.length) {
            upsize();
        }
        if (lastindex == items.length - 1) {
            lastindex = 0;
        } else {
            lastindex += 1;
        }
        items[lastindex] = item;
        size += 1;
    }

    private void upsize() {
        T[] a = (T []) new Object[items.length * Rfactor];
        int sizeFirstHalf = items.length - firstindex;
        System.arraycopy(items, firstindex, a, 0, sizeFirstHalf);
        System.arraycopy(items, 0, a, sizeFirstHalf, size - sizeFirstHalf);
        items = a;
        firstindex = 0;
        lastindex = size - 1;
    }

    private void downsize() {
        T[] a = (T []) new Object[items.length / 2];
        if (lastindex < firstindex) {
            int sizeFirstHalf = items.length - firstindex;
            System.arraycopy(items, firstindex, a, 0, sizeFirstHalf);
            System.arraycopy(items, 0, a, sizeFirstHalf, size - sizeFirstHalf);
        } else {
            System.arraycopy(items, firstindex, a, 0, size);
        }
        items = a;
        firstindex = 0;
        lastindex = size - 1;
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[firstindex];
        items[firstindex] = null;
        if (firstindex == items.length - 1) {
            firstindex = 0;
        } else {
            firstindex += 1;
        }
        size -= 1;
        if (size == 0) {
            firstindex = 0;
            lastindex = 0;
        }
        if ((float) size / items.length < 0.5 && size > 16) {
            downsize();
        }
        return returnItem;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T returnItem = items[lastindex];
        items[lastindex] = null;
        if (lastindex == 0) {
            lastindex = items.length - 1;
        } else {
            lastindex -= 1;
        }
        size -= 1;
        if (size == 0) {
            lastindex = 0;
            firstindex = 0;
        }
        if ((float) size / items.length < 0.5 && size > 16) {
            downsize();
        }
        return returnItem;
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        for (int i = 0; i < items.length; i++) {
            System.out.print(items[i] + " ");
        }
    }
    @Override
    public T get(int index) {
        if (size <= index) {
            return null;
        }
        return items[(index + firstindex) % items.length];
    }
}