package com.hdoubleq.exception;

import java.util.concurrent.RecursiveTask;

/**
 * @author hdoubleq
 * @Date 2020/11/1-8:27
 */
public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("用户不存在！");
    }
}
