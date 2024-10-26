package demo.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static demo.GUI.Test01_Panel.StringToHexArray;
import static demo.GUI.Test01_Panel.StringToIntArray;
import static demo.GUI.Test01_Panel.IntArrayToString;

import static demo.S_AES.CBC.*;

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
                String key = keyField.getText();
                String iv = ivField.getText();
                String ciphertext;

                int [] plaintext_array = StringToIntArray(plaintext);
                int [] key_array = StringToHexArray(key);
                int [] iv_array = StringToIntArray(iv);

                int [] ciphertext_array = cbcEncrypt(plaintext_array,key_array,iv_array);
                ciphertext = IntArrayToString(ciphertext_array);
                ciphertextArea.setText(ciphertext);
            }
        });
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext;
                String key = keyField.getText();
                String iv = ivField.getText();
                String ciphertext = ciphertextArea.getText();

                int [] ciphertext_array = StringToIntArray(ciphertext);
                int [] key_array = StringToHexArray(key);
                int [] iv_array = StringToIntArray(iv);

                int [] plaintext_array = cbcDecrypt(ciphertext_array,key_array,iv_array);
                plaintext = IntArrayToString(plaintext_array);
                plaintextArea.setText(plaintext);
            }
        });
    }



}
