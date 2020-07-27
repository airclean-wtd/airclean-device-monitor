package com.sailbright.airclean.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class HttpClientUtil {

    @Resource
    private CloseableHttpClient httpClient;
    @Resource
    private RequestConfig requestConfig;

    /**
     * 通过给的url地址，获取服务器数据
     * @param url 服务器地址
     * @param params 封装用户参数
     * @param charset 设定字符编码
     * @return
     */
    public String doGet(String url, Map<String,String> params, String charset) {
        if (StringUtils.isEmpty(charset)) {
            charset = "utf-8";
        }
        //判断是否有参数
        if (params != null) {
            url +="?";
            for (Map.Entry<String,String> entry: params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                url += key +"="+value+"&";
            }
            url = url.substring(0,url.length() - 1);
        }
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        String result = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity(),charset);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }

    public String doGet(String url, Map<String,String> params) {
        return doGet(url,params,null);
    }
    public String doGet(String url) {
        return doGet(url,null,null);
    }

    public <T> T doGet(String url,Class<T> tClass,Map<String,String> map, String charSet){
        String result = doGet(url,map,charSet);
        return JSON.parseObject(result, tClass);
    }

}