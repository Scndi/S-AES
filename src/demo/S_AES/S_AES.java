package demo.S_AES;

import java.util.Arrays;

public class S_AES {
    public int[] P; // 明文
    public int[] C; // 密文
    public int[] key; // 密钥
    public static int[][] SBox1 = {{9, 4, 10, 11}, {13, 1, 8, 5}, {6, 2, 0, 3}, {12, 14, 15, 7}};
    public static int[][] SBox2 = {{10, 5, 9, 11}, {1, 7, 8, 15}, {6, 0, 2, 3}, {12, 4, 13, 14}};

    public S_AES() {
        P = new int[16];
        key = new int[2];
        C = new int[16];
    }

    public S_AES(int[] key) {
        P = new int[16];
        this.key = key;
        C = new int[16];
        keyExpansion(key);
    }

    // 生成轮密钥
    public static int[] keyExpansion(int[] key) {
        int[] RCON = {0x80, 0x30}; // 轮常数
        int[] wkey = new int[2 + (3 - 1) * 2]; // 扩展后的密钥数组
        System.arraycopy(key, 0, wkey, 0, 2); // 复制初始密钥

        for (int i = 2; i < wkey.length; i++) {
            int temp = wkey[i - 1];

            // 每个第2个字进行轮常数和子字节替换以及旋转
            if (i % 2 == 0) {
                temp = subNib1(rotNib(temp), SBox1) ^ RCON[i / 2 - 1];
            }

            wkey[i] = wkey[i - 2] ^ temp;
        }

        return wkey;
    }

    //用于密钥扩展
    private static int subNib1(int input, int[][] SBox) {
        // 计算行和列
        int rowHigh = (input >> 6) & 0x03; // 前两位
        int colHigh = (input >> 4) & 0x03; // 后两位
        int rowLow = (input >> 2) & 0x03;  // 前两位
        int colLow = input & 0x03;        // 后两位

        // 从 S-Box 中查找替换值
        int newHighNibble = SBox[rowHigh][colHigh];
        int newLowNibble = SBox[rowLow][colLow];

        // 合并并返回结果
        return (newHighNibble << 4) | newLowNibble;
    }

    //用于半字节替换
    private static int subNib2(int input, int[][] SBox) {
        // 计算行和列
        int rowLow = (input >> 2) & 0x03;  // 前两位
        int colLow = input & 0x03;        // 后两位

        // 从 S-Box 中查找替换值并返回
        return SBox[rowLow][colLow];

    }


    public static int rotNib(int input) {
        return ((input << 4) | (input >> 4)) & 0xFF; // 只保持 8 位
    }


    // 密钥加操作
    public static int[] round1(int[] C, int[] key) {
        // 检查密钥的长度（应为两个字节，每个 8 位）
        if (key.length != 2) {
            throw new IllegalArgumentException("密钥长度必须为2个字节！");
        }

        // 将密钥转换为16位的二进制数组
        int[] expandedKey = new int[16];
        int[] firstByteBinary = toBinary(key[0]); // 第一个字节的二进制表示
        int[] secondByteBinary = toBinary(key[1]); // 第二个字节的二进制表示

        // 填充密钥二进制数组，前8位为第一个字节，后8位为第二个字节
        System.arraycopy(firstByteBinary, 0, expandedKey, 0, firstByteBinary.length);
        System.arraycopy(secondByteBinary, 0, expandedKey, 8, secondByteBinary.length);

        // 执行逐位异或操作
        int[] result = new int[16];
        for (int i = 0; i < 16; i++) {
            result[i] = C[i] ^ expandedKey[i]; // 明文和密钥逐位异或
        }

        return result; // 返回异或后的结果
    }

    // 将十进制字节转换为 8 位二进制数组（辅助函数）
    public static int[] toBinary(int decimal) {
        int[] binary = new int[8]; // 一个字节有8位
        for (int i = 7; i >= 0; i--) {
            binary[i] = decimal & 1; // 取最低位
            decimal >>= 1;           // 右移一位
        }
        return binary;
    }

    // 使用subNib函数对16位明文进行半字节代替操作
    public static int[] round2(int[] C, int[][] SBox) {
        if (C.length != 16) {
            throw new IllegalArgumentException("明文长度必须为16位！");
        }

        int[] result = new int[16];

        // 将明文分成4个半字节，每4位一组
        for (int i = 0; i < 16; i += 4) {
            // 将4位二进制转换为十进制数
            int[] nibble = {C[i], C[i + 1], C[i + 2], C[i + 3]};
            int nibbleValue = toDecimal2(nibble);

            // 使用subNib函数进行代替
            int substitutedNibble = subNib2(nibbleValue, SBox);

            // 将代替后的十进制数转换回4位二进制，并存入结果
            int[] substitutedNibbleBinary = toBinary2(substitutedNibble, 4);
            System.arraycopy(substitutedNibbleBinary, 0, result, i, 4);
        }

        return result; // 返回经过SubNib处理的结果
    }

