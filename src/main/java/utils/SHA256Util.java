package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*SHA-256密码加密*/
public class SHA256Util {
	//获取加密结果
	public static String getSHA256Str(String pwd) {
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(pwd.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("加密结果："+encodeStr);
		return encodeStr;
	}
	
	public static String byte2Hex(byte bytes[]) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for(int i = 0 ; i < bytes.length ; i ++) {
			temp = Integer.toHexString(bytes[i] & 0xff);
			if(temp.length() == 1)
				stringBuffer.append("0");
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
}
