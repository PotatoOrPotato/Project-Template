package unmanned.supermarket.pay.controller;

public class test {
    private static int num = 100;
    public static void main(String[] args) {
        test test = new test();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(num > 0){
                    synchronized (test){
                        if (num %2 != 0){
                            try {
                                test.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            System.out.println(Thread.currentThread().getName()+"票"+num);
                            num--;
                            test.notifyAll();
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    synchronized (test){
                        if (num %2 == 0){
                            try {
                                test.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else {
                            System.out.println(Thread.currentThread().getName()+"票"+num);
                            num--;
                            test.notifyAll();
                        }
                    }
                }
            }
        }).start();
    }
}