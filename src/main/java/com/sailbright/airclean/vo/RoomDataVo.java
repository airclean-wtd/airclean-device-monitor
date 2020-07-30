package com.sailbright.airclean.vo;

import java.io.Serializable;
import java.util.List;

public class RoomDataVo implements Serializable {

    private String roomNo;

    private String deviceNo;

    private String deviceMac;

    private List<dataVo> dataVoList;


    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    public List<dataVo> getDataVoList() {
        return dataVoList;
    }

    public void setDataVoList(List<dataVo> dataVoList) {
        this.dataVoList = dataVoList;
    }

    public class dataVo {

        private int dataTp;

        private int value;

        private String color;

        public int getDataTp() {
            return dataTp;
        }

        public void setDataTp(int dataTp) {
            this.dataTp = dataTp;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

    }

}
