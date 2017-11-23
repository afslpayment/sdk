package com.payment.sdk;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class netTest {
    private static net net;
    @BeforeEach
    void setUp() {
        net = new net();
    }

    @Test
    void get() {
        try {
            assertEquals("send", net.get("http://pay.afsl.vip/test.php"));
        } catch (Exception e) {

        }
    }

    @Test
    void post() {
        Map param = new HashMap();
        param.put("test", "ok");
        try {
            assertEquals("ok", net.post("http://pay.afsl.vip/test.php", param));
        } catch (Exception e) {

        }
    }

    @Test
    void urlencode() {
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("bb", "aa");
        map.put("aa", "bb");
        assertEquals("bb=aa&aa=bb", net.urlencode(map));
    }

}