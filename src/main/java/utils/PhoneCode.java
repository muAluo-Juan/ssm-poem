package utils;

import java.util.Random;

//用于产生手机验证码
public class PhoneCode {
	public String codes ="0123456789";
	public int codeLength = 5;
	public Random random = new Random();
	
	public String getPhoneCode() {
		String phoneCode = "";
		for(int i = 1 ; i <= codeLength; i++)
		{
			int pos = random.nextInt(codes.length());
			phoneCode += codes.charAt(pos);
		}
		return phoneCode;
	}
}
