package com.example.demo.util;

import java.security.SecureRandom;

public class PasswordGenerate {

	private static SecureRandom random = new SecureRandom();

//    /** different dictionaries used */	
//    private static final String NUMERIC = "0123456789";
//    private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

    public static String generatePassword(int len, String dic) {
		String result = "";
		for (int i = 0; i < len; i++) {
		    int index = random.nextInt(dic.length());
		    result += dic.charAt(index);
		}
		return result;
    }

}

