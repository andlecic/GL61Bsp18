package synthesizer;
// package <package name>;
import java.sql.Array;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (!this.isFull()) {
            rb[last] = x;
            fillCount += 1;
            if (last + 1 == this.capacity) {
                last = 0;
            } else {
                last += 1;
            }
        } else {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        T res = rb[first];
        if (!this.isEmpty()) {
            fillCount -= 1;
            if (first + 1 == this.capacity) {
                first = 0;
            } else {
                first += 1;
            }
            return res;
        } else {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            return rb[first];
        }
    }

    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }

    private class ArrayRingIterator implements Iterator<T> {
        private int pos;
        private int count;

        public ArrayRingIterator() {
            pos = first;
            count = 0;
        }

        public boolean hasNext() {
            return count < fillCount;
        }

        public T next() {
            if (this.hasNext()) {
                T res = rb[pos];
                count += 1;
                if (pos == capacity) {
                    pos = 0;
                } else {
                    pos += 1;
                }
                return res;
            } else {
                throw new RuntimeException("No Next Item");
            }
        }
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.
}
