package epsi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hasher {

    public static String getHash(String user, String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException  {
        String realm = "Fiche agent protégée, veuillez vous identifier";

        byte b[] = java.security.MessageDigest.getInstance("MD5").digest( (user + ":" + realm + ":" + password ).getBytes());
        java.math.BigInteger bi = new java.math.BigInteger(1, b);
        String s = bi.toString(16);
        while (s.length() < 32)
            s = "0" + s;

        return s;
    }
}
