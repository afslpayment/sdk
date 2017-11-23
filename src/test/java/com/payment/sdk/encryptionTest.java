package com.payment.sdk;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import java.security.NoSuchAlgorithmException;

class encryptionTest {
    private static encryption encryption;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        encryption = new encryption("aaa", "bbb");
    }

    @org.junit.jupiter.api.Test
    void sign() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("bb", "aa");
        map.put("aa", "bb");
        try {
            assertEquals("d36367b54d45ac5ae084feabee42db4d",this.encryption.sign(map));
        } catch (NoSuchAlgorithmException e) {

        }
    }

    @org.junit.jupiter.api.Test
    void sortMapByKey() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("bb", "aa");
        map.put("aa", "bb");
        assertEquals("{aa=bb, bb=aa}",encryption.sortMapByKey(map));
    }

    @org.junit.jupiter.api.Test
    void md5() {
        try{
            assertEquals("e10adc3949ba59abbe56e057f20f883e", encryption.md5("123456"));
        } catch (NoSuchAlgorithmException e) {

        }
    }

    @org.junit.jupiter.api.Test
    void check() {
        Map<String, String> map = new HashMap<String,String>();
        map.put("bb", "aa");
        map.put("aa", "bb");
        try {
            assertEquals(true, this.encryption.check(map, "d36367b54d45ac5ae084feabee42db4d"));
        } catch (NoSuchAlgorithmException e) {

        }
    }

}