package demo.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import static demo.GUI.Test01_Panel.*;

import static demo.S_AES.双重加密.*;
import static demo.S_AES.中间相遇攻击.*;
import static demo.S_AES.三重加密.*;

public class Test04_TabbedPane extends JTabbedPane {

    public Test04_TabbedPane() {

        // 关卡 3.1 双重加密
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        JLabel label1 = new JLabel("密钥 (32 bits):");
        label1.setBounds(20, 20, 120, 25);
        panel1.add(label1);
        JTextField keyField1 = new JTextField();
        keyField1.setBounds(150, 20, 400, 25);
        panel1.add(keyField1);

        JLabel plaintextLabel1 = new JLabel("明文:");
        plaintextLabel1.setBounds(20, 60, 120, 25);
        panel1.add(plaintextLabel1);
        JTextField plaintextField1 = new JTextField();
        plaintextField1.setBounds(150, 60, 400, 25);
        panel1.add(plaintextField1);

        JLabel ciphertextLabel1 = new JLabel("密文:");
        ciphertextLabel1.setBounds(20, 100, 120, 25);
        panel1.add(ciphertextLabel1);
        JTextField ciphertextField1 = new JTextField();
        ciphertextField1.setBounds(150, 100, 400, 25);
        panel1.add(ciphertextField1);

        JButton encryptButton1 = new JButton("执行双重加密");
        encryptButton1.setBounds(20, 140, 150, 30);
        panel1.add(encryptButton1);

        JButton decryptButton1 = new JButton("执行双重解密");
        decryptButton1.setBounds(180, 140, 150, 30);
        panel1.add(decryptButton1);

        JLabel resultLabel1 = new JLabel("结果:");
        resultLabel1.setBounds(20, 180, 120, 25);
        panel1.add(resultLabel1);
        JTextArea resultArea1 = new JTextArea();
        resultArea1.setBounds(20, 210, 400, 100);
        resultArea1.setLineWrap(true);
        resultArea1.setWrapStyleWord(true);
        panel1.add(resultArea1);
        addTab("关卡3.1 双重加密", panel1);

        // 关卡 3.2 中间相遇
        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        JLabel label2 = new JLabel("明文:");
        label2.setBounds(20, 20, 120, 25);
        panel2.add(label2);
        JTextField plaintextField2 = new JTextField();
        plaintextField2.setBounds(150, 20, 400, 25);
        panel2.add(plaintextField2);

        JLabel label3 = new JLabel("密文:");
        label3.setBounds(20, 60, 120, 25);
        panel2.add(label3);
        JTextField ciphertextField2 = new JTextField();
        ciphertextField2.setBounds(150, 60, 400, 25);
        panel2.add(ciphertextField2);

        JButton attackButton = new JButton("执行中间相遇攻击");
        attackButton.setBounds(20, 100, 180, 30);
        panel2.add(attackButton);

        JLabel resultLabel2 = new JLabel("结果:");
        resultLabel2.setBounds(20, 140, 120, 25);
        panel2.add(resultLabel2);
        JTextArea resultArea2 = new JTextArea();
        JScrollPane result_scrollPane2 = new JScrollPane(resultArea2);
        result_scrollPane2.setBounds(20, 170, 400, 300);
        panel2.add(result_scrollPane2);
        addTab("关卡3.2 中间相遇", panel2);

        // 关卡 3.3 三重加密
        JPanel panel3 = new JPanel();
        panel3.setLayout(null);
        JLabel label4 = new JLabel("密钥 (K1+K2, 32 bits):");
        label4.setBounds(20, 20, 150, 25);
        panel3.add(label4);
        JTextField keyField3 = new JTextField();
        keyField3.setBounds(180, 20, 400, 25);
        panel3.add(keyField3);

        JLabel plaintextLabel3 = new JLabel("明文:");
        plaintextLabel3.setBounds(20, 60, 120, 25);
        panel3.add(plaintextLabel3);
        JTextField plaintextField3 = new JTextField();
        plaintextField3.setBounds(150, 60, 400, 25);
        panel3.add(plaintextField3);

        JLabel ciphertextLabel3 = new JLabel("密文:");
        ciphertextLabel3.setBounds(20, 100, 120, 25);
        panel3.add(ciphertextLabel3);
        JTextField ciphertextField3 = new JTextField();
        ciphertextField3.setBounds(150, 100, 400, 25);
        panel3.add(ciphertextField3);

        JButton tripleEncryptButton1 = new JButton("执行三重加密模式1");
        tripleEncryptButton1.setBounds(20, 140, 180, 30);
        panel3.add(tripleEncryptButton1);

        JButton tripleDecryptButton1 = new JButton("执行三重解密模式1");
        tripleDecryptButton1.setBounds(210, 140, 180, 30);
        panel3.add(tripleDecryptButton1);

        JLabel resultLabel3 = new JLabel("结果:");
        resultLabel3.setBounds(20, 180, 120, 25);
        panel3.add(resultLabel3);
        JTextArea resultArea3 = new JTextArea();
        resultArea3.setBounds(20, 210, 400, 400);
        resultArea3.setLineWrap(true);
        resultArea3.setWrapStyleWord(true);
        panel3.add(resultArea3);
        addTab("关卡3.3 三重加密模式一", panel3);



        // 添加按钮监听器
        encryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField1.getText();
                String key = keyField1.getText();

                String key1 = key.substring(0,16);
                String key2 = key.substring(16,32);

                int [] plaintext_array = StringToIntArray(plaintext);
                int [] key1_array = StringToHexArray(key1);
                int [] key2_array = StringToHexArray(key2);

                int [] result_array = doubleEncrypt(plaintext_array,key1_array,key2_array);
                String result = IntArrayToString(result_array);

                resultArea1.append("双重加密结果: " + result + "\n");
            }
        });

        decryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField1.getText();
                String key = keyField1.getText();
                String key1 = key.substring(0,16);
                String key2 = key.substring(16,32);

                int [] ciphertext_array = StringToIntArray(ciphertext);
                int [] key1_array = StringToHexArray(key1);
                int [] key2_array = StringToHexArray(key2);

                int [] result_array = doubleDecrypt(ciphertext_array,key1_array,key2_array);
                String result = IntArrayToString(result_array);


                resultArea1.append("双重解密结果: " + result + "\n " );
            }
        });

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintextText = plaintextField2.getText();
                String ciphertextText = ciphertextField2.getText();

                int[] plaintext = StringToIntArray(plaintextText);
                int[] ciphertext = StringToIntArray(ciphertextText);

                // 调用中间相遇攻击函数
                Set<String> resultKeys = meetInTheMiddle(plaintext, ciphertext);
                resultArea2.append("中间相遇攻击结果:\n");
                int cnt = 0;
                if (resultKeys.isEmpty()) {
                    resultArea2.append("未找到共同的密钥对。\n");
                } else {
                    for (String keyPair : resultKeys) {
                        resultArea2.append(keyPair + "\n");
                        cnt++;
                    }
                }
                resultArea2.append("最后找到密钥对的数量为: " + cnt + "\n");
            }
        });

        tripleEncryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField3.getText();
                String key = keyField3.getText();
                String key1 = key.substring(0,16);
                String key2 = key.substring(16,32);

                int [] plaintext_array = StringToIntArray(plaintext);
                int [] key1_array = StringToHexArray(key1);
                int [] key2_array = StringToHexArray(key2);

                int [] result_array = triEncrypt(plaintext_array,key1_array,key2_array);
                String result = IntArrayToString(result_array);

                resultArea3.append("三重加密结果: " + result + "\n");
            }
        });

        tripleDecryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField3.getText();
                String key = keyField3.getText();
                String key1 = key.substring(0,16);
                String key2 = key.substring(16,32);

                int [] ciphertext_array = StringToIntArray(ciphertext);
                int [] key1_array = StringToHexArray(key1);
                int [] key2_array = StringToHexArray(key2);

                int [] result_array = triDecrypt(ciphertext_array,key1_array,key2_array);
                String result = IntArrayToString(result_array);


                resultArea3.append("三重解密结果: " + result + "\n " );
            }
        });


    }
}
