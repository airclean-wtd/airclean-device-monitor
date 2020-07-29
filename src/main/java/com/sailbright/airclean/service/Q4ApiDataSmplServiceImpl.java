package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.enums.DEVICE_TP;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Q4 设备数据采集 API
 */
@Service("Q4ApiDataSmplService")
public class Q4ApiDataSmplServiceImpl extends AytApiDataSmplAbstractService implements DataSmplService {

    @Override
    public void recordDatas(Device device) throws Exception {
        List<DeviceSmplData> list = super.getDatas(device);
        for(DeviceSmplData item : list) {
            item.setDeviceTp(DEVICE_TP.Q4.getCode());
        }
    }

}