    // 将十进制数转换为指定长度的二进制数组
    private static int[] toBinary2(int decimal, int length) {
        int[] binary = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            binary[i] = decimal & 1; // 取最低位
            decimal >>= 1;           // 右移一位
        }
        return binary;
    }

    // 将4位的二进制数组转换为十进制数（辅助函数）
    private static int toDecimal2(int[] binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length; i++) {
            decimal += binary[i] * Math.pow(2, binary.length - 1 - i);
        }
        return decimal;
    }

    public static int[] round3(int[] P) {
        if (P.length != 16) {
            throw new IllegalArgumentException("明文长度必须为16位！");
        }

        // 临时数组，用于保存第5到8位（索引为4到7）
        int[] temp = new int[4];
        for (int i = 0; i < 4; i++) {
            temp[i] = P[4 + i];
        }

        // 将第13到16位（索引为12到15）赋值给第5到8位（索引为4到7）
        for (int i = 0; i < 4; i++) {
            P[4 + i] = P[12 + i];
        }

        // 将临时数组中的第5到8位值赋值给第13到16位（索引为12到15）
        for (int i = 0; i < 4; i++) {
            P[12 + i] = temp[i];
        }

        return P; // 返回经过行位移操作后的明文数组
    }

    // round4: 列混淆
    public static int[] round4(int[] state, int en) {
        if (state.length != 16) {
            throw new IllegalArgumentException("状态数组长度必须为16位！");
        }

        // 定义 GF(2^4) 中的列混淆矩阵
        int[] mixedState = new int[16];

        // 定义GF(2^4)中的有限域乘法
        if (en == 1) {
            // 第一个变换条件
            // 将明文分成4个半字节，每4位一组
            for (int i = 0; i < 16; i += 8) {
                // 将4位二进制转换为十进制数
                int[] state1 = {state[i], state[i + 1], state[i + 2], state[i + 3]};
                int[] state2 = {state[i + 4], state[i + 5], state[i + 6], state[i + 7]};
                int state10 = toDecimal2(state1);
                int state20 = toDecimal2(state2);
                int res1 = state10 ^ gfMult(state20, 4);
                int res2 = state20 ^ gfMult(state10, 4);
                // 将代替后的十进制数转换回4位二进制，并存入结果
                int[] res10 = toBinary2(res1, 4);
                int[] res20 = toBinary2(res2, 4);
                System.arraycopy(res10, 0, mixedState, i, 4);
                System.arraycopy(res20, 0, mixedState, i + 4, 4);
            }

        } else {
            // 第二个变换条件
            // 将明文分成4个半字节，每4位一组
            for (int i = 0; i < 16; i += 8) {
                // 将4位二进制转换为十进制数
                int[] state1 = {state[i], state[i + 1], state[i + 2], state[i + 3]};
                int[] state2 = {state[i + 4], state[i + 5], state[i + 6], state[i + 7]};
                int state10 = toDecimal2(state1);
                int state20 = toDecimal2(state2);
                int res1 = gfMult(state10, 9) ^ gfMult(state20, 2);
                int res2 = gfMult(state10, 2) ^ gfMult(state20, 9);
                // 将代替后的十进制数转换回4位二进制，并存入结果
                int[] res10 = toBinary2(res1, 4);
                int[] res20 = toBinary2(res2, 4);
                System.arraycopy(res10, 0, mixedState, i, 4);
                System.arraycopy(res20, 0, mixedState, i + 4, 4);
            }
        }
        return mixedState;
    }

    private static int gfMult(int a, int b) {
        int res = 0;
        while (b > 0) {
            if ((b & 1) != 0) {
                res ^= a; // 累加到结果
            }
            a <<= 1; // 左移 a
            if ((a & 0x10) != 0) { // 检查 a 的最高位 x^4
                a ^= 0x13; // 如果有进位，模多项式 x^4 + x + 1 (10011)
            }
            b >>= 1; // b 右移
        }
        return res & 0xF; // 保留低4位
    }


    //加密方法
    public static int[] encrypt(int[] plaintext, int[] key) {
        // 1. 生成轮密钥
        int[] roundKeys = keyExpansion(key);
        int[] roundKey1 = new int[2];
        int[] roundKey2 = new int[2];
        System.arraycopy(roundKeys, 2, roundKey1, 0, 2); //第一轮密钥
        System.arraycopy(roundKeys, 4, roundKey2, 0, 2); //第二轮密钥
        // 2. 初始轮密钥加
        int[] state = round1(plaintext, key);
        //第一轮
        // 进行S盒代换
        state = round2(state, SBox1);
        // 行位移
        state = round3(state);
        // 列混淆
        state = round4(state, 1);
        // 轮密钥加
        state = round1(state, roundKey1);
        // 第二轮
        // 进行S盒代换
        state = round2(state, SBox1);
        // 行位移
        state = round3(state);
        // 轮密钥加
        state = round1(state, roundKey2);
        // 7. 返回加密结果
        return state;
    }

    // 解密方法
    public static int[] decrypt(int[] ciphertext, int[] key) {
        // 1. 生成轮密钥
        int[] roundKeys = keyExpansion(key);
        int[] roundKey1 = new int[2];
        int[] roundKey2 = new int[2];
        System.arraycopy(roundKeys, 2, roundKey1, 0, 2); // 第一轮密钥
        System.arraycopy(roundKeys, 4, roundKey2, 0, 2); // 第二轮密钥

        // 2. 初始轮密钥加（最后轮）
        int[] state = round1(ciphertext, roundKey2);

        // 第二轮
        // 行位移逆操作
        state = round3(state);
        // S盒逆代换（使用 SBox2 进行逆代换）
        state = round2(state, SBox2);
        // 轮密钥加
        state = round1(state, roundKey1);
        // 第一轮
        // 列混淆逆操作
        state = round4(state, 0);
        // 行位移逆操作
        state = round3(state);
        // S盒逆代换（使用 SBox2 进行逆代换）
        state = round2(state, SBox2);
        // 轮密钥加
        state = round1(state, key);
        // 返回解密结果
        return state;
    }

}
