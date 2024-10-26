package demo.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import static demo.S_AES.S_AES.decrypt;
import static demo.S_AES.S_AES.encrypt;

public class Test01_Panel extends JPanel {
    // 标签用于提示用户输入
    JLabel input1_label = new JLabel("请输入16bits明文或密文：");
    JLabel input2_label = new JLabel("请输入16bits密钥：");
    JLabel output_label = new JLabel("加密/解密结果：");

    // 文本框用于用户输入
    JTextField input1_field = new JTextField();
    JTextField input2_field = new JTextField();
    JTextArea output_area = new JTextArea();
    JScrollPane output = new JScrollPane(output_area);

    JButton encrypt_button = new JButton("加密");
    JButton decrypt_button = new JButton("解密");

    Test01_Panel() {
        // 设置布局为null，使用绝对定位
        this.setLayout(null);
        this.setPreferredSize(new Dimension(650, 650));

        // 设置组件的位置和大小
        input1_label.setBounds(20, 20, 200, 25);
        input1_field.setBounds(220, 20, 400, 25);

        input2_label.setBounds(20, 60, 200, 25);
        input2_field.setBounds(220, 60, 400, 25);

        encrypt_button.setBounds(100, 100, 100, 30);
        decrypt_button.setBounds(250, 100, 100, 30);

        output_label.setBounds(20, 150, 200, 25);
        output.setBounds(20, 200, 600, 300);
        output_area.setEditable(false); // 设置为不可编辑

        // 添加组件到面板
        this.add(input1_label);
        this.add(input1_field);
        this.add(input2_label);
        this.add(input2_field);
        this.add(encrypt_button);
        this.add(decrypt_button);
        this.add(output_label);
        this.add(output);

        // 添加按钮监听器
        encrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = input1_field.getText();
                String key = input2_field.getText();

                int [] plaintext_array = StringToIntArray(plaintext);
                int [] key_array = StringToHexArray(key);

                String result = IntArrayToString(encrypt(plaintext_array, key_array)); // 调用加密函数
                output_area.append("加密结果:"+result + "\n"); // 显示结果
            }
        });

        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = input1_field.getText();
                String key = input2_field.getText();

                int [] ciphertext_array = StringToIntArray(ciphertext);
                int [] key_array = StringToHexArray(key);

                String result = IntArrayToString(decrypt(ciphertext_array, key_array)) ; // 调用解密函数
                output_area.append("解密结果:"+result + "\n"); // 显示结果
            }
        });

        // 设置面板可见
        this.setVisible(true);
    }


    public static int[] StringToIntArray(String str) {
        int[] intArray = new int[str.length()]; // 创建整型数组

        // 将每个字符转换为整数
        for (int i = 0; i < str.length(); i++) {
            intArray[i] = Character.getNumericValue(str.charAt(i));
        }
        return intArray; // 返回整型数组
    }

    public static int[] StringToHexArray(String str) {

        int[] result = new int[2]; // 创建一个大小为 2 的整数数组

        // 分割字符串
        String firstBinary = str.substring(0, 8); // 前8位
        String secondBinary = str.substring(8, 16); // 后8位

        // 将二进制字符串转换为整数
        result[0] = Integer.parseInt(firstBinary, 2); // 以二进制解析
        result[1] = Integer.parseInt(secondBinary, 2); // 以二进制解析

        return result; // 返回整数数组
    }


    public static String IntArrayToString(int[] intArray) {
        StringBuilder sb = new StringBuilder();

        for (int j : intArray) {
            sb.append(j);

        }

        return sb.toString(); // 返回最终的字符串
    }


}



