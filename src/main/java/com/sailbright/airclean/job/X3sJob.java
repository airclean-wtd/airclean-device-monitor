package com.sailbright.airclean.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.dao.DeviceMapper;
import com.sailbright.airclean.enums.DEVICE_TP;
import com.sailbright.airclean.service.DataSmplService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class X3sJob implements SimpleJob {

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    @Qualifier("X3SModBusDataSmplService")
    private DataSmplService dataSmplService;

    @Override
    public void execute(ShardingContext shardingContext) {
        List<Device> devicelist = deviceMapper.getValidDevicesByTpShardingNo(DEVICE_TP.X3S.getCode(), shardingContext.getShardingItem());
        for(Device device : devicelist) {
            try {
                dataSmplService.recordDatas(device);
            }catch(Exception e) {
                log.error(e.getMessage(),e);
            }
        }

    }
}
