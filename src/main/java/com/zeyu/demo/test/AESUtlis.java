package com.zeyu.demo.test;


import com.alibaba.druid.util.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES算法进行加密
 *
 * @author chenhu
 * @create 2018-01-23 上午11:00
 **/
public class AESUtlis {

    /**
     * 密钥根据不同的密匙生成不同的密文
     */
    // AES加密要求key必须要128个比特位（这里需要长度为16，否则会报错）
//    private static final String KEY = "1234567887654320";
    private static final String KEY = "swhysc_manage123";

    //密码器种子
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";
    //加密类型
    private static final String AES = "AES";

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        //需要加密的字符串
        String content = "url：findNames.act";
        System.out.println("加密前：" + content);

        System.out.println("加密密钥和解密密钥：" + KEY);
        //加密方法
        String encrypt = aesEncrypt(content, KEY);
        System.out.println("加密后：" + encrypt);
        //解密方法
        String decrypt = aesDecrypt(encrypt, KEY);

        System.out.println("解密后：" + decrypt);
    }

//=================================================================


    /**
     * AES加密为base 64 code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * 将base 64 code AES解密
     *
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }
    //========================================================================


    /**
     * AES加密
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        //创建生产者
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        //把生产者初始化成128位（192/256）
        kgen.init(128);
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        //把密码器初始化成加密模式
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), AES));
        //调用方法进行加密
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * AES解密
     *
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey   解密密钥
     * @return 解密后的String
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES);
        kgen.init(128);
        //创建密码器
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), AES));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    /**
     * base 64 encode
     *
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    private static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception 抛出异常
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


}
