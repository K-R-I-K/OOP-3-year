import java.util.concurrent.atomic.AtomicReference;

public class MyNode<T> {
    T value;
    AtomicReference<MyNode<T>> next;

    MyNode() {
        this.next = new AtomicReference<>();
    }
    MyNode(T value) {
        this.value = value;
        this.next = new AtomicReference<>();
    }
}