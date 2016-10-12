package thread;

/**
 * Created by ZMoffice on 2016/7/5.
 */
public class Thread1 extends Thread {

    //thread都有一个名称
//    public Thread1 (String name) {
//        super(name);
//    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
//            System.out.println(this.getName() + i);
            System.out.println(Thread.currentThread().getName() + ">>" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
