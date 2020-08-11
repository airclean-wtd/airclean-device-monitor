package com.sailbright.airclean.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 特点： 读取配置文件。可以对静态变量直接赋值
 *
 * @author xuyangyang
 */
@Component
@ConfigurationProperties(prefix = "app.data.sftp")
@Data
public class SftpConfig {


    public static String ip;
    public static Integer port;
    public static String username;
    public static String password;
    public static String sourcePath;
    public static String targetPath;

    //注意这里是 static 修饰，便于sftputil直接取值
    public static String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        SftpConfig.ip = ip;
    }

    public static Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        SftpConfig.port = port;
    }

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        SftpConfig.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        SftpConfig.password = password;
    }

    public static String getSourcePath() {
        return sourcePath;
    }

    public static void setSourcePath(String sourcePath) {
        SftpConfig.sourcePath = sourcePath;
    }

    public static String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        SftpConfig.targetPath = targetPath;
    }
}