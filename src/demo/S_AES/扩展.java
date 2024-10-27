package demo.S_AES;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static demo.S_AES.S_AES.*;

public class 扩展 {
    public static String encryptString(String input, int[] key) {
        // 如果输入字符串长度是奇数，添加一个空格
        if (input.length() % 2 != 0) {
            input += " ";
        }

        StringBuilder encrypted = new StringBuilder();

        // 遍历每两个字符
        for (int i = 0; i < input.length(); i += 2) {
            // 获取两个字符
            char ch1 = input.charAt(i);
            char ch2 = input.charAt(i + 1);

            // 将两个字符转换为16位的int数组（0和1）
            int[] temp1 = charToIntArray(ch1);
            int[] temp2 = charToIntArray(ch2);

            int [] plaintext = new int [16];
            for (int j = 0; j < temp1.length; j++) {
                plaintext[j] = temp1[j];
                plaintext[j + 8] = temp2[j];
            }
            // 加密
            int[] ciphertext = encrypt(plaintext, key);

            temp1 = Arrays.copyOfRange(ciphertext, 0,8);
            temp2 = Arrays.copyOfRange(ciphertext,8,16);
            // 将加密结果转换为字符并追加到结果中
            ch1 = intArrayToChar(temp1);
            encrypted.append(ch1);
            ch2 = intArrayToChar(temp2);
            encrypted.append(ch2);
        }

        return encrypted.toString();
    }

    public static String decryptString(String input, int[] key) {
        StringBuilder decrypted = new StringBuilder();

        // 遍历每两个字符
        for (int i = 0; i < input.length(); i += 2) {
            // 获取两个字符
            char ch1 = input.charAt(i);
            char ch2 = input.charAt(i + 1);

            // 将两个字符转换为16位的int数组（0和1）
            int[] temp1 = charToIntArray(ch1);
            int[] temp2 = charToIntArray(ch2);

            int [] ciphertext = new int[16];
            for (int j = 0; j < temp1.length; j++) {
                ciphertext[j] = temp1[j];
                ciphertext[j + 8] = temp2[j];
            }
            // 解密
            int[] plaintext = decrypt(ciphertext, key);

            temp1 = Arrays.copyOfRange(plaintext, 0,8);
            temp2 = Arrays.copyOfRange(plaintext,8,16);

            // 将解密后的结果转换为字符并追加到结果中
            ch1 = intArrayToChar(temp1);
            decrypted.append(ch1);
            ch2 = intArrayToChar(temp2);
            decrypted.append(ch2);
        }

        return decrypted.toString().trim(); // 去除可能的空格
    }


    public static int[] charToIntArray(char ch) {
        int[] result = new int[8];

        // 将ASCII值转换为8位二进制表示
        for (int i = 0; i < 8; i++) {
            result[i] = ((int) ch >> (7 - i)) & 1; // 高位在前
        }

        return result;
    }
    public static char intArrayToChar(int[] bits) {
        int asciiValue = 0;

        // 将int[8]的二进制位转换为ASCII值
        for (int i = 0; i < 8; i++) {
            asciiValue += bits[i] * (1 << (7 - i)); // 将每一位放到正确的位置
        }

        return (char) asciiValue; // 将ASCII值转换为字符
    }




    public static void main(String[] args) {
        // 测试用例
        String plaintext = "yuanshen";
        int[] key = {0xFF, 0xFF}; // 示例密钥，替换为实际密钥

        // 加密
        String encryptedText = 扩展.encryptString(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);

        // 解密
        String decryptedText = 扩展.decryptString(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);

        // 验证
        if (plaintext.equals(decryptedText)) {
            System.out.println("解密成功，输出与输入一致！");
        } else {
            System.out.println("解密失败，输出与输入不一致！");
        }
    }
}
