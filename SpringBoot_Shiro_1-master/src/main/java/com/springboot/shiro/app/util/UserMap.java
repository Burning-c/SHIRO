package com.springboot.shiro.app.util;

import java.util.HashMap;
import java.util.Map;

import com.springboot.shiro.model.User;

/**
 * @author 作者 :HouZhiBo
 * @version 创建时间:2018年10月1日 下午3:59:14 类说明:模拟数据库
 */
public class UserMap {

//	static {
//		// 使用Map模拟数据库获取User表信息
//		userMap.put("admin1", new User("admin1", "admin1", false));
//		userMap.put("admin2", new User("admin2", "admin2", false));
//		userMap.put("admin3", new User("admin3", "admin3", true));
//	}
	// md5simplehash明文
	private static Map<String, User> userMap = new HashMap<String, User>();

//	public static Map<String, User> getUserMap() {
//		userMap.put("admin1", new User("admin1", "admin1", false));
//		userMap.put("admin2", new User("admin2", "admin2", false));
//		userMap.put("admin3", new User("admin3", "admin3", true));
//		return userMap;
//	}

	// md5simplehash密文
	private static Map<String, User> userMd5Map = new HashMap<String, User>();

	public static Map<String, User> getUserMd5Map() {
		return userMd5Map;
	}

	public static void setUserMd5Map(Map<String, User> userMd5Map) {
		UserMap.userMd5Map = userMd5Map;
	}

}
