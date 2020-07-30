package com.sailbright.airclean.redis;


import com.sailbright.airclean.bean.DataConfig;
import com.sailbright.airclean.dao.DataConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DataConfigMapper dataConfigMapper;


    @Test
    public void test() {
        redisTemplate.opsForList().getOperations().delete("PM2.5_LEVEL");

        List<DataConfig> configList = dataConfigMapper.getDataConfigByKey("PM2.5_LEVEL");

        for(DataConfig dc : configList) {
            redisTemplate.opsForList().leftPush("PM2.5_LEVEL", dc.getVal()+"-"+dc.getName());
        }

        List list = redisTemplate.opsForList().range("PM2.5_LEVEL",0, -1);
        log.info("list长度：" + list.size());
    }
}