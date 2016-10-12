package thread;

/**
 * Created by ZMoffice on 2016/7/5.
 */
public class TestThread {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        thread1.start();
        Runable1 run1 = new Runable1();
        Thread thread2 = new Thread(run1);
        thread2.start();
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ">>" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
