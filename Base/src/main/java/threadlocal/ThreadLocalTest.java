package threadlocal;

public class ThreadLocalTest {

    public static void main(String[] args) {
        TestThread testThread1 = new ThreadLocalTest.TestThread();
        TestThread testThread2 = new ThreadLocalTest.TestThread();
        testThread1.start();
        testThread2.start();

    }

    public static class TestThread extends Thread {

        ThreadLocal local = new ThreadLocal<String>();

        @Override
        public void run() {
            local.set(Thread.currentThread().getName());
            System.out.println(":" + local.get());
        }
    }
}
