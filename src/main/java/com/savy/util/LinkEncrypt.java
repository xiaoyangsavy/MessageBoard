package com.savy.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密和解密
 * @author chenqi
 *
 */
public class LinkEncrypt {

	private static final String SECTET = "Coolading-SECTET"; 
	
	public static String getUserIdByToken(String token) throws IOException {
		String[] keys = LinkEncrypt.unToken(token);
		if (null != keys && keys.length == 3) {
			return keys[2];
		}
		return null;
	}

	/**
	 * Description:将加密参数进行解密,并返回
	 * 
	 * @author chenqi
	 * @version 1.0
	 * @param token
	 *            加密的参数
	 * @return 解密后的参数
	 * @throws IOException
	 */
	public static String[] unToken(String token) throws IOException {
		if (token == null || token.trim().equals(""))
			return null;
		String str = unEncrypt(token);
		if (str == null || str.trim().equals(""))
			return null;
		return str.split(":");
	}

	/**
	 * @author chenqi
	 * @version 1.0
	 * @param key
	 *            用户名
	 * @return 返回 [双方协定的连接密码]+[:]+[系统时间]+[:]+[用户名]
	 * @throws Exception
	 */
	public static String generateToken(String key)
			throws Exception {
		if (key == null || key.trim().equals(""))
			return "";
		String time = String.valueOf(System.currentTimeMillis() / 1000);
		StringBuffer sb = new StringBuffer();
		sb.append(SECTET).append(":");
		sb.append(time).append(":");
		sb.append(key);
		String token = encrypt(sb.toString());
		return token;
	}

	/**
	 * Description:对字符串进行BASE64加密
	 * 
	 * @author chenqi
	 * @version 1.0
	 * @param str
	 *            需加密的字符串
	 * @return 返回加密后的字符串
	 * @throws Exception
	 */
	private static String encrypt(String str) throws Exception {
		if (str == null || str.equals("")) {
			return "";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}

	/**
	 * Description:对BASE64加密后的字符串进行解密
	 * 
	 * @author chenqi
	 * @version 1.0
	 * @param str
	 *            需解密的字符串
	 * @return 返回解密后的字符串
	 * @throws IOException
	 */
	private static String unEncrypt(String str) throws IOException {
		if (str == null || str.equals("")) {
			return "";
		}
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(str);
		return new String(b);
	}
}