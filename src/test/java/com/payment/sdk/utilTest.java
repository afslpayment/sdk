package com.payment.sdk;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class utilTest {
    private static util util;
    @BeforeEach
    void setUp() {

        this.util = new util("170719041602861764", "RG3p9g6itKiQbweTIrjyA1UkAJbRB6Zt");
    }

    @Test
    void signToString() {
        try{
            assertEquals("{\"v_amount\":\"1000\",\"v_notifyurl\":\"http:\\/\\/www.jadebirdpay.com\\/PayUser\\/Business\\/backpaytest\",\"appid\":\"170719041602861764\",\"sign\":\"c1c9b82c77510871951b164df5585ad2\",\"v_oid\":\"201711010001\",\"v_ymd\":\"2017-11-23\",\"v_url\":\"http:\\/\\/www.jadebirdpay.com\\/PayUser\\/Business\\/backpay\"}", util.signToString(
                    "201711010001",
                    "1000",
                    "http://www.jadebirdpay.com/PayUser/Business/backpay",
                    "http://www.jadebirdpay.com/PayUser/Business/backpaytest"
            ));
        } catch (NoSuchAlgorithmException e ) {

        }
    }

    @Test
    void signToMap() {
        try{
            Map accessToken = util.signToMap(
                    "201711010001",
                    "1000",
                    "http://www.jadebirdpay.com/PayUser/Business/backpay",
                    "http://www.jadebirdpay.com/PayUser/Business/backpaytest"
            );
            assertEquals("c1c9b82c77510871951b164df5585ad2",accessToken.get("sign"));
        } catch (NoSuchAlgorithmException e ) {

        }
    }

}