import java.util.concurrent.atomic.AtomicReference;

public class MyNonBlockingQueue<T> {
    private final AtomicReference<MyNode<T>> head;
    private final AtomicReference<MyNode<T>> tail;

    public MyNonBlockingQueue() {
        MyNode<T> dummy = new MyNode<>();
        this.head = new AtomicReference<>(dummy);
        this.tail = new AtomicReference<>(dummy);
    }

    public void push(T value) {
        MyNode<T> toPush = new MyNode<>(value);
        MyNode<T> currentTail;
        MyNode<T> currentNext;

        while (true) {
            currentTail = tail.get();
            currentNext = currentTail.next.get();

            if (currentTail == tail.get()) {
                if (currentNext == null) {
                    if (currentTail.next.compareAndSet(null, toPush)) {
                        break;
                    }
                } else {
                    tail.compareAndSet(currentTail, currentNext);
                }
            }
        }
        tail.compareAndSet(currentTail, toPush);
    }

    public T pop() {
        T result;
        MyNode<T> currentHead;
        MyNode<T> currentTail;
        MyNode<T> currentNext;

        while (true) {
            currentHead = head.get();
            currentTail = tail.get();
            currentNext = currentHead.next.get();

            if (currentHead == head.get()) {
                if (currentHead == currentTail) {
                    if (currentNext == null) {
                        return null;
                    }
                    tail.compareAndSet(currentTail, currentNext);
                } else {
                    result = currentNext.value;
                    if (head.compareAndSet(currentHead, currentNext)) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}