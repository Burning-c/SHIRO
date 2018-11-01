package com.springboot.shiro.app.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 作者 :HouZhiBo
 * @version 创建时间:2018年10月1日 下午3:59:14 类说明:模拟数据库
 */
public class EncryptAes256 {
	private static final String key = "74kjs&33$2240JsfkUgalaujYRDCasa#";
	private static final String iv = "4I!9V6X8Af98^5bC";
	private static final int block = 16;

	/**
	 * 加密
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static byte[] Encrypt(String src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes("UTF-8")));

		byte[] srawt = src.getBytes("UTF-16LE");
		int len = srawt.length;// 10
		if (len % block != 0) {
			len = (len / block + 1) * block;// 16
		}
		byte[] sraw = new byte[len];
		for (int i = 0; i < len; ++i) {
			if (i < srawt.length) {
				sraw[i] = srawt[i];

			} else {
				sraw[i] = 0;
			}
		}
		return cipher.doFinal(sraw);
	}

	/**
	 * 解密
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String Decrypt(byte[] src) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
		byte[] decrpted = cipher.doFinal(src);
		int len = decrpted.length;
		for (int i = len; i > 0; i -= 2) {
			if (decrpted[i - 1] != 0 || decrpted[i - 2] != 0) {
				len = i;
				break;
			}
		}
		return new String(decrpted, 0, len, "UTF-16LE");
	}

	public static String EncodeHex(byte[] src) {
		String chars = "0123456789ABCDEF";
		int pos = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length; i++) {
			pos = (src[i] & 0xf0) >>> 4;
			sb.append(chars.charAt(pos));
			pos = (src[i] & 0x0f);
			sb.append(chars.charAt(pos));
		}
		return sb.toString();
	}

	public static byte[] DecodeHex(String src) {
		String chars = "0123456789ABCDEF";
		byte[] bytes = new byte[src.length() / 2];
		int n = 0;
		for (int i = 0; i < bytes.length; i++) {
			n = chars.indexOf(src.charAt(2 * i)) * 16;
			n += chars.indexOf(src.charAt(2 * i + 1));
			bytes[i] = (byte) (n & 0xff);
		}
		return bytes;
	}
	// JAVA:
	// 1.高位在前，低位在后
	// C++：
	// 1.Windows：低位在前，高位在后
	// 2.Linux/Unix：高位在前，低位在后

	public static void main(String[] args) {

		try {
			String OldPassWord = "123456";
			String strEncyptData = EncodeHex(Encrypt(OldPassWord));
			String strDecyptData = Decrypt(DecodeHex(strEncyptData));
			System.out.println("加密前：：" + OldPassWord);
			System.out.println("加密后：：" + strEncyptData);
			System.out.println("解密后：：" + strDecyptData);

		} catch (Exception e) {

		}
	}

}
