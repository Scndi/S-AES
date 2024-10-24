package demo.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                String result = s_encrypt(plaintext, key); // 调用加密函数
                output_area.append(result + "\n"); // 显示结果
            }
        });

        decrypt_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = input1_field.getText();
                String key = input2_field.getText();
                String result = s_decrypt(ciphertext, key); // 调用解密函数
                output_area.append(result + "\n"); // 显示结果
            }
        });

        // 设置面板可见
        this.setVisible(true);
    }

    private String s_encrypt(String plaintext, String key) {
        // 这里添加你的加密逻辑
        return "加密结果: " + plaintext + ", 使用密钥 " + key;
    }

    private String s_decrypt(String ciphertext, String key) {
        // 这里添加你的解密逻辑
        return "解密结果: " + ciphertext + ", 使用密钥 " + key;
    }


}
