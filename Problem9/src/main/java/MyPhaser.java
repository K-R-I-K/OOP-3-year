public class MyPhaser {
    private int parties;
    private int phase;
    private int partiesAwait;

    public MyPhaser() {
        this(0);
    }

    public MyPhaser(int parties) {
        this.parties = parties;
        this.phase = 0;
        this.partiesAwait = parties;
    }

    public synchronized int register() {
        ++parties;
        ++partiesAwait;
        return phase;
    }

    public synchronized int arriveAndAwaitAdvance() {
        --partiesAwait;
        if (partiesAwait > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        partiesAwait = parties;
        ++phase;
        return phase;
    }

    public synchronized int arriveAndDeregister() {
        --partiesAwait;
        --parties;
        if (partiesAwait == 0) {
            notifyAll();
            ++phase;
            partiesAwait = parties;
        }
        return phase;
    }
}
