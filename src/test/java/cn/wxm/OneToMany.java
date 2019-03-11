package cn.wxm;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wxm.dao.UserRepository;
import cn.wxm.dao.UserRepositoryByName;
import cn.wxm.pojo.Roles;
import cn.wxm.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class OneToMany {

	@Autowired
	private UserRepository userRepository;
	//由于使用jpaRepository接口老是返回Optional值无法调用user的方法，这里使用Repository接口
	@Autowired
	private UserRepositoryByName UserRepositoryByName;
	
	
	
	//@Test
	public void testSaveUser_Role(){
		//创建用户
		User user=new User();
		user.setName("wxm");
		user.setAge(23);
		user.setAddress("河北");
		
		//创建角色
		Roles role=new Roles();
		role.setRolename("管理员");
		
		//关联
		role.getUsers().add(user);
		user.setRoles(role);
		
		//保存客户
		this.userRepository.save(user);
	}
	
	@Test
	public void testFindRoleByUser(){
		User user = this.UserRepositoryByName.findById(7);
		//由于这种方式是对象导航图模式，所以role也被查出来了
		System.out.println(user);
		Roles role=user.getRoles();
		System.out.println(role);
	
	}
}
