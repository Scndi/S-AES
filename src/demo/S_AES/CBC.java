package demo.S_AES;
import java.security.SecureRandom;

import static demo.S_AES.S_AES.decrypt;
import static demo.S_AES.S_AES.encrypt;


public class CBC {



    // CBC模式加密
    public static int[] cbcEncrypt(int[] plaintext, int[] key, int [] iv) {

        int[] ciphertext = new int[plaintext.length]; // 初始化密文数组

        int[] previousCiphertext = iv; // 保存上一个密文分组，初始为IV

        for (int i = 0; i < plaintext.length; i += 16) {
            // 获取当前明文分组
            int[] block = new int[16];
            System.arraycopy(plaintext, i, block, 0, 16);

            // 明文分组与上一个密文分组（或IV）异或
            for (int j = 0; j < 16; j++) {
                block[j] ^= previousCiphertext[j]; // 逐位异或
            }

            // 加密当前分组
            int[] encryptedBlock = encrypt(block, key);
            System.arraycopy(encryptedBlock, 0, ciphertext, i, 16); // 存储密文分组

            // 更新上一个密文分组
            previousCiphertext = encryptedBlock;
        }

        return ciphertext; // 返回包含IV和密文的数组
    }

    // CBC模式解密
    public static int[] cbcDecrypt(int[] ciphertext, int[] key ,int [] iv) {

        int[] plaintext = new int[ciphertext.length]; // 初始化明文数组
        int[] previousCiphertext = iv; // 保存上一个密文分组，初始为IV

        for (int i = 0; i < ciphertext.length; i += 16) {
            // 获取当前密文分组
            int[] block = new int[16];
            System.arraycopy(ciphertext, i, block, 0, 16);

            // 解密当前密文分组
            int[] decryptedBlock = decrypt(block, key);

            // 明文分组与上一个密文分组（或IV）异或
            for (int j = 0; j < 16; j++) {
                plaintext[i + j] = decryptedBlock[j] ^ previousCiphertext[j]; // 逐位异或
            }

            // 更新上一个密文分组
            previousCiphertext = block; // 更新为当前密文分组
        }

        return plaintext; // 返回解密后的明文
    }

    public static void main(String[] args) {
        // 示例密钥
        int[] key = {0x1A, 0x3C};

        // 示例明文（长明文消息）
        int[] plaintext = {
                1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0,
                1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0
        }; // 可以是任意长度的明文
        int [] iv = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        // 执行CBC加密
        int[] ciphertext = cbcEncrypt(plaintext, key,iv);
        System.out.print("Ciphertext (IV + Ciphertext): ");
        for (int bit : ciphertext) {
            System.out.print(bit); // 输出加密结果
        }
        System.out.println();

        // 执行CBC解密
        int[] decryptedText = cbcDecrypt(ciphertext, key ,iv);
        System.out.print("Decrypted text: ");
        for (int bit : decryptedText) {
            System.out.print(bit); // 输出解密结果
        }
        System.out.println();
    }
}
