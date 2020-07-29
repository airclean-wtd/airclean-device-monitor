package com.sailbright.airclean.dao;

import com.sailbright.airclean.bean.Device;
import com.sailbright.airclean.bean.DeviceSmplData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceSmplDataMapper {

    @Insert("insert into device_smpl_data(DEVICE_NO,DEVICE_MAC,DEVICE_TP,DATA_TP,VAL,IO,SMPL_MTHD,SMPL_TM) " +
            "values(#{deviceNo},#{deviceMac},#{deviceTp},#{dataTp},#{val},#{io},#{smplMthd},#{smplTm})")
    public void insertDeviceSmplData(DeviceSmplData dsd);

}
