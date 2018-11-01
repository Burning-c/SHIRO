package com.testSM;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.alibaba.fastjson.JSON;
import com.dao.RoleMapper;
import com.pojo.Mapping_UR;
import com.pojo.Role;
import com.pojo.User;
import com.service.IUserService;

//表示继承了SpringJUnit4ClassRunner类
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/spring-mybatis.xml" })
public class TestMyBatis {

	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	@Resource
	private IUserService userService = null;
	@Resource
	private User user;
	@Resource
	private Role role;
	@Resource
	private Mapping_UR mapping_UR;
	@Resource
	private RoleMapper roleMapper;
	
	@Test
	public void test() {
		/*List<Mapping_UR> list=new ArrayList<Mapping_UR>();
		user.setPassword("11");
		user.setUsername("as");
		role.setName("role");
		mapping_UR.setRole(role);
		mapping_UR.setUser(user);
		list.add(mapping_UR);
		role.setMapping_UR(list);	
		user.setMapping_UR(list);
		roleMapper.insert(role);
		userService.correlationRoles(1, 1);
		*/
		User user1 = userService.getUser(1);
		System.out.println(user1);
		logger.info(JSON.toJSONString(user1));
	}
}
