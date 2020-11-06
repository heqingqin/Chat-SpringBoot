package com.hdoubleq.backstage.dao;

import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.list.UserList;
import jdk.nashorn.internal.objects.annotations.Where;
import org.codehaus.groovy.transform.sc.transformers.RangeExpressionTransformer;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * @author hdoubleq
 * @Date 2020/11/4-10:53
 */
public class DBUtils {
    private static String USERNAME = "root";
    // 定义数据库的密码
    private static String PASSWORD = "hdoubleq";
    // 定义数据库的驱动信息
    private static String DRIVER = "com.mysql.jdbc.Driver";
    // 定义访问数据库的地址
    private static String URL = "jdbc:mysql://localhost:3306/eesy";

    private static DBUtils per = null;
    // 定义数据库的链接
    private Connection con = null;
    // 定义sql语句的执行对象
    private PreparedStatement pstmt = null;


    private DBUtils() {

    }

    /**
     * 单例模式，获得工具类的一个对象
     *
     * @return
     */
    public static DBUtils getInstance() {
        if (per == null) {
            per = new DBUtils();
            per.registeredDriver();
        }
        return per;
    }

    private void registeredDriver() {
        // TODO Auto-generated method stub
        try {
            Class.forName(DRIVER);
            System.out.println("注册驱动成功！");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获得数据库的连接
     *
     * @return
     */
    public Connection getConnection() {
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("连接数据库成功!!");
        return con;
    }

    /**
     * 完成对数据库的表的添加删除和修改的操作
     *
     * @return
     * @throws SQLException
     */
    public int saveClient(User user) throws SQLException {
        String acc = user.getAcc();
        String name = user.getName();
        String pwd = user.getPwd();
        String who = user.getWho();
        pstmt=getConnection().prepareStatement("INSERT INTO User(acc,name,pwd,who) VALUES(?,?,?,?)");
        if (user != null) {
            pstmt.setString(1,acc);
            pstmt.setString(2,name);
            pstmt.setString(3,pwd);
            pstmt.setString(4,who);
        }
        int i = pstmt.executeUpdate();
        pstmt= getConnection().prepareStatement("select * from User where acc=?");
        pstmt.setString(1,acc);
        if (i==1){
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet);
                UserList.getList().add(new User( resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)));
            }
        }

        return i;
    }
}
