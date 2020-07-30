package com.sailbright.airclean.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeviceRoomRltMapper {

    @Select("select room_no from device_room_rlt where device_mac=#{mac} and end_tm IS NULL")
    public String getRoomByDeviceMac(String mac);

}
