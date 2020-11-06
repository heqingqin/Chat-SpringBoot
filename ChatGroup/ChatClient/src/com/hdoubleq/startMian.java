package com.hdoubleq;

import com.hdoubleq.view.MainView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author hdoubleq
 * @Date 2020/10/31-14:56
 */
public class startMian {
    static ApplicationContext applicationContext;
    public static MainView mainView;
    public static void main(String[] args) {
        //开启界面
        applicationContext= new ClassPathXmlApplicationContext("spring-config.xml");
        mainView = applicationContext.getBean("mainView", MainView.class);
        mainView.setUndecorated(true);
        mainView.setVisible(true);
    }

}
