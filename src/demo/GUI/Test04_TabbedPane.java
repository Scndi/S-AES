package demo.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        resultArea2.setBounds(20, 170, 400, 100);
        resultArea2.setLineWrap(true);
        resultArea2.setWrapStyleWord(true);
        panel2.add(resultArea2);
        addTab("关卡3.2 中间相遇", panel2);

        // 关卡 3.3 三重加密模式1
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
        resultArea3.setBounds(20, 210, 400, 100);
        resultArea3.setLineWrap(true);
        resultArea3.setWrapStyleWord(true);
        panel3.add(resultArea3);
        addTab("关卡3.3 三重加密模式1", panel3);

        // 关卡 3.4 三重加密模式2
        JPanel panel4 = new JPanel();
        panel4.setLayout(null);
        JLabel label5 = new JLabel("密钥 (K1+K2+K3, 48 bits):");
        label5.setBounds(20, 20, 150, 25);
        panel4.add(label5);
        JTextField keyField4 = new JTextField();
        keyField4.setBounds(180, 20, 400, 25);
        panel4.add(keyField4);

        JLabel plaintextLabel4 = new JLabel("明文:");
        plaintextLabel4.setBounds(20, 60, 120, 25);
        panel4.add(plaintextLabel4);
        JTextField plaintextField4 = new JTextField();
        plaintextField4.setBounds(150, 60, 400, 25);
        panel4.add(plaintextField4);

        JLabel ciphertextLabel4 = new JLabel("密文:");
        ciphertextLabel4.setBounds(20, 100, 120, 25);
        panel4.add(ciphertextLabel4);
        JTextField ciphertextField4 = new JTextField();
        ciphertextField4.setBounds(150, 100, 400, 25);
        panel4.add(ciphertextField4);

        JButton tripleEncryptButton2 = new JButton("执行三重加密模式2");
        tripleEncryptButton2.setBounds(20, 140, 180, 30);
        panel4.add(tripleEncryptButton2);

        JButton tripleDecryptButton2 = new JButton("执行三重解密模式2");
        tripleDecryptButton2.setBounds(210, 140, 180, 30);
        panel4.add(tripleDecryptButton2);

        JLabel resultLabel4 = new JLabel("结果:");
        resultLabel4.setBounds(20, 180, 120, 25);
        panel4.add(resultLabel4);
        JTextArea resultArea4 = new JTextArea();
        resultArea4.setBounds(20, 210, 400, 100);
        resultArea4.setLineWrap(true);
        resultArea4.setWrapStyleWord(true);
        panel4.add(resultArea4);
        addTab("关卡3.4 三重加密模式2", panel4);

        // 添加按钮监听器
        encryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField1.getText();
                String key = keyField1.getText();
                String result = "结果";
                // 调用双重加密函数
                resultArea1.setText("双重加密结果: " + result + ", 使用密钥: " + key);
            }
        });

        decryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField1.getText();
                String key = keyField1.getText();
                String result = "结果";
                // 调用双重解密函数
                resultArea1.setText("双重解密结果: " + result + ", 使用密钥: " + key);
            }
        });

        attackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField2.getText();
                String ciphertext = ciphertextField2.getText();
                // 调用中间相遇攻击函数
                resultArea2.setText("中间相遇攻击结果: ");

            }
        });

        tripleEncryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField3.getText();
                String key = keyField3.getText();
                String result = "结果";

                // 调用三重加密模式1
                resultArea3.setText("三重加密模式1结果: " + result + ", 使用密钥: " + key);
            }
        });

        tripleDecryptButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField3.getText();
                String key = keyField3.getText();
                String result = "结果";

                // 调用三重解密模式1
                resultArea3.setText("三重解密模式1结果: " + result + ", 使用密钥: " + key);
            }
        });

        tripleEncryptButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField4.getText();
                String key = keyField4.getText();
                String result = "结果";

                // 调用三重加密模式2
                resultArea4.setText("三重加密模式2结果: " + result + ", 使用密钥: " + key);
            }
        });

        tripleDecryptButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField4.getText();
                String key = keyField4.getText();
                String result = "结果";

                // 调用三重解密模式2
                resultArea4.setText("三重解密模式2结果: " + result + ", 使用密钥: " + key);
            }
        });
    }
}
