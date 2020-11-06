package com.hdoubleq.view;

import com.hdoubleq.dao.SendToSercice;
import com.hdoubleq.model.CustomerListModel;
import com.hdoubleq.view.Service.ViewService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;



public class MainView extends JFrame{
    static ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring-config.xml");
    static ViewService viewService;
    static {
        viewService = applicationContext.getBean("viewService", ViewService.class);
    }
    static String username="";
    static JPanel contentPane;
    static JPanel contentPanelogin;
    static JPanel contentPaneregin;
    private TableColumn column;
    JPanel meunPanel;
    JPanel chattalknamepanel;
    JPanel syspanel;
    JPanel chatgrappanel;
    JPanel mainpanel;
    JPanel talkmainpanel;
    JPanel showtalkpanel;
    JPanel inputpanel;
    JPanel inputtxtpanel;
    JPanel inputbutpanel;
    static JPanel login;
    JLabel chattalknamelabel;
    JLabel hidden;
    JLabel bigorsmall;
    JLabel close;
    JLabel chatLabel;
    JLabel inputLabel;
    JLabel uname;
    JLabel acc;
    JLabel upass;
    JLabel upassagain;
    static JLabel tip;
    JButton sendbut;
    JButton clearbut;
    JButton backlogin;
    JButton searchbut;
    JButton newnamebut;
    JButton loginbut;
    JButton okbut;
    static JTable table;
    static JTextArea inputcontent = new JTextArea(4,100);
    JScrollPane contentScroll;
    static JTextArea showcontent = new JTextArea(10,100);;
    static JPasswordField password;
    static JPasswordField repassword;
    static JPasswordField passwordagain;
    static TextField tf_name;
    static JTextField searchfield;
    static TextField retf_name;
    static TextField retf_acc;
    JScrollPane showScroll;
    JScrollPane listjs;
    public static String message ="";
    boolean IS = true;
    public static boolean islogin = false;
    public static boolean isregin = false;
    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置日期格式
    public MainView(){
        ReSizeEvent dg = new ReSizeEvent(this);        /**添加两个监听器**/
//        鼠标监听
        this.addMouseListener(dg);
        this.addMouseMotionListener(dg);
        setBounds(160,100,550,350);    //设置窗口大小
        Toolkit tool=this.getToolkit();
        Image im=tool.getImage(Frame.class.getResource("/images/chat.png"));
        this.setIconImage(im);
//        键盘监听
        keyListner();
//        登录界面面板
        contentPanelogin = new JPanel();
        setContentPane(contentPanelogin);
        contentPanelogin.setLayout(new BorderLayout(0, 0));
        contentPanelogin.setBackground(null);
        contentPanelogin.setOpaque(false);


        //菜单栏
        meunPanel = new JPanel();
        meunPanel.setBackground(new Color(51,51,204)); // 设置窗体背景颜色
        contentPanelogin.add(meunPanel,BorderLayout.NORTH);
        meunPanel.setPreferredSize(new Dimension(846, 63));//关键代码,设置JPanel的大小
        meunPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        meunPanel.setLayout(new BorderLayout(10, 10));

        chattalknamepanel = new JPanel();
        meunPanel.add(chattalknamepanel,BorderLayout.CENTER);
        chattalknamepanel.setBackground(null);
        chattalknamepanel.setOpaque(false);
        chattalknamepanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        chattalknamelabel = new JLabel("登录页面");
        chattalknamepanel.add(chattalknamelabel);
        chattalknamelabel.setForeground(Color.WHITE);
        chattalknamelabel.setFont(new java.awt.Font("微软雅黑", 1, 25));
        //系统控制按钮
        syspanel = new JPanel();
        meunPanel.add(syspanel,BorderLayout.EAST);
        syspanel.setBackground(null);
        syspanel.setOpaque(false);
        syspanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));



