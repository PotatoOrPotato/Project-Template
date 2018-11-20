package unmanned.supermarket.pay.thread;

// Thread.currentThread().getName()

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class MyThread extends Thread{

    private static Logger log = LoggerFactory.getLogger(MyThread.class);

    private ThreadPoolTaskExecutor threadPool;

    public void setThreadPool(ThreadPoolTaskExecutor threadPool) {
        this.threadPool = threadPool;
    }

    public ThreadPoolTaskExecutor getThreadPool() {
        return threadPool;
    }

    public void run(){
        try {
            // 执行方法
            System.out.print("线程名字"+Thread.currentThread().getName());
            /*threadPool.execute();*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
