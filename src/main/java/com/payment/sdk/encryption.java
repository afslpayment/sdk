package com.payment.sdk;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.kopitubruk.util.json.JSONUtil;

class MapKeyComparator implements Comparator<String>{
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}

public class encryption {
    protected static String appID;
    protected static String appSecret;
    public encryption(String appID, String appSecret) {
        this.appID = appID;
        this.appSecret = appSecret;
    }
    public static String sign(Map param) throws NoSuchAlgorithmException  {

        String o = JSONUtil.toJSON(sortMapByKey(param));

        try {
            return md5(o);
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }

    public static String md5(String o) throws NoSuchAlgorithmException {
        try{
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] bs =digest.digest(o.getBytes());
            StringBuffer hexString = new StringBuffer();
            for (byte b : bs) {
                 // 第一步，将数据全部转换成正数：
                 // 解释：为什么采用b&255
                 /*
                  * b:它本来是一个byte类型的数据(1个字节) 255：是一个int类型的数据(4个字节)
                  * byte类型的数据与int类型的数据进行运算，会自动类型提升为int类型 eg: b: 1001 1100(原始数据)
                  * 运算时： b: 0000 0000 0000 0000 0000 0000 1001 1100 255: 0000
                  * 0000 0000 0000 0000 0000 1111 1111 结果：0000 0000 0000 0000
                  * 0000 0000 1001 1100 此时的temp是一个int类型的整数
                  */
                int temp = b & 255;
                // 第二步，将所有的数据转换成16进制的形式
                // 注意：转换的时候注意if正数>=0&&<16，那么如果使用Integer.toHexString()，可能会造成缺少位数
                // 因此，需要对temp进行判断
                if (temp < 16 && temp >= 0) {
                    // 手动补上一个“0”
                    hexString.append('0');
                    hexString.append(Integer.toHexString(temp));
                } else {
                    hexString.append(Integer.toHexString(temp));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }

    public static boolean check(Map param, String accessToken) throws NoSuchAlgorithmException {
        try {
            return  accessToken.equals(sign(param));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
    }
}