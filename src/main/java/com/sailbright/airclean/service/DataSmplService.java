package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;

import java.util.List;

public interface DataSmplService {

    public void recordDatas(Device device) throws Exception;

}
