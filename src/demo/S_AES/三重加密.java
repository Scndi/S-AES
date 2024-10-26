package demo.S_AES;



import static demo.S_AES.S_AES.decrypt;
import static demo.S_AES.S_AES.encrypt;

public class 三重加密 {
    //加密方法
    public static int[] triEncrypt(int[] plaintext, int[] key1,int[] key2) {
        int []res=encrypt(plaintext,key1);
        res=decrypt(res,key2);
        return encrypt(res,key1);
    }

    // 解密方法
    public static int[] triDecrypt(int[] ciphertext, int[] key1,int[] key2) {
        int []res=decrypt(ciphertext,key1);
        res=encrypt(res,key2);
        return decrypt(res,key1);
    }

    public static void main(String[] args) {
        // 创建两个32位密钥（每个由两个16位部分组成）
        int[] key1 = {0b11110000,0x00}; // 第一个密钥
        int[] key2 = {0x00,0xFF}; // 第二个密钥

        // 示例明文（16位）
        int[] plaintext = {1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0}; // 16位二进制数组

        // 执行双重加密
        int[] ciphertext = triEncrypt(plaintext, key1, key2);
        System.out.print("Ciphertext after double encryption: ");
        for (int bit : ciphertext) {
            System.out.print(bit); // 输出加密结果
        }
        System.out.println();

        // 执行三重解密
        int[] decryptedText = triDecrypt(ciphertext, key1, key2);
        System.out.print("Decrypted text after double decryption: ");
        for (int bit : decryptedText) {
            System.out.print(bit); // 输出解密结果
        }
        System.out.println();
    }

}

