package demo.GUI;


import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JFrame main_frame = new JFrame();


    public GUI() {
        main_frame.setTitle("AES加密");
        main_frame.setBounds(300, 250, 500, 400);

        Container main_container = main_frame.getContentPane();

        JTabbedPane main_tabs = new JTabbedPane();

        //五个测试关卡 第二关不用单独的页面
        Test01_Panel test1_panel = new Test01_Panel();
        Test03_Panel test3_panel = new Test03_Panel();
        JPanel test4_panel = new JPanel();
        JPanel test5_panel = new JPanel();




        main_tabs.add(test1_panel);
        main_tabs.add(test3_panel);
        main_tabs.add(test4_panel);
        main_tabs.add(test5_panel);

        main_container.add(main_tabs);



        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main_frame.setVisible(true);

    }
}
