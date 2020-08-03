package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.dao.DeviceRoomRltMapper;
import com.sailbright.airclean.enums.*;
import com.sailbright.airclean.util.FileUtil;
import com.sailbright.airclean.util.JLibModbusUtil;
import com.sailbright.airclean.vo.ModbusParamVo;
import com.sailbright.airclean.vo.RoomDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 爱优特设备数据采集 API
 */
@Slf4j
public abstract class AytModbusDataSmplAbstractService extends AytProduceDataAbstractService {

    @Autowired
    private com.sailbright.airclean.dao.DeviceRoomRltMapper DeviceRoomRltMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${app.data-path}")
    private String dataPath;

    private Device device;
    private RoomDataVo rdVo;

    public List<DeviceSmplData> getDatas(Device device) throws Exception {
        List<DeviceSmplData> list = new ArrayList<DeviceSmplData>();

        this.device = device;

        Long lastSmplTm = (Long)redisTemplate.opsForValue().get(device.getMac()+"^"+ SMPL_MTHD.MB.getCode());
        if(lastSmplTm!=null) {
            if(System.currentTimeMillis()<=lastSmplTm.longValue()) {
                return list;
            }
        }
        redisTemplate.opsForValue().set(device.getMac()+"^"+ SMPL_MTHD.MB.getCode(), System.currentTimeMillis());

        String roomNo = DeviceRoomRltMapper.getRoomByDeviceMac(device.getMac());
        RoomDataVo rdVo = new RoomDataVo();
        this.rdVo = rdVo;
        rdVo.setRoomNo(roomNo);
        List<RoomDataVo.dataVo> dvList = new ArrayList<RoomDataVo.dataVo>();
        rdVo.setDataVoList(dvList);

        try {
            DeviceSmplData pm25Data = this.getPm25Data();
            list.add(pm25Data);
            super.produceData(pm25Data, DATA_CONFIG.PM25_LEVEL, rdVo);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        try {
            DeviceSmplData temperatureData = this.getTemperatureData();
            list.add(temperatureData);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        try {
            DeviceSmplData humidityData = this.getHumidityData();
            list.add(humidityData);
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        try {
            DeviceSmplData pm25OutData = this.getPm25OutData();
            if(pm25OutData!=null) {
                list.add(pm25OutData);
            }
        }catch (Exception e) {
            log.error(e.getMessage(),e);
        }

        File dataFile = new File(dataPath, roomNo+".json");
        FileUtil.writeJson(dataFile, rdVo);

        return list;
    }

    private DeviceSmplData getPm25Data() throws Exception {
        ModbusParamVo vo = new ModbusParamVo(device.getIp(),device.getPort(),device.getSid(),MB_REG.PM25.getCode(),1);
        Integer value = JLibModbusUtil.readHoldingRegisters(vo).intValue();
        DeviceSmplData item = new DeviceSmplData();
        item.setDataTp(DATA_TP.PM25.getCode());
        item.setDeviceNo(device.getNo());
        item.setDeviceMac(device.getMac());
        item.setSmplMthd(SMPL_MTHD.MB.getCode());
        item.setIo(IO.IN.getCode());
        item.setVal(value+"");
        return item;
    }

    private DeviceSmplData getTemperatureData() throws Exception {
        ModbusParamVo vo = new ModbusParamVo(device.getIp(),device.getPort(),device.getSid(),MB_REG.TEMPERATURE.getCode(),1);
        Integer value = JLibModbusUtil.readHoldingRegisters(vo).intValue();
        DeviceSmplData item = new DeviceSmplData();
        item.setDataTp(DATA_TP.TEMPERATURE.getCode());
        item.setDeviceNo(device.getNo());
        item.setDeviceMac(device.getMac());
        item.setSmplMthd(SMPL_MTHD.MB.getCode());
        String valStr = value.toString();
        String v = valStr.substring(0, 2) + "." + valStr.substring(2, 3);
        item.setVal(v);
        return item;
    }

    private DeviceSmplData getHumidityData() throws Exception {
        ModbusParamVo vo = new ModbusParamVo(device.getIp(),device.getPort(),device.getSid(),MB_REG.HUMIDITY.getCode(),1);
        Integer value = JLibModbusUtil.readHoldingRegisters(vo).intValue();
        DeviceSmplData item = new DeviceSmplData();
        item.setDataTp(DATA_TP.HUMIDITY.getCode());
        item.setDeviceNo(device.getNo());
        item.setDeviceMac(device.getMac());
        item.setSmplMthd(SMPL_MTHD.MB.getCode());
        String v = String.format("%.1f",value.floatValue()/10);
        item.setVal(v);
        return item;
    }

    private DeviceSmplData getPm25OutData() throws Exception {
        ModbusParamVo vo = new ModbusParamVo(device.getIp(),device.getPort(),device.getSid(),MB_REG.PM25_OUT.getCode(),1);
        Integer value = JLibModbusUtil.readHoldingRegisters(vo);
        if(value==null || value.intValue()==0) {
            return null;
        }
        DeviceSmplData item = new DeviceSmplData();
        item.setDataTp(DATA_TP.PM25.getCode());
        item.setDeviceNo(device.getNo());
        item.setDeviceMac(device.getMac());
        item.setSmplMthd(SMPL_MTHD.MB.getCode());
        item.setVal(value.toString());
        item.setIo(IO.OUT.getCode());
        return item;
    }

}
