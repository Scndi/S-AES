package demo.S_AES;

import java.nio.charset.StandardCharsets;

import static demo.S_AES.S_AES.toBinary;

public class 扩展 {
    public static String encryptString(String plaintext, int[] key) {
        // 如果字符个数是奇数，则在末尾补一个空格
        if (plaintext.length() % 2 != 0) {
            plaintext += " ";
        }

        byte[] textBytes = plaintext.getBytes(StandardCharsets.US_ASCII);
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < textBytes.length; i += 2) {
            int[] plaintextBlock = byteTo16BitArray(textBytes, i);
            int[] encryptedBlock = S_AES.encrypt(plaintextBlock, key); // 调用静态方法
            encryptedText.append(intArrayToASCII(encryptedBlock));
        }
        return encryptedText.toString();
    }

    public static String decryptString(String encryptedText, int[] key) {
        byte[] textBytes = encryptedText.getBytes(StandardCharsets.US_ASCII);
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < textBytes.length; i += 2) {
            int[] encryptedBlock = new int[16];
            if (i + 1 < textBytes.length) {
                int firstByte = textBytes[i] & 0xFF;
                int secondByte = textBytes[i + 1] & 0xFF;
                System.arraycopy(toBinary(firstByte), 0, encryptedBlock, 0, 8);
                System.arraycopy(toBinary(secondByte), 0, encryptedBlock, 8, 8);
            } else {
                // 处理单个字节的情况
                int lastByte = textBytes[i] & 0xFF;
                System.arraycopy(toBinary(lastByte), 0, encryptedBlock, 0, 8);
                // 填充剩余的位
                for (int j = 8; j < 16; j++) {
                    encryptedBlock[j] = 0; // 填充为0或适当的填充值
                }
            }

            int[] plaintextBlock = S_AES.decrypt(encryptedBlock, key); // 调用静态方法
            plainText.append(intArrayToASCII(plaintextBlock));
        }
        return plainText.toString().trim(); // 去掉末尾的空格
    }


    private static int[] byteTo16BitArray(byte[] bytes, int index) {
        int[] result = new int[16];
        if (index < bytes.length) {
            int[] firstBinary = toBinary(bytes[index] & 0xFF);
            int[] secondBinary = (index + 1 < bytes.length) ? toBinary(bytes[index + 1] & 0xFF) : new int[8];
            System.arraycopy(firstBinary, 0, result, 0, 8);
            System.arraycopy(secondBinary, 0, result, 8, 8);
        }
        return result;
    }

    private static String intArrayToASCII(int[] bits) {
        int firstByte = 0, secondByte = 0;
        for (int i = 0; i < 8; i++) {
            firstByte = (firstByte << 1) | bits[i];
            secondByte = (secondByte << 1) | bits[i + 8];
        }
        // 返回 ASCII 字符串
        return new String(new byte[]{(byte) firstByte, (byte) secondByte}, StandardCharsets.US_ASCII);
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
