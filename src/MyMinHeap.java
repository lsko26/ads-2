public class MyMinHeap<T extends Comparable<T>> {
    private MyArrayList<T> heap;

    public MyMinHeap() {
        heap = new MyArrayList<>();
    }

    public void insert(T item) {
        heap.add(item);
        siftUp();
    }

    private void siftUp() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            T item = heap.get(index);
            T parent = heap.get(parentIndex);
            if (item.compareTo(parent) < 0) {
                heap.set(index, parent);
                heap.set(parentIndex, item);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        T minItem = heap.get(0);
        T lastItem = heap.get(heap.size() - 1);
        heap.set(0, lastItem);
        heap.remove(heap.size() - 1);
        siftDown();
        return minItem;
    }

    private void siftDown() {
        int index = 0;
        int leftChildIndex = 2 * index + 1;
        while (leftChildIndex < heap.size()) {
            int minIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if (rightChildIndex < heap.size()) {
                if (heap.get(rightChildIndex).compareTo(heap.get(leftChildIndex)) < 0) {
                    minIndex = rightChildIndex;
                }
            }
            if (heap.get(index).compareTo(heap.get(minIndex)) > 0) {
                T temp = heap.get(index);
                heap.set(index, heap.get(minIndex));
                heap.set(minIndex, temp);
                index = minIndex;
                leftChildIndex = 2 * index + 1;
            } else {
                break;
            }
        }
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    public int size() {
        return heap.size();
    }
}
