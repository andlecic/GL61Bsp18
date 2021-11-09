public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    private class TNode {
        public T item;
        public TNode next;
        public TNode previous;

        public TNode(T i, TNode n, TNode p) {
            item = i;
            next = n;
            previous = p;
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode(null, sentinel, sentinel);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    private void LinkedListDeque(T item) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.previous = sentinel.next;
    }

    public void addFirst(T item) {
        size += 1;
        TNode first = new TNode(item, sentinel.next, sentinel);
        sentinel.next.previous = first;
        sentinel.next = first;
    }

    public void addLast(T item) {
        size += 1;
        TNode last = new TNode(item, sentinel, sentinel.previous);
        sentinel.previous.next = last;
        sentinel.previous = last;
    }

    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.previous == sentinel) {
            return true;
        }
        return false;
    }

    public int size() {

        return size;
    }

    public void printDeque() {
        TNode copy_list_pointer = sentinel;
        while (copy_list_pointer.next != sentinel) {
            System.out.print(copy_list_pointer.next + " ");
            copy_list_pointer = copy_list_pointer.next;
        }
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        size -= 1;
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        if (size == 0) {
            sentinel.previous = sentinel;
        }
        return first;
    }

    public T removeLast() {
        if (sentinel.previous == sentinel) {
            return null;
        }
        size -= 1;
        T last = sentinel.previous.item;
        sentinel.previous = sentinel.previous.previous;
        if (size == 0) {
            sentinel.next = sentinel;
        }
        return last;
    }

    public T get(int index) {
        if (index + 1 > size) {
            return null;
        }
        int i = 0;
        TNode temp_pointer = sentinel.next;
        while (i < index) {
            i += 1;
            temp_pointer = temp_pointer.next;
        }
        return temp_pointer.item;
    }


    private T getRecursiveHelper(int index, TNode nodenow) {
        if (index > size) {
            return null;
        }
        if (index == 0) {
            return nodenow.item;
        } else {
            return getRecursiveHelper(index - 1, nodenow.next);
        }
    }


    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
}
