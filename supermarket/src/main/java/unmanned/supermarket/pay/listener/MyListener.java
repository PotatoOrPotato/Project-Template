package unmanned.supermarket.pay.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(MyListener.class);

    //监听服务的开启动
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("服务启动");
    }

    // 监听服务的销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("服务结束");
    }
}
