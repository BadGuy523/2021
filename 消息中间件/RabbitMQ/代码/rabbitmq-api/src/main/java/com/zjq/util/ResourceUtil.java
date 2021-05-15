package com.zjq.util;

import java.util.ResourceBundle;

/**
  * @Description: 配置文件读取工具类
  * @Author: zhangjunqiang
  * @Date: 2021/5/15 23:45
  * @version v1.0
  */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("config");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
