package api;

/**
 * wait()
 * 1.方法的使用需结合synchronized来使用 获取对像的monitor(监视器)
 * 2.在未获取monitor的时候会抛出InterruptedException异常
 *      注意获取监视器之后对象值的变化 https://blog.csdn.net/historyasamirror/article/details/6709693
 * 3. wait放在while里面使用
 *
 * notify()
 *  该方法唤醒在此对象监视器上等待的单个线程。
 *  如果有多个线程都在此对象上等待，则会随机选择唤醒其中一个线程，
 *  对其发出通知notify()，并使它等待获取该对象的对象锁。
 *  注意“等待获取该对象的对象锁”，这意味着，即使收到了通知，wait的线程也不会马上获取对象锁，
 *  必须等待notify()方法的线程释放锁才可以。和wait()一样，notify()也要在同步方法/同步代码块中调用。
 *
 *  wait 和 sleep的区别
 *  1.wait方法依赖于同步，而sleep方法可以直接调用
 *  2.sleep方法只是暂时让出CPU的执行权，并不释放锁。而wait方法则需要释放锁。
 *
 *
 * Created by myijoes on 2019/12/16.
 *
 * @author wanqiao
 */
public class ThreadApi_Wait {

    private String waitValue = "test";

    public static void main(String[] args) {
        ThreadApi_Wait wait = new ThreadApi_Wait();
        try {
            System.out.println("begin0");
            wait.useWait();
            System.out.println("begin1");
            Thread.sleep(2000);
            System.out.println("begin2");
            synchronized (wait.waitValue) {
                System.out.println("begin3");
                wait.waitValue.notify();
                System.out.println(".....123");
                //wait.waitValue.wait();
                System.out.println(".....");
                Thread.sleep(10000);
                System.out.println("00000000");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取锁
     */
    public void useWait() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    System.out.println("开始0");
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("开始1");
                    synchronized (waitValue) {
                        System.out.println("开始2");
                        Thread.sleep(5000);
                        System.out.println("开始3");
                        waitValue.wait();
                        System.out.println("等待结束0");
                    }
                    System.out.println("等待结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
