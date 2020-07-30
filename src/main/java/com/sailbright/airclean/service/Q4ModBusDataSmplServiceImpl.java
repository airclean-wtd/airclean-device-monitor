package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.dao.DeviceSmplDataMapper;
import com.sailbright.airclean.enums.DEVICE_TP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Q4设备数据采集 MODBUS
 */
@Service("Q4ModBusDataSmplService")
public class Q4ModBusDataSmplServiceImpl extends AytModbusDataSmplAbstractService implements DataSmplService {

    @Autowired
    private DeviceSmplDataMapper deviceSmplDataMapper;

    public void recordDatas(Device device) throws Exception {
        List<DeviceSmplData> list = super.getDatas(device);
        for(DeviceSmplData item : list) {
            item.setDeviceTp(DEVICE_TP.Q4.getCode());
            item.setSmplTm(new Timestamp(System.currentTimeMillis()));
            deviceSmplDataMapper.insertDeviceSmplData(item);
        }
    }

}
