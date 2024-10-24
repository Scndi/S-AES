package demo.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test05_Panel extends JPanel {
    private JTextArea plaintextArea;
    private JTextArea ciphertextArea;
    private JTextField keyField;
    private JTextField ivField;

    public Test05_Panel() {
        setLayout(null);
        setPreferredSize(new Dimension(650, 650));

        // 输入区域
        plaintextArea = new JTextArea();
        ciphertextArea = new JTextArea();
        keyField = new JTextField(16);
        ivField = new JTextField(16);

        // 设置组件大小和位置
        JLabel plaintextLabel = new JLabel("明文");
        plaintextLabel.setBounds(20, 20, 100, 25);
        plaintextArea.setBounds(20, 50, 600, 150);

        JLabel keyLabel = new JLabel("密钥 (16 字节):");
        keyLabel.setBounds(20, 220, 150, 25);
        keyField.setBounds(160, 220, 460, 25);

        JLabel ivLabel = new JLabel("初始向量 (IV):");
        ivLabel.setBounds(20, 260, 150, 25);
        ivField.setBounds(160, 260, 460, 25);

        JButton encryptButton = new JButton("加密");
        encryptButton.setBounds(140, 300, 100, 30);
        JButton decryptButton = new JButton("解密");
        decryptButton.setBounds(350, 300, 100, 30);

        JLabel ciphertextLabel = new JLabel("密文:");
        ciphertextLabel.setBounds(20, 350, 100, 25);
        ciphertextArea.setBounds(20, 380, 600, 150);

        // 添加组件到面板
        add(plaintextLabel);
        add(plaintextArea);
        add(keyLabel);
        add(keyField);
        add(ivLabel);
        add(ivField);
        add(encryptButton);
        add(decryptButton);

        add(ciphertextLabel);
        add(ciphertextArea);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextArea.getText();
                String ciphertext = "null";
                ciphertextArea.setText(ciphertext);
            }
        });
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextArea.getText();
                String plaintext = "null";
                plaintextArea.setText(plaintext);
            }
        });
    }



}
