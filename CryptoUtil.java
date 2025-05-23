package org.cucumbertaf.utils;

import java.util.Base64;


public class CryptoUtil {

    public static void main(String... args) throws Exception {

        String encrptData= "urchizyebctpngmx";
        byte[] encodedBytes = Base64.getEncoder().encode(encrptData.getBytes());
        String encrypted = new String(encodedBytes);
        System.out.println("encodedBytes --------------->" + encrypted);
        byte[] decodeBytes = Base64.getDecoder().decode(encrypted);
        String dencrypted = new String(decodeBytes);
        System.out.println("decodedBytes --------------->" + dencrypted);

    }

    public static String getDecryptedPassword(String key){
    	if(key == null) {
    		return "";
    	}
        byte[] decodeBytes = Base64.getDecoder().decode(key);
        return new String(decodeBytes);
    }

}
