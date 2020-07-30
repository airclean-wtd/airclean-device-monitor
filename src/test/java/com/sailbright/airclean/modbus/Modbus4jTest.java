package com.sailbright.airclean.modbus;


import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.dao.DeviceMapper;
import com.sailbright.airclean.enums.DEVICE_TP;
import com.sailbright.airclean.service.DataSmplService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
@Slf4j
public class Modbus4jTest {

    private Logger logger = LoggerFactory.getLogger(Modbus4jTest.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    @Qualifier("X3SModBusDataSmplService")
    private DataSmplService dataSmplService;

    @Test
    public void test() {
        List<Device> devicelist = deviceMapper.getValidDevicesByTpShardingNo(DEVICE_TP.X3S.getCode(), 1);
        for(Device device : devicelist) {
            try {
                dataSmplService.recordDatas(device);
            }catch(Exception e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}