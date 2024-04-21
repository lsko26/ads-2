import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements MyList<T> {

    @Override
    public void set(int index, T element) {
        Node node = nodeAt(index);
        if (node != null) {
            node.element = element;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node(element);
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node current = nodeAt(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    @Override
    public void addFirst(T element) {
        Node newNode = new Node(element);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T element) {
        add(element);
    }

    @Override
    public T getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    @Override
    public T getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size - 1) {
            removeLast();
        } else {
            Node toRemove = nodeAt(index);
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
    }

    @Override
    public void sort() {
        if (size > 1) {
            for (Node node1 = head; node1.next != null; node1 = node1.next) {
                T value = node1.next.element;
                Node node2 = node1;
                while (node2 != null && ((Comparable<T>)value).compareTo(node2.element) < 0) {
                    node2.next.element = node2.element;
                    node2 = node2.prev;
                }
                if (node2 == null) {
                    head.element = value;
                } else {
                    node2.next.element = value;
                }
            }
        }
    }

    @Override
    public int indexOf(Object object) {
        Node current = head;
        for (int index = 0; current != null; current = current.next, index++) {
            if ((object == null && current.element == null) || (object != null && object.equals(current.element))) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        Node current = tail;
        for (int index = size - 1; current != null; current = current.prev, index--) {
            if ((object == null && current.element == null) || (object != null && object.equals(current.element))) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            array[index++] = current.element;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}
