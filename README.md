# 群聊系统

#### 介绍
使用swing编写界客户端界面并使用socket进行数据传输、还实现了用户登录、注册、搜索功能、私聊等功能；服务端使用springboot来实现对用户的注册、用户信息管理、在线用户管理（对在线用户进行强制下线、禁言操作），使用CopyOnWriteArrayList来解决多线程并发问题，阻塞式等待用户连接并用Thread为先连接上来的用户开一个线程，用cookie来对保存管理员登录系统的账号密码，在HTML中用JavaScript取出cookie中保存的账号密码并赋值给输入框。


#### 安装教程

1、分别将ChatGroup和ChatService拉到IDE中
2、找到ChatService\sql中的user.sql，在数据库中运行生成user表
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151238_29363941_8278333.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151257_406930bb_8278333.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151508_e9b673c7_8278333.png "屏幕截图.png")
3、找到ChatService中的application.yml和com.hdoubleq.backstage.dao.DBUtils，将其中的数据库地址、用户名和密码换掉
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151109_37e45b07_8278333.png "屏幕截图.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151203_b4003e0c_8278333.png "屏幕截图.png")

#### 使用说明

1、先运行服务器端，即运行ChatService中的com\hdoubleq\ChatServiceApplication.java，运行程序操作如下：
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151634_4e547a0d_8278333.png "屏幕截图.png")
2、打开浏览器地址栏中访问http://localhost:8080/
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/151951_f35544fa_8278333.png "屏幕截图.png")
3、账号为admin，密码为123456
4、运行客户端：找到ChatGroup，运行com\hdoubleq\startMian.java
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/153209_3403d046_8278333.png "屏幕截图.png")
5、点击注册，进入注册页面，注册成功后会自动跳转回登录页面
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/153241_25faf4df_8278333.png "屏幕截图.png")
6、在登录页面中输入账号密码，进入聊天室
![输入图片说明](https://images.gitee.com/uploads/images/2020/1106/164436_8d72c1ec_8278333.png "屏幕截图.png")
