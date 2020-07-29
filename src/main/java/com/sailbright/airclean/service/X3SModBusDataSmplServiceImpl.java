package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.dao.DeviceMapper;
import com.sailbright.airclean.enums.DATA_TP;
import com.sailbright.airclean.enums.DEVICE_TP;
import com.sailbright.airclean.enums.IO;
import com.sailbright.airclean.util.Modbus4jUtils;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.locator.BaseLocator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * X3S设备数据采集 MODBUS
 */
@Service("X3SModBusDataSmplService")
public class X3SModBusDataSmplServiceImpl implements DataSmplService {

    @Resource
    private DeviceMapper deviceMapper;

    public void recordDatas(Device device) throws Exception {
        ModbusMaster master = Modbus4jUtils.getMaster(device.getIp(), device.getPort());
        BaseLocator<Number> loc = BaseLocator.holdingRegister(device.getSid(), 0, DataType.TWO_BYTE_INT_UNSIGNED);
        Number value = master.getValue(loc);
        List<DeviceSmplData> list = new ArrayList<DeviceSmplData>();
        DeviceSmplData pm25Data = new DeviceSmplData();
        pm25Data.setDataTp(DATA_TP.PM25.getCode());
        pm25Data.setDeviceNo(device.getNo());
        pm25Data.setDeviceTp(DEVICE_TP.X3S.getCode());
        pm25Data.setIo(IO.IN.getCode());
        pm25Data.setSmplTm(new Timestamp(System.currentTimeMillis()));
        pm25Data.setVal(value.toString());
        list.add(pm25Data);
    }

}
