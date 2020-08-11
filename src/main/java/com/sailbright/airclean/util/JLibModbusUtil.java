package com.sailbright.airclean.util;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import com.sailbright.airclean.vo.ModbusParamVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Slf4j
@Component
public class JLibModbusUtil {

    public static Integer readHoldingRegisters(ModbusParamVo vo) {
        try {
            // 设置主机TCP参数
            TcpParameters tcpParameters = new TcpParameters();

            // 设置TCP的ip地址
            InetAddress adress = InetAddress.getByName(vo.getHost());

            // TCP参数设置ip地址
            // tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setHost(adress);

            // TCP设置长连接
            tcpParameters.setKeepAlive(true);
            // TCP设置端口，这里设置是默认端口502
            tcpParameters.setPort(vo.getPort());

            // 创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);
            master.setResponseTimeout(5000);

            int slaveId = vo.getSlaveId();//从机地址
            int offset = vo.getOffset();//寄存器读取开始地址
            int quantity = vo.getQuantity();//读取的寄存器数量

            try {
                if (!master.isConnected()) {
                    master.connect();// 开启连接
                }

                int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
                return Integer.valueOf(registerValues[0]);

            } catch (ModbusProtocolException e) {
                log.error(e.getMessage(), e);
            } catch (ModbusNumberException e) {
                log.error(e.getMessage(), e);
            } catch (ModbusIOException e) {
                log.error(e.getMessage(), e);
            } finally {
                try {
                    master.disconnect();
                } catch (ModbusIOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
