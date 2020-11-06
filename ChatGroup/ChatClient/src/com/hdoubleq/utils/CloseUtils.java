package com.hdoubleq.utils;

import java.io.Closeable;

/**
 * @author hdoubleq
 * @Date 2020/10/31-16:20
 */
public class CloseUtils {
    /**
     * 释放资源
     */
    public static void close(Closeable... targets ) {
        for(Closeable target:targets) {
            try {
                if(null!=target) {
                    target.close();
                }
            }catch(Exception e) {

            }
        }
    }
}
