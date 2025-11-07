package com.refine.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HashUtil {

    public HashUtil() {}

    public static String digestStringSHA256(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digestString(data, "SHA-256");
    }

    public static String digestString(String data, String algorithm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte digest[] = md.digest(data.getBytes("iso-8859-1"));
        String hash = "";
        for (int i = 0; i < digest.length; i++) {
            hash += String.format("%02x", digest[i] & 0xff).toUpperCase();
        }
        return hash;
    }

    /***
     * 
     */
    public static boolean chkSHA256(String hash, String key) {
        String sha256 = "";
        try {
            String _date = new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
            System.out.println(" ****** date : " + _date);
            sha256 = digestStringSHA256(key + _date);
            System.out.println(" ****** key : " + key);
            System.out.println(" ****** date : " + _date);
            System.out.println(" ****** sha256 : " + sha256);
            System.out.println(" ****** hash : " + hash);
            if (sha256.equals(hash))
                return true;
//		System.err.println("input hash = " + hash + "SHA256 = " + sha256);
//		logger.error("input hash = " + hash + "SHA256 = " + sha256);
        } catch (NoSuchAlgorithmException e1) {
            System.err.println(e1.getMessage());
        } catch (UnsupportedEncodingException e1) {
            System.err.println(e1.getMessage());
        }
        return false;
    }
}