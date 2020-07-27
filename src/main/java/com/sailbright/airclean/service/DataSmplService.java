package com.sailbright.airclean.service;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;

import java.util.List;

public interface DataSmplService {

    public List<DeviceSmplData> getDatas(Device device) throws Exception;

}
