package utils;

import java.io.UnsupportedEncodingException;

public class Utils {
	public static String DoGetString(String str) throws UnsupportedEncodingException{
		byte [] bs = str.getBytes("ISO8859-1");
		return new String(bs,"UTF-8");
	}
}