//        最小化
        hidden = new JLabel("");
        syspanel.add(hidden);
        hidden.setToolTipText("最小化");
        hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/hidden.png")));
        hidden.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    viewService.MinWindow();
                }
            }public void mouseEntered(MouseEvent e) {
                hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/selecthidden.png")));
            } public void mouseExited(MouseEvent e) {
                hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/hidden.png")));
            }});

        close = new JLabel("");
        syspanel.add(close);
        close.setToolTipText("关闭");
        close.setIcon(new ImageIcon(MainView.class.getResource("/images/close.png")));
        close.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    viewService .exit();
                }
            }public void mouseEntered(MouseEvent e) {
                close.setIcon(new ImageIcon(MainView.class.getResource("/images/selectclose.png")));
            } public void mouseExited(MouseEvent e) {
                close.setIcon(new ImageIcon(MainView.class.getResource("/images/close.png")));
            }});


        login=new JPanel(new GridLayout(7,1));//生成一个新的版面
        contentPanelogin.add(login,BorderLayout.SOUTH);

        JLabel Label = new JLabel("欢迎登录聊天室",JLabel.CENTER);
        login.add(Label);
        Label.setForeground(Color.gray);
        Label.setFont(new java.awt.Font("微软雅黑", 1, 20));
        Label.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

        JPanel pan1 = new JPanel();
        login.add(pan1);
        pan1.setBackground(new Color(255,255,255)); // 设置窗体背景颜色


        JPanel pan2=new JPanel();//生成一个新的版面
        login.add(pan2);
        pan2.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        uname=new JLabel("账     号:");
        pan2.add(uname);
        uname.setForeground(Color.black);
        uname.setFont(new java.awt.Font("微软雅黑", 1, 15));
        tf_name=new TextField(20);
        tf_name.setText("");
        pan2.add(tf_name);
        tf_name.setForeground(Color.black);
        tf_name.setFont(new java.awt.Font("宋体", 1, 15));


        JPanel pan3=new JPanel();//生成一个新的版面
        login.add(pan3);
        upass = new JLabel("密     码:");
        pan3.add(upass);
        pan3.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        upass.setForeground(Color.black);
        upass.setFont(new java.awt.Font("微软雅黑", 1, 15));
        password=new JPasswordField(20);
        password.setEchoChar('*');
        pan3.add(password);
        password.setForeground(Color.black);
        password.setFont(new java.awt.Font("宋体", 1, 15));


        JPanel pan4 = new JPanel();
        login.add(pan4);
        pan4.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        pan4.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        newnamebut=new JButton("注册");
        pan4.add(newnamebut);
        newnamebut.setFocusPainted(false);
        newnamebut.setToolTipText("注册");
        newnamebut.setBackground(Color.WHITE);
        newnamebut.setForeground(Color.black);
        newnamebut.setFont(new java.awt.Font("微软雅黑", 1, 15));
        newnamebut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    isregin = true;
                    islogin = false;
                    login.setVisible(false);
                    Regin();
                    chattalknamelabel.setText("注册页面");
                }
            }
            public void mouseEntered(MouseEvent e) {
                newnamebut.setBackground(Color.red);
            }
            public void mouseExited(MouseEvent e) {
                newnamebut.setBackground(Color.WHITE);
            }});

        JButton jButton = new JButton("   ");
        pan4.add(jButton);
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setFocusPainted(false);
        loginbut=new JButton("登陆");
        pan4.add(loginbut);
        loginbut.setFocusPainted(false);
        loginbut.setToolTipText("登陆");
        loginbut.setBackground(Color.green);
        loginbut.setForeground(Color.black);
        loginbut.setFont(new java.awt.Font("微软雅黑", 1, 15));
        loginbut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    try {
                        username = tf_name.getText();
                        tip.setText("登录中....");
                        if(!username.equals("") && !new String(password.getPassword()).equals("")) {
                            viewService.Login(username,new String(password.getPassword()));
                        }else tip.setText("账号或密码不能为空！");
                        password.setText("");
                        tf_name.setText("");
                        Thread.sleep(100);
                        if(islogin) {
                            contentPanelogin.setVisible(false);
                            GUIini();
                        }
                    } catch (Exception e1) {
                        tip.setText("连接服务器失败！");
                        islogin = false;
                    }
                }
            }public void mouseEntered(MouseEvent e) {
                loginbut.setBackground(Color.red);
            } public void mouseExited(MouseEvent e) {
                loginbut.setBackground(Color.green);
            }});


        JPanel pan5 = new JPanel();
        login.add(pan5);
        pan5.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

        JPanel pan6 = new JPanel(new BorderLayout(0,0));
        login.add(pan6);
        tip = new JLabel("",JLabel.CENTER);
        pan6.add(tip,BorderLayout.CENTER);
        tip.setForeground(Color.black);
        tip.setFont(new java.awt.Font("微软雅黑", 1, 25));
        pan6.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
    }

    //注册
    protected void Regin() {
        contentPaneregin=new JPanel(new GridLayout(8,1));//生成一个新的版面
        contentPanelogin.add(contentPaneregin,BorderLayout.SOUTH);

        JLabel Label = new JLabel("您已进入注册页面",JLabel.CENTER);
        contentPaneregin.add(Label);
        Label.setForeground(Color.gray);
        Label.setFont(new java.awt.Font("微软雅黑", 1, 20));
        Label.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

        JPanel pan11 = new JPanel();
        contentPaneregin.add(pan11);
        pan11.setBackground(new Color(255,255,255)); // 设置窗体背景颜色


        JPanel pan0=new JPanel();//生成一个新的版面
        contentPaneregin.add(pan0);
        pan0.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        acc=new JLabel("请 输 入 账 号:");
        pan0.add(acc);
        acc.setForeground(Color.black);
        acc.setFont(new java.awt.Font("微软雅黑", 1, 15));
        retf_acc=new TextField(20);
        retf_acc.setText("");
        pan0.add(retf_acc);
        retf_acc.setForeground(Color.black);
        retf_acc.setFont(new java.awt.Font("宋体", 1, 15));


        JPanel pan21=new JPanel();//生成一个新的版面
        contentPaneregin.add(pan21);
        pan21.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        uname=new JLabel("用      户      名:");
        pan21.add(uname);
        uname.setForeground(Color.black);
        uname.setFont(new java.awt.Font("微软雅黑", 1, 15));
        retf_name=new TextField(20);
        retf_name.setText("");
        pan21.add(retf_name);
        retf_name.setForeground(Color.black);
        retf_name.setFont(new java.awt.Font("宋体", 1, 15));


        JPanel pan31=new JPanel();//生成一个新的版面
        contentPaneregin.add(pan31);
        upass = new JLabel("请 输 入 密 码:");
        pan31.add(upass);
        pan31.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        upass.setForeground(Color.black);
        upass.setFont(new java.awt.Font("微软雅黑", 1, 15));
        repassword=new JPasswordField(20);
        repassword.setEchoChar('*');
        pan31.add(repassword);
        repassword.setForeground(Color.black);
        repassword.setFont(new java.awt.Font("宋体", 1, 15));

        JPanel pan51 = new JPanel();
        contentPaneregin.add(pan51);
        upassagain = new JLabel("请再输入密码:");
        pan51.add(upassagain);
        pan51.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        upassagain.setForeground(Color.black);
        upassagain.setFont(new java.awt.Font("微软雅黑", 1, 15));
        passwordagain = new JPasswordField(20);
        passwordagain.setEchoChar('*');
        pan51.add(passwordagain);
        passwordagain.setForeground(Color.black);
        passwordagain.setFont(new java.awt.Font("宋体", 1, 15));



        JPanel pan41 = new JPanel();
        contentPaneregin.add(pan41);
        pan41.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        pan41.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        backlogin=new JButton("返回");
        pan41.add(backlogin);
        backlogin.setFocusPainted(false);
        backlogin.setToolTipText("返回");
        backlogin.setBackground(Color.WHITE);
        backlogin.setForeground(Color.black);
        backlogin.setFont(new java.awt.Font("微软雅黑", 1, 15));
        backlogin.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    System.out.println("这是登录页面");
                    //返回登录界面
                    retf_acc.setText("");
                    retf_name.setText("");
                    repassword.setText("");
                    passwordagain.setText("");
                    contentPaneregin.setVisible(false);
                    login.setVisible(true);
                    isregin = false;
                    chattalknamelabel.setText("登录页面");
                }
            }public void mouseEntered(MouseEvent e) {
                backlogin.setBackground(Color.red);
            } public void mouseExited(MouseEvent e) {
                backlogin.setBackground(Color.WHITE);
            }});

        JButton jButton = new JButton("   ");
        pan41.add(jButton);
        jButton.setFocusPainted(false);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setFocusPainted(false);
        okbut=new JButton("提交");
        pan41.add(okbut);
        okbut.setFocusPainted(false);
        okbut.setToolTipText("提交");
        okbut.setBackground(Color.green);
        okbut.setForeground(Color.black);
        okbut.setFont(new java.awt.Font("微软雅黑", 1, 15));
        okbut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    //将密码发送到服务器
                    tip.setText("注册中...");
                    String repwd = new String(repassword.getPassword());
                    String repwdagain = new String(passwordagain.getPassword());
                    if(!retf_acc.getText().equals("")&&!repwd.equals("")&&repwd.equals(repwdagain)) {
                        try {
                            viewService.Regin(retf_acc.getText(),retf_name.getText(),repwd);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }
                            contentPaneregin.setVisible(false);
                            login.setVisible(true);
                            isregin = false;
                            chattalknamelabel.setText("登录页面");
                            System.out.println("注册完成");
                        } catch (Exception e1) {
                            System.out.println("注册出现异常");
                            e1.printStackTrace();
                        }
                    }else {
                        tip.setText("账号或密码输入不符合要求！");
                    }
                    retf_acc.setText("");
                    retf_name.setText("");
                    repassword.setText("");
                    passwordagain.setText("");
                }
            }
            public void mouseEntered(MouseEvent e) {
                okbut.setBackground(Color.red);
            }
            public void mouseExited(MouseEvent e) {
                okbut.setBackground(Color.green);
            }});



        JPanel pan61 = new JPanel(new BorderLayout(0,0));
        contentPaneregin.add(pan61);
        tip = new JLabel("",JLabel.CENTER);
        pan61.add(tip,BorderLayout.CENTER);
        tip.setForeground(Color.black);
        tip.setFont(new java.awt.Font("微软雅黑", 1, 25));
        pan61.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

    }

    //主页面
    public void GUIini(){
        setTitle("聊天软件");//软件名
        setBounds(160,100,845,850);    //设置窗口大小
        this.setMinimumSize(new Dimension(510, 500));
        Toolkit tool=this.getToolkit();
        Image im=tool.getImage(Frame.class.getResource("/images/chat.png"));
        this.setVisible(true);
        this.setIconImage(im);
        contentPane=new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(null);
        contentPane.setOpaque(false);

        //菜单栏
        meunPanel = new JPanel();
        meunPanel.setBackground(new Color(51,51,204)); // 设置窗体背景颜色
        contentPane.add(meunPanel,BorderLayout.NORTH);
        meunPanel.setPreferredSize(new Dimension(846, 63));//关键代码,设置JPanel的大小
        meunPanel.setBorder(new EmptyBorder(0, 5, 0, 0));
        meunPanel.setLayout(new BorderLayout(10, 10));

        //群聊名字
        chattalknamepanel = new JPanel();
        meunPanel.add(chattalknamepanel,BorderLayout.CENTER);
        chattalknamepanel.setBackground(null);
        chattalknamepanel.setOpaque(false);
        chattalknamepanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        chattalknamelabel = new JLabel("我的群聊");
        chattalknamepanel.add(chattalknamelabel);
        chattalknamelabel.setForeground(Color.WHITE);
        chattalknamelabel.setFont(new java.awt.Font("微软雅黑", 3, 35));

        //系统控制按钮
        syspanel = new JPanel();
        meunPanel.add(syspanel,BorderLayout.EAST);
        syspanel.setBackground(null);
        syspanel.setOpaque(false);
        syspanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));

        hidden = new JLabel("");
        syspanel.add(hidden);
        hidden.setToolTipText("最小化");
        hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/hidden.png")));
        hidden.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    viewService.MinWindow();
                }
            }public void mouseEntered(MouseEvent e) {
                hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/selecthidden.png")));
            } public void mouseExited(MouseEvent e) {
                hidden.setIcon(new ImageIcon(MainView.class.getResource("/images/hidden.png")));
            }});

        bigorsmall = new JLabel("");
        syspanel.add(bigorsmall);
        bigorsmall.setToolTipText("最大化");
        bigorsmall.setIcon(new ImageIcon(MainView.class.getResource("/images/changebig.png")));
        bigorsmall.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                viewService.PanelFullScree(IS);
                IS = !IS;
            }public void mouseEntered(MouseEvent e) {
                if(IS) {
                    bigorsmall.setIcon(new ImageIcon(MainView.class.getResource("/images/selectbig.png")));
                }else {
                    bigorsmall.setIcon(new ImageIcon(MainView.class.getResource("/images/selectsmall.png")));
                }
            } public void mouseExited(MouseEvent e) {
                if(IS) {
                    bigorsmall.setIcon(new ImageIcon(MainView.class.getResource("/images/changebig.png")));
                    bigorsmall.setToolTipText("最大化");
                }else {
                    bigorsmall.setIcon(new ImageIcon(MainView.class.getResource("/images/changesmall.png")));
                    bigorsmall.setToolTipText("最小化");
                }
            }});

        close = new JLabel("");
        syspanel.add(close);
        close.setToolTipText("关闭");
        close.setIcon(new ImageIcon(MainView.class.getResource("/images/close.png")));
        close.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    viewService .exit();
                }
            }public void mouseEntered(MouseEvent e) {
                close.setIcon(new ImageIcon(MainView.class.getResource("/images/selectclose.png")));
            } public void mouseExited(MouseEvent e) {
                close.setIcon(new ImageIcon(MainView.class.getResource("/images/close.png")));
            }});

        //主板面
        mainpanel = new JPanel();
        contentPane.add(mainpanel,BorderLayout.CENTER);
        mainpanel.setLayout(new BorderLayout(10,10));

        //群成员列表区域女
        chatgrappanel = new JPanel();
        mainpanel.add(chatgrappanel,BorderLayout.EAST);
        chatgrappanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        chatgrappanel.setPreferredSize(new Dimension(250,500));

        //查询群成员
        JPanel findpanel = new JPanel();
        chatgrappanel.add(findpanel);
        findpanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        searchfield = new JTextField(18);
        findpanel.add(searchfield);
        searchfield.setFont(new java.awt.Font("宋体",1, 15));
        searchbut = new JButton("搜索");
        findpanel.add(searchbut);
        searchbut.setToolTipText("搜索");
        searchbut.setForeground(Color.black);
        searchbut.setFocusPainted(false);
        searchbut.setBorderPainted(false);
        searchbut.setBackground(Color.green); // 设置背景颜色
        searchbut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    String text = searchfield.getText();
                    viewService.Search(text);
                    searchfield.setText("");
                }
            }public void mouseEntered(MouseEvent e) {
                searchbut.setBackground(Color.red);
            } public void mouseExited(MouseEvent e) {
                searchbut.setBackground(Color.green);
            }});


        //群成员列表
        CustomerListModel model =applicationContext.getBean("customerListModel", CustomerListModel.class);       //添加表
        table=new JTable(model){ // 设置jtable的单元格为透明的
            public Component prepareRenderer(TableCellRenderer renderer,int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                return c;
            }
        };
        table.setOpaque(false);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);// 以下设置表格列宽
        table.setFont(new Font("微软雅黑",1,18));
