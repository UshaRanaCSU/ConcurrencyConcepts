import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        CounterThread counterThread1 = new CounterThread(true, lock);
        CounterThread counterThread2 = new CounterThread(false, lock);

        counterThread1.start();
        counterThread2.start();
    }
}

class CounterThread extends Thread {
    private final boolean isCountingUp;
    private final Lock lock;

    public CounterThread(boolean isCountingUp, Lock lock) {
        this.isCountingUp = isCountingUp;
        this.lock = lock;
    }

    @Override
    public void run() {
        if (isCountingUp) {
            countUp();
        } else {
            countDown();
        }
    }

    private void countUp() {
        for (int i = 1; i <= 20; i++) {
            lock.lock();
            try {
                System.out.println("Counting up: " + i);
                Thread.sleep(500); // Sleep for 500 milliseconds between each count
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private void countDown() {
        for (int i = 20; i >= 0; i--) {
            lock.lock();
            try {
                System.out.println("Counting down: " + i);
                Thread.sleep(500); // Sleep for 500 milliseconds between each count
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
