package com.sailbright.airclean.dao;

import com.sailbright.airclean.bean.Device;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeviceMapper {

    @Select("select * from device where no=#{deviceNo,jdbcType=VARCHAR}")
    public Device loadDevice(String deviceNo);

    @Select("select * from device where DEL=0 and TP=#{tp} and RIGHT(NO,1)=#{shardingNo}")
    public List<Device> getValidDevicesByTpShardingNo(String tp, int shardingNo);

}
