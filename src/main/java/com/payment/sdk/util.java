package com.payment.sdk;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.kopitubruk.util.json.JSONUtil;
import java.util.Date;
import java.text.SimpleDateFormat;

public class util {
    private static net net;
    private static encryption encryption;
    private static String appID;
    private static String appSecret;
    public util(String appID, String appSecret) {
        this.net = new net();
        this.encryption = new encryption(appID, appSecret);
        this.appID = appID;
        this.appSecret = appSecret;
    }
    public static String signToString(String oid, String amount, String url, String notifyurl) throws NoSuchAlgorithmException {
        Map param = new HashMap();
        param.put("v_oid", oid);
        param.put("v_amount", amount);
        param.put("v_url", url);
        param.put("v_notifyurl", notifyurl);
        param.put("v_ymd", nowYMD());
        try{
            param.put("sign", encryption.sign(param));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        param.put("appid", appID);
        return JSONUtil.toJSON(param);
    }
    public static Map signToMap(String oid, String amount, String url, String notifyurl) throws NoSuchAlgorithmException {
        Map param = new HashMap();
        param.put("v_oid", oid);
        param.put("v_amount", amount);
        param.put("v_url", url);
        param.put("v_notifyurl", notifyurl);
        param.put("v_ymd", nowYMD());
        try{
            param.put("sign", encryption.sign(param));
        } catch (NoSuchAlgorithmException e) {
            throw e;
        }
        param.put("appid", appID);
        return param;
    }
    public static String nowYMD() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}
