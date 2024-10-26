package demo.S_AES;


import static demo.S_AES.S_AES.decrypt;
import static demo.S_AES.S_AES.encrypt;

public class 双重加密 {
    //加密方法
    public static int[] doubleEncrypt(int[] plaintext, int[] key1,int[] key2) {
        int []res=encrypt(plaintext,key1);
       return encrypt(res,key2);
    }

    // 解密方法
    public static int[] doubleDecrypt(int[] ciphertext, int[] key1,int[] key2) {
        int []res=decrypt(ciphertext,key2);
        return decrypt(res,key1);
    }

        public static void main(String[] args) {
            // 创建两个32位密钥（每个由两个16位部分组成）
            int[] key1 = {65, 71}; // 第一个密钥
            int[] key2 = {104, 188}; // 第二个密钥

            // 示例明文（16位）
            int[] plaintext = {1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1}; // 16位二进制数组

            // 执行双重加密
            int[] ciphertext = doubleEncrypt(plaintext, key1, key2);
            System.out.print("Ciphertext after double encryption: ");
            for (int bit : ciphertext) {
                System.out.print(bit); // 输出加密结果
            }
            System.out.println();

            // 执行双重解密
            int[] decryptedText = doubleDecrypt(ciphertext, key1, key2);
            System.out.print("Decrypted text after double decryption: ");
            for (int bit : decryptedText) {
                System.out.print(bit); // 输出解密结果
            }
            System.out.println();
        }

    }

