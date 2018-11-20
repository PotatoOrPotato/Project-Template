package unmanned.supermarket.pay.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import unmanned.supermarket.pay.mapper.UserMapper;
import unmanned.supermarket.pay.service.TestService;
import org.springframework.stereotype.Service;

// 开启事物
@Transactional(readOnly = true)// 配置事物，查询使用只读，false用于增删改；
@Service
public class TestServiceImpl implements TestService {

    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    @Override
    public int run() {
        int num = userMapper.findUserInfoById("2");
        logger.info("个数:{}",num);
        return num;
    }
}
