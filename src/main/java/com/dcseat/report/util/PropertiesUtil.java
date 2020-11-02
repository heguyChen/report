package com.dcseat.report.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 项目application.properties配置文件读取类
 */
public class PropertiesUtil {

    private static Logger log =  LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties p;

    static {
        loadProperty();
    }

    synchronized static private void loadProperty() {
        p = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
            p.load(in);
        } catch (Exception e) {
            log.error("配置加载异常, {}", e.getMessage());
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException ioe) {
                log.error("关闭输入流异常, {}", ioe.getMessage());
            }
        }
    }

    public static String getProperty(String key) {
        if (null == p) {
            loadProperty();
        }
        return p.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if (null == p) {
            loadProperty();
        }
        return p.getProperty(key, defaultValue);
    }
}
