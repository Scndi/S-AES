package demo.S_AES;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import static demo.S_AES.S_AES.decrypt;
import static demo.S_AES.S_AES.encrypt;

public class 中间相遇攻击 {

    // 前向加密，记录中间值
    public static Map<Integer, Set<int[]>> forwardEncrypt(int[] plaintext) {
        Map<Integer, Set<int[]>> middleValues = new HashMap<>(); // 允许存储多个 key1

        // 尝试所有可能的 key1 值
        for (int high = 0; high < 256; high++) {
            for (int low = 0; low < 256; low++) {
                int[] tempKey1 = {high, low}; // 生成完整的 key1

                // 进行加密
                int[] ciphertext = encrypt(plaintext, tempKey1);
                // 将 ciphertext 数组转换为十进制数
                int middleValue = 0;
                for (int k = 0; k < 16; k++) {
                    middleValue += ciphertext[k] * (1 << (15 - k)); // 计算十进制值
                }
                // 将中间值和对应的 key1 存储在映射中
                middleValues.computeIfAbsent(middleValue, k -> new HashSet<>()).add(tempKey1);
            }
        }

        return middleValues;
    }

    // 反向解密，查找中间值
    public static Set<String> backwardDecrypt(int[] ciphertext, Map<Integer, Set<int[]>> middleValues) {
        Set<String> foundKeys = new HashSet<>(); // 用于存储找到的所有密钥对

        // 尝试所有可能的 key2 值，从 {0x00, 0x00} 到 {0xFF, 0xFF}
        for (int high = 0; high < 256; high++) {
            for (int low = 0; low < 256; low++) {
                int[] tempKey2 = {high, low}; // 生成完整的 key2

                // 进行解密
                int[] decryptedText = decrypt(ciphertext, tempKey2);
                // 将 decryptedText 数组转换为十进制数
                int mid = 0;
                for (int k = 0; k < 16; k++) {
                    mid += decryptedText[k] * (1 << (15 - k)); // 计算十进制值
                }
                // 检查中间值是否匹配
                if (middleValues.containsKey(mid)) {
                    // 找到匹配的密钥组合
                    for (int[] foundKey1 : middleValues.get(mid)) {
                        // 存储密钥对，避免重复
                        String keyPair = String.format("K1 = [%d, %d], K2 = [%d, %d]", foundKey1[0], foundKey1[1], tempKey2[0], tempKey2[1]);
                        foundKeys.add(keyPair);
                    }
                }
            }
        }

        return foundKeys;
    }

    public static Set<String> meetInTheMiddle(int[] plaintext, int[] ciphertext) {
        // 进行前向加密并存储中间值
        Map<Integer, Set<int[]>> middleValues = forwardEncrypt(plaintext);
        Set<String> commonKeys = null; // 初始化为空，后续将其赋值

        // 进行反向解密并查找匹配的中间值
        Set<String> foundKeys = backwardDecrypt(ciphertext, middleValues);

        // 对第一次找到的密钥对进行赋值，后续进行交集操作
        if (commonKeys == null) {
            commonKeys = new HashSet<>(foundKeys); // 将第一次找到的密钥对赋值
        } else {
            commonKeys.retainAll(foundKeys); // 更新共同的密钥
        }

        return backwardDecrypt(ciphertext, middleValues);
    }
    public static void main(String[] args) {
        Set<String> commonKeys = meetInTheMiddle(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new int[]{0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1});
        int cnt = 0;
        // 打印所有共同找到的密钥对
        if (commonKeys == null || commonKeys.isEmpty()) {
            System.out.println("No common key pairs found.");
        } else {
            for (String keyPair : commonKeys) {
                System.out.println(keyPair);
            }
        }
        cnt+=commonKeys.size();
        System.out.println(cnt);

    }
}
