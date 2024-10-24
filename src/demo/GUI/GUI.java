package demo.GUI;


import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JFrame main_frame = new JFrame();


    public GUI() {
        main_frame.setTitle("AES加密");
        main_frame.setBounds(300, 250, 650, 650);

        Container main_container = main_frame.getContentPane();

        JTabbedPane main_tabs = new JTabbedPane();

        //五个测试关卡 第二关不用单独的页面
        Test01_Panel test1_panel = new Test01_Panel();
        Test03_Panel test3_panel = new Test03_Panel();
        Test04_TabbedPane test4_Tabbed= new Test04_TabbedPane();
        Test05_Panel test5_panel = new Test05_Panel();




        main_tabs.addTab("关卡1 标准加密",test1_panel);
        main_tabs.addTab("关卡3 拓展加密",test3_panel);
        main_tabs.addTab("关卡4 多重加密",test4_Tabbed);
        main_tabs.addTab("关卡5 工作模式",test5_panel);

        main_container.add(main_tabs);



        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setVisible(true);

    }
}
