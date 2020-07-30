package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.DeviceSmplData;
import com.sailbright.airclean.enums.DATA_CONFIG;
import com.sailbright.airclean.enums.DATA_TP;
import com.sailbright.airclean.vo.RoomDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Slf4j
public abstract class AytProduceDataAbstractService {

    @Autowired
    private RedisTemplate redisTemplate;

    public void produceData(DeviceSmplData item, DATA_CONFIG dc, RoomDataVo rdVo) {
        int v = Integer.parseInt(item.getVal());
        List<String> ranges = redisTemplate.opsForList().range(dc.getText(),0, -1);
        for(int i=ranges.size()-1;i>=0;i--) {String r = ranges.get(i);
            int down = Integer.parseInt(r.split("-")[0]);
            int up = Integer.parseInt(r.split("-")[1]);
            String color = r.split("-")[2];
            if(up>=v && v>=down) {
                rdVo.setDeviceMac(item.getDeviceMac());
                rdVo.setDeviceNo(item.getDeviceNo());
                RoomDataVo.dataVo dv = rdVo.new dataVo();
                dv.setColor(color);
                dv.setDataTp(DATA_TP.PM25.getCode());
                dv.setValue(v);
                rdVo.getDataVoList().add(dv);
                break;
            }
        }
    }

}
