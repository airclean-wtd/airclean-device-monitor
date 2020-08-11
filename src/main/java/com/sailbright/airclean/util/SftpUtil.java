package com.sailbright.airclean.util;

import com.jcraft.jsch.*;
import com.sailbright.airclean.config.SftpConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * SFTP工具类
 */
@Slf4j
@Component
public class SftpUtil {

    @Autowired
    SftpConfig sftpConfig;

    private static String ip = SftpConfig.getIp();
    private static Integer port = SftpConfig.getPort();
    private static String username = SftpConfig.getUsername();
    private static String password = SftpConfig.getPassword();
    private static String targetPath = SftpConfig.getTargetPath();


    private static Session createSession() throws JSchException {
        //场景JSch对象
        JSch jSch = new JSch();
        // jsch.addIdentity(); 私钥
        Session session = jSch.getSession(username, ip, port);

        // 第一次登陆时候提示, (ask|yes|no)
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        config.put("compression.s2c", "zlib,none");
        config.put("compression.c2s", "zlib,none");
        session.setConfig(config);
        //设置超时
//            session.setTimeout(10*1000);
        //设置密码
        session.setPassword(password);
        session.connect();
        return session;
    }

    /**
     * @param uploadFile 上传文件
     * @throws Exception
     */
    public static void upload(File uploadFile) throws Exception {
        Session session = null;
        ChannelSftp channel = null;
        FileInputStream fileInputStream = null;
        try {
            session = createSession();
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            if (channel == null) {
                log.error("获取sftp连接失败，请检查" + ip + +port + "@" + username + "" + password + "是否可以访问");
            }
            channel.cd(targetPath);
            fileInputStream = new FileInputStream(uploadFile);
            channel.put(fileInputStream, uploadFile.getName());
        }catch(Exception e) {
            log.error(e.getMessage(),e);
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
            if (null != channel) {
                try {
                    channel.disconnect();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (null != session) {
                try {
                    session.disconnect();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

}