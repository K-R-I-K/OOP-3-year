import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyThreadPool implements Executor {
    private final Thread[] threads;
    private final BlockingQueue<Runnable> tasks;
    private final AtomicBoolean toShutdown;

    public MyThreadPool(int threadsCount) {
        this.threads = new Thread[threadsCount];
        this.tasks = new LinkedBlockingQueue<>();
        this.toShutdown = new AtomicBoolean(false);
        for (int i = 0; i < threadsCount; i++) {
            threads[i] = new Thread(() -> {
                while (!toShutdown.get() || !tasks.isEmpty()) {
                    Runnable task = tasks.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            });
            threads[i].start();
        }
    }

    public void shutdown() {
        toShutdown.set(true);
    }

    public void shutdownNow() {
        toShutdown.set(true);
        for (Thread thread : threads) {
            thread.interrupt();
        }
        tasks.clear();
    }

    @Override
    public void execute(Runnable command) {
        if (!toShutdown.get()) {
            try {
                tasks.put(command);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}