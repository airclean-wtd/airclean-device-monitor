package com.sailbright.airclean.vo;

public class ModbusParamVo {

    private String host;
    private int port;
    private int slaveId;
    private int offset;
    private int quantity;

    public ModbusParamVo(String host, int port, int slaveId, int offset, int quantity) {
        this.host = host;
        this.port = port;
        this.slaveId = slaveId;
        this.offset = offset;
        this.quantity = quantity;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSlaveId() {
        return slaveId;
    }

    public void setSlaveId(int slaveId) {
        this.slaveId = slaveId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
