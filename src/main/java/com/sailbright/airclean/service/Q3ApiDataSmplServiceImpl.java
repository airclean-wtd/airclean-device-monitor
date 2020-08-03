package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.dao.DeviceRoomRltMapper;
import com.sailbright.airclean.dao.DeviceSmplDataMapper;
import com.sailbright.airclean.enums.DEVICE_TP;
import com.sailbright.airclean.vo.RoomDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Q3设备数据采集 API
 */
@Service("Q3ApiDataSmplService")
public class Q3ApiDataSmplServiceImpl extends AytApiDataSmplAbstractService implements DataSmplService {

    @Autowired
    private DeviceSmplDataMapper deviceSmplDataMapper;

    @Override
    public void recordDatas(Device device) throws Exception {
        List<DeviceSmplData> list = super.getDatas(device);
        for(DeviceSmplData item : list) {
            item.setDeviceTp(DEVICE_TP.Q3.getCode());
            deviceSmplDataMapper.insertDeviceSmplData(item);
        }
    }

}
