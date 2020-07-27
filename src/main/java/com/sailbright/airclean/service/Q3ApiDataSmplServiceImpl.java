package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.enums.DEVICE_TP;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Q3设备数据采集 API
 */
@Service("Q3ApiDataSmplService")
public class Q3ApiDataSmplServiceImpl extends AytApiDataSmplAbstractService implements DataSmplService {

    @Override
    public List<DeviceSmplData> getDatas(Device device) throws Exception {
        List<DeviceSmplData> list = super.getDatas(device);
        for(DeviceSmplData item : list) {
            item.setDeviceTp(DEVICE_TP.Q3.getCode());
        }
        return list;
    }

}
