package unmanned.supermarket.pay.thread;

public class MyRunnable implements Runnable {
    private int count = 100;
    @Override
    public  void run() {
        while(true){
            synchronized(new Object()){
                if (count > 0){
                    System.out.println("线程"+Thread.currentThread().getName()+"在卖第"+count--+"张漂");
                }
            }
        }
    }
}