//        table.getColumnModel().getColumn(0).setPreferredWidth(250);
//    //table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
        tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
        table.setDefaultRenderer(Object.class, tcr);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION );
        table.setShowHorizontalLines(false);
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2) {
                    viewService.ClickList();
                }
            }});
        listjs = new JScrollPane(table);
        listjs.setOpaque(false);
        listjs.getViewport().setOpaque(false);
        chatgrappanel.add(listjs);

        //交流面板
        talkmainpanel = new JPanel();
        mainpanel.add(talkmainpanel,BorderLayout.CENTER);
        talkmainpanel.setLayout(new BorderLayout(0,0));
        talkmainpanel.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

        //输入区
        inputpanel = new JPanel();
        talkmainpanel.add(inputpanel,BorderLayout.SOUTH);
        inputpanel.setLayout(new BorderLayout(0,0));
        inputpanel.setMaximumSize(new Dimension(857,200));
        inputpanel.setBackground(new Color(255,255,255)); // 设置窗体背景颜色

        //输入文本面板
        inputLabel = new JLabel("输入区");
        inputpanel.add(inputLabel,BorderLayout.NORTH);
        inputLabel.setFont(new java.awt.Font("宋体",1, 28));
        inputLabel.setMaximumSize(new Dimension(110,56));
        inputtxtpanel = new JPanel();
        inputpanel.add(inputtxtpanel,BorderLayout.CENTER);
        inputtxtpanel.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        inputtxtpanel.setMaximumSize(new Dimension(857,135));
        inputtxtpanel.setLayout(new BorderLayout(0,0));
        inputcontent.setFont(new java.awt.Font("宋体",3, 18));
        inputcontent.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        inputcontent.setBorder(null);
        contentScroll = new JScrollPane(inputcontent);
        contentScroll.setBackground(null);
        contentScroll.setOpaque(false);
        inputtxtpanel.add(contentScroll,BorderLayout.CENTER);
        inputcontent.setLineWrap(true);
        //取消显示水平滚动条
        contentScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //显示垂直滚动条
        contentScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        inputcontent.setBorder(BorderFactory.createBevelBorder(1));

        //输入按钮面板
        inputbutpanel = new JPanel();
        inputpanel.add(inputbutpanel,BorderLayout.SOUTH);
        inputbutpanel.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        inputbutpanel.setMaximumSize(new Dimension(857,65));
        inputbutpanel.setLayout(new FlowLayout(FlowLayout.RIGHT,20,20));

        //清除按钮
        clearbut = new JButton("清除");
        inputbutpanel.add(clearbut);
        clearbut.setToolTipText("清除");
        clearbut.setFont(new java.awt.Font("宋体",1, 18));
        clearbut.setMaximumSize(new Dimension(110,40));
        clearbut.setFocusPainted(false);
        clearbut.setBackground(Color.WHITE);
        clearbut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1) {
                    inputcontent.setText("");
                }
            }public void mouseEntered(MouseEvent e) {
                clearbut.setBackground(Color.red);
            } public void mouseExited(MouseEvent e) {
                clearbut.setBackground(Color.WHITE);
            }});


        //发送按钮
        sendbut = new JButton("发送");
        inputbutpanel.add(sendbut);
        sendbut.setToolTipText("发送");
        sendbut.setFont(new java.awt.Font("宋体",1, 18));
        sendbut.setMaximumSize(new Dimension(110,40));
        sendbut.setForeground(Color.white);
        sendbut.setFocusPainted(false);
        sendbut.setBorderPainted(false);
        sendbut.setBackground(Color.blue); // 设置背景颜色
        sendbut.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==1&&!inputcontent.getText().equals("")) {
                    SendToSercice.Msg(message+inputcontent.getText());
                    showcontent.setText(showcontent.getText()+"---我("+username+") "+df.format(new Date())+"---\n  "+inputcontent.getText()+"\n");
                    inputcontent.setText("");
                }
            }public void mouseEntered(MouseEvent e) {
                sendbut.setBackground(Color.red);
            } public void mouseExited(MouseEvent e) {
                sendbut.setBackground(Color.blue);
            }});


        //聊天记录区
        chatLabel = new JLabel("聊天记录");
        talkmainpanel.add(chatLabel,BorderLayout.NORTH);
        chatLabel.setFont(new java.awt.Font("宋体",1, 28));
        chatLabel.setMaximumSize(new Dimension(110,76));
        showtalkpanel = new JPanel();
        talkmainpanel.add(showtalkpanel,BorderLayout.CENTER);
        showtalkpanel.setLayout(new BorderLayout(0,0));
        showcontent.setEditable(false);
        showcontent.setFont(new java.awt.Font("宋体",3, 18));
        showcontent.setBackground(new Color(255,255,255)); // 设置窗体背景颜色
        showcontent.setBorder(null);
        showScroll = new JScrollPane(showcontent);
        showScroll.setBackground(null);
        showScroll.setOpaque(false);
        showtalkpanel.add(showScroll,BorderLayout.CENTER);
        showcontent.setLineWrap(true);
        //取消显示水平滚动条
        showScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //显示垂直滚动条
        showScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        showcontent.setBorder(BorderFactory.createBevelBorder(1));
    }


    public JLabel getBigorsmall() {
        // TODO 自动生成的方法存根
        return bigorsmall;
    }

    public static JPanel getcContentPanelogin() {
        // TODO 自动生成的方法存根
        return contentPanelogin;
    }

    public static JPasswordField getPassword() {
        // TODO 自动生成的方法存根
        return password;
    }
    public static TextField getTf_named() {
        // TODO 自动生成的方法存根
        return tf_name;
    }
    public static JLabel getTip() {
        // TODO 自动生成的方法存根
        return tip;
    }
    public static JTextArea getShowcontent() {
        // TODO 自动生成的方法存根
        return showcontent;
    }

    public static JTextArea getinputcontent() {
        // TODO 自动生成的方法存根
        return inputcontent;
    }

    public void keyListner(){
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent e) {
                // TODO Auto-generated method stub
                if(((KeyEvent)e).getID()==KeyEvent.KEY_PRESSED){
                    switch (((KeyEvent)e).getKeyCode()) {
                        case KeyEvent.VK_ENTER:{
                            if(((KeyEvent)e).isControlDown()) {
                                if(!islogin&&!isregin) {
                                    try {
                                        username = tf_name.getText();
                                        tip.setText("登录中....");
                                        viewService.Login(username,new String(password.getPassword()));
                                        Thread.sleep(100);
                                        if(islogin) {
                                            contentPanelogin.setVisible(false);
                                            GUIini();
                                        }
                                    } catch (Exception e1) {
                                        tip.setText("连接服务器失败！");
                                        islogin = false;
                                    }
                                }else if(isregin) {
//                                    该功能未完善
//                                    //将密码发送到服务器
//                                    tip.setText("注册中...");
//                                    String repwd = new String(repassword.getPassword());
//                                    String repwdagain = new String(passwordagain.getPassword());
//                                    if(!retf_acc.getText().equals("")&&!repwd.equals("")&&repwd.equals(repwdagain)) {
//                                        try {
//                                            viewService.Regin(retf_acc.getText(),retf_name.getText(),repwd);
//                                            try {
//                                                Thread.sleep(100);
//                                            } catch (InterruptedException e1) {
//                                                e1.printStackTrace();
//                                            }
//                                            contentPaneregin.setVisible(false);
//                                            login.setVisible(true);
//                                            isregin = false;
//                                            chattalknamelabel.setText("登录页面");
//                                            System.out.println("注册完成");
//                                        } catch (Exception e1) {
//                                            System.out.println("注册出现异常");
//                                            e1.printStackTrace();
//                                        }
//                                    }else {
//                                        tip.setText("账号或密码输入不符合要求！");
//                                    }
//                                    retf_acc.setText("");
//                                    retf_name.setText("");
//                                    repassword.setText("");
//                                    passwordagain.setText("");
                                }else if(islogin&&!inputcontent.getText().equals("")){
                                    SendToSercice.Msg(message+inputcontent.getText());
                                    showcontent.setText(showcontent.getText()+"---我("+username+") "+df.format(new Date())+"---\n  "+inputcontent.getText()+"\n");
                                    inputcontent.setText("");
                                }

                            }
                            break;
                        }
                        case KeyEvent.VK_ESCAPE:{
                            viewService .exit();
                            break;
                        }
                        case KeyEvent.VK_DELETE:{
                            inputcontent.setText("");
                        }
                    }

                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    public static  JTable getTable() {
        // TODO 自动生成的方法存根
        return table;
    }
}



