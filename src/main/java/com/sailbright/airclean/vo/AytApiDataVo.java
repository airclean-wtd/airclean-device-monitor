package com.sailbright.airclean.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class AytApiDataVo implements Serializable {

    /**
     * 设备数据: [
     *          {
     *          设备地址: "FCEEE612DBE4",
     *          上传时间: "2019-09-06 09:30:07.0",
     *          检测PM2.5: "71",
     *          室外PM2.5: "18",
     *          甲醛: "",
     *          TVOC: "",
     *          温度: "28",
     *          CO2: "",
     *          湿度: "57"
     *          }
     *      ]
     */

    @JSONField(name="设备数据")
    private List<dataItem> deviceData;

    public class dataItem {
        @JSONField(name="设备地址")
        private String deviceMac;

        @JSONField(name="上传时间", format="yyyy-MM-dd HH:mm:ss")
        private Timestamp uploadTm;

        @JSONField(name="检测PM2.5")
        private String pm25In;

        @JSONField(name="室外PM2.5")
        private String pm25Out;

        @JSONField(name="甲醛:")
        private String hcho;

        @JSONField(name="TVOC:")
        private String tvoc;

        @JSONField(name="温度")
        private String temperature;

        @JSONField(name="CO2")
        private String co2;

        @JSONField(name="湿度")
        private String humidity;

        public String getDeviceMac() {
            return deviceMac;
        }

        public void setDeviceMac(String deviceMac) {
            this.deviceMac = deviceMac;
        }

        public Timestamp getUploadTm() {
            return uploadTm;
        }

        public void setUploadTm(Timestamp uploadTm) {
            this.uploadTm = uploadTm;
        }

        public String getPm25In() {
            return pm25In;
        }

        public void setPm25In(String pm25In) {
            this.pm25In = pm25In;
        }

        public String getPm25Out() {
            return pm25Out;
        }

        public void setPm25Out(String pm25Out) {
            this.pm25Out = pm25Out;
        }

        public String getHcho() {
            return hcho;
        }

        public void setHcho(String hcho) {
            this.hcho = hcho;
        }

        public String getTvoc() {
            return tvoc;
        }

        public void setTvoc(String tvoc) {
            this.tvoc = tvoc;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getCo2() {
            return co2;
        }

        public void setCo2(String co2) {
            this.co2 = co2;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }

    public List<dataItem> getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(List<dataItem> deviceData) {
        this.deviceData = deviceData;
    }
}
