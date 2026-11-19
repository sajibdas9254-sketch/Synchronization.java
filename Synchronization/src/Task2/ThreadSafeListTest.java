package Task2;

public class ThreadSafeListTest {
    private static final int THREADS = 10;
    private static final int ITEMS_PER_THREAD = 1000;

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeList safeList = new ThreadSafeList();


        Thread[] adders = new Thread[THREADS];
        for (int t = 0; t < THREADS; t++) {
            final int threadId = t;
            adders[t] = new Thread(() -> {
                for (int i = 0; i < ITEMS_PER_THREAD; i++) {
                    safeList.add("Thread" + threadId + "-Item" + i);
                }
            });
            adders[t].start();
        }
        for (Thread adder : adders) {
            adder.join();
        }

        int expectedAfterAdd = THREADS * ITEMS_PER_THREAD;
        System.out.println("Size after adding: " + safeList.size()
                + " (expected " + expectedAfterAdd + ")");


        Thread[] removers = new Thread[THREADS];
        for (int t = 0; t < THREADS; t++) {
            final int threadId = t;
            removers[t] = new Thread(() -> {
                for (int i = 0; i < ITEMS_PER_THREAD / 2; i++) {
                    safeList.remove("Thread" + threadId + "-Item" + i);
                }
            });
            removers[t].start();
        }
        for (Thread remover : removers) {
            remover.join();
        }

        int expectedAfterRemove = expectedAfterAdd / 2;
        System.out.println("Size after removing: " + safeList.size()
                + " (expected " + expectedAfterRemove + ")");

        if (safeList.size() == expectedAfterRemove) {
            System.out.println("Test passed: the list is thread-safe.");
        } else {
            System.out.println("Test failed: the list is NOT thread-safe.");
        }
    }
}
