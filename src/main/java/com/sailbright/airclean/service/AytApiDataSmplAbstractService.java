package com.sailbright.airclean.service;

import com.alibaba.fastjson.JSON;
import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.config.AytApiConfig;
import com.sailbright.airclean.dao.DeviceRoomRltMapper;
import com.sailbright.airclean.enums.DATA_CONFIG;
import com.sailbright.airclean.enums.DATA_TP;
import com.sailbright.airclean.enums.IO;
import com.sailbright.airclean.enums.SMPL_MTHD;
import com.sailbright.airclean.util.FileUtil;
import com.sailbright.airclean.util.HttpClientUtil;
import com.sailbright.airclean.vo.AytApiDataVo;
import com.sailbright.airclean.vo.RoomDataVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爱优特设备数据采集 API
 */
@Slf4j
public abstract class AytApiDataSmplAbstractService extends AytProduceDataAbstractService {
    @Autowired
    private DeviceRoomRltMapper DeviceRoomRltMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${app.data-path}")
    private String dataPath;

    private RoomDataVo rdVo;

    public List<DeviceSmplData> getDatas(Device device) throws Exception {
        String url = AytApiConfig.API_URL + "/DeviceData";
        Map<String,String> params = new HashMap<String,String>();
        params.put("CompanyToken",AytApiConfig.API_TOKEN);
        params.put("DeviceMac",device.getMac());
        params.put("DataType","ALL");
        AytApiDataVo dataVo = HttpClientUtil.doGet(url, AytApiDataVo.class,params, "UTF-8");

        String roomNo = DeviceRoomRltMapper.getRoomByDeviceMac(device.getMac());
        RoomDataVo rdVo = new RoomDataVo();
        this.rdVo = rdVo;
        rdVo.setRoomNo(roomNo);
        List<RoomDataVo.dataVo> dvList = new ArrayList<RoomDataVo.dataVo>();
        rdVo.setDataVoList(dvList);

        List<DeviceSmplData> list = trans(device.getNo(), dataVo);

        File dataFile = new File(dataPath,roomNo+".json");
        FileUtil.writeJson(dataFile, rdVo);

        return list;
    }

    private List<DeviceSmplData> trans(String deviceNo, AytApiDataVo dataVo) {
        List<DeviceSmplData> list = new ArrayList<DeviceSmplData>();
        if(dataVo.getDeviceData()==null && dataVo.getDeviceData().size()==0) {
            return list;
        }
        AytApiDataVo.dataItem di = dataVo.getDeviceData().get(0);
        Timestamp uploadTm = di.getUploadTm();

        Long lastSmplTm = (Long)redisTemplate.opsForValue().get(di.getDeviceMac()+"^"+ SMPL_MTHD.API.getCode());
        if(lastSmplTm!=null) {
            if(uploadTm.getTime()<=lastSmplTm.longValue()) {
                return list;
            }
        }
        redisTemplate.opsForValue().set(di.getDeviceMac()+"^"+ SMPL_MTHD.API.getCode(), uploadTm.getTime());

        String co2 = di.getCo2();
        if(StringUtils.isNotBlank(co2)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.CO2.getCode());
            item.setVal(co2);
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        String hcho = di.getHcho();
        if(StringUtils.isNotBlank(hcho)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.HCHO.getCode());
            item.setVal(hcho);
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        String humidity = di.getHumidity();
        if(StringUtils.isNotBlank(humidity)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.HUMIDITY.getCode());
            item.setVal(hcho);
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        String pm25In = di.getPm25In();
        if(StringUtils.isNotBlank(pm25In)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.PM25.getCode());
            item.setVal(pm25In);
            item.setIo(IO.IN.getCode());
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);

            super.produceData(item, DATA_CONFIG.PM25_LEVEL, rdVo);
        }

        String pm25Out = di.getPm25Out();
        if(StringUtils.isNotBlank(pm25Out)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.PM25.getCode());
            item.setVal(pm25Out);
            item.setIo(IO.OUT.getCode());
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        String temperature = di.getTemperature();
        if(StringUtils.isNotBlank(temperature)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.TEMPERATURE.getCode());
            item.setVal(temperature);
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        String tvoc = di.getTvoc();
        if(StringUtils.isNotBlank(tvoc)) {
            DeviceSmplData item = new DeviceSmplData();
            item.setDataTp(DATA_TP.TVOC.getCode());
            item.setVal(tvoc);
            item.setSmplTm(uploadTm);
            item.setSmplMthd(SMPL_MTHD.API.getCode());
            item.setDeviceNo(deviceNo);
            item.setDeviceMac(di.getDeviceMac());
            list.add(item);
        }

        return list;
    }
}
