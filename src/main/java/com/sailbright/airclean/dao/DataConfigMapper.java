package com.sailbright.airclean.dao;

import com.sailbright.airclean.bean.DataConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataConfigMapper {

    @Select("select * from data_config where ky=#{key} order by odr")
    public List<DataConfig> getDataConfigByKey(String key);

}
