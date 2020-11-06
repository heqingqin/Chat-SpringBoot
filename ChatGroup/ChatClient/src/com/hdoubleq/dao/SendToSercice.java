package com.hdoubleq.dao;

import com.hdoubleq.utils.CloseUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:52
 */
public class SendToSercice implements Runnable {
    private BufferedReader console ;
    private DataOutputStream dos;
    private Socket client;
    private boolean isRunning;
    static String Msg="";

    public void Send(Socket client, String LoginorRegin,boolean isregin) throws InterruptedException {
        this.client =client;
        this.isRunning = true;
        try {
            dos =new DataOutputStream(client.getOutputStream());
            //发送名称
            send(LoginorRegin);
            if(isregin) {
                dos.close();
            }
        } catch (IOException e) {
            System.out.println("发送身份验证信息");
            this.release();
        }

    }

    //发送消息
    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            System.out.println("我发送的信息："+msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("发送信息失败");
            release();
        }finally {
            Msg = "";
        }
    }



    @Override
    public void run() {
        while(isRunning) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            if(!Msg.equals("")) {
                send(Msg);
            }
        }
    }
    //释放资源
    private void release() {
        this.isRunning = false;
        CloseUtils.close(dos,client);
    }

    public static void Msg(String content) {
        Msg = content;
    }
}
