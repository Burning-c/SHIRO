package com.springboot.shiro.app.util;
/**
* @author 作者 :HouZhiBo
* @version 创建时间:2018年10月1日 下午2:48:28
* 类说明:
*/

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import com.springboot.shiro.model.User;

public class SimpleHashMd5 {

	/**
	 * 明文进行谜面进行加密
	 * 
	 * @param args
	 */
//	public static void main(String[] args) {
//		int hashIterations = 2;//加密的次数
//		Object salt = "admin";//盐值
//		Object credentials = "123456";//密码
//		String hashAlgorithmName = "MD5";//加密方式
//		Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,
//				salt, hashIterations);
//		System.out.println("加密后的值----->" + simpleHash);
//	}

	private static Map<String, User> userMap = new HashMap<String, User>();
	public static void main(String[] args) {
		User user = null;
		for (int i = 1; i < 5; i++) {
			user = new User();
			user.setUsername("admin" + i);
			user.setPassword("admin" + i);
			userMap.put("admin"+i, user);
		}
		User u = null;
		Iterator<String> it = userMap.keySet().iterator();
		while (it.hasNext()) {
			u = userMap.get(it.next());
			String hashAlgorithmName = "MD5";// 加密方式
			Object crdentials = u.getPassword();// 密码原值
			ByteSource salt = ByteSource.Util.bytes(u.getUsername() + u.getPassword());// 以账号作为盐值
			int hashIterations = 1024;// 加密1024次
			Object result = new SimpleHash(hashAlgorithmName, crdentials, salt, hashIterations);
			System.out.println(u.getUsername() + ":" + result + "," + salt);

		}
	}

}
