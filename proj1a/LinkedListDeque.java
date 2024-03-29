public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    private class TNode {
        private T item;
        private TNode next;
        private TNode previous;

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

//    private void LinkedListDeque(T item) {
//        sentinel = new TNode(null, null, null);
//        sentinel.next = new TNode(item, sentinel, sentinel);
//        sentinel.previous = sentinel.next;
//    }

    public void addFirst(T item) {
        size += 1;
        TNode first = new TNode(item, sentinel.next, sentinel);
        sentinel.next = first;
        first.next.previous = first;
    }

    public void addLast(T item) {
        size += 1;
        TNode last = new TNode(item, sentinel, sentinel.previous);
        sentinel.previous = last;
        last.previous.next = last;
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
        TNode lstpointer = sentinel;
        while (lstpointer.next != sentinel) {
            System.out.print(lstpointer.next + " ");
            lstpointer = lstpointer.next;
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        TNode removedNode = sentinel.next;
        sentinel.next = removedNode.next;
        removedNode.next.previous = sentinel;
//        if (size == 0) {
//            sentinel.previous = sentinel;
//        }
        return removedNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        TNode removedNode = sentinel.previous;
        sentinel.previous = removedNode.previous;
        removedNode.previous.next = sentinel;
//        if (size == 0) {
//            sentinel.next = sentinel;
//        }
        return removedNode.item;
    }

    public T get(int index) {
//        if (index + 1 > size) {
//            return null;
//        }
        int i = 0;
        TNode temppointer = sentinel.next;
        while (i < index && temppointer != sentinel) {
            i += 1;
            temppointer = temppointer.next;
        }
        return temppointer.item;
    }


    private T getRecursiveHelper(int index, TNode nodenow) {
        if (index > size) {
            return null;
        }
        if (index <= 0) {
            return nodenow.item;
        } else {
            return getRecursiveHelper(index - 1, nodenow.next);
        }
    }


    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
}
