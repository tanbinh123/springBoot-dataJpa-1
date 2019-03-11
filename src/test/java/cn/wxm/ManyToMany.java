package cn.wxm;

import java.util.Set;

import javax.persistence.FetchType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wxm.dao.RoleRepository;
import cn.wxm.pojo.Menu;
import cn.wxm.pojo.Roles;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)
public class ManyToMany {

	@Autowired
	private RoleRepository roleRepository;
	
	//@Test
	public void testSave(){
		//创建role
		Roles role=new Roles();
		role.setRolename("项目经理");
		
		//创建menu
		Menu menu=new Menu();
		menu.setMenuname("xxxx管理系统");
		menu.setParentid(0);
		
		Menu menu2=new Menu();
		menu2.setMenuname("项目管理");
		menu2.setParentid(1);
		
		//关联
		role.getMenu().add(menu);
		role.getMenu().add(menu2);
		menu.getRoles().add(role);
		menu2.getRoles().add(role);
		
		//保存
		this.roleRepository.save(role);
	}
	
	@Test
	public void testFind(){
		Roles role = this.roleRepository.findByRoleid(2);
		System.out.println(role);
		//对象导航图:
		/**
		 * 需要在role映射上面加上fetch=FetchType.EAGER:立即加载来解决no session 的问题
		 */
		Set<Menu> munes=role.getMenu();
		for (Menu menu : munes) {
			System.out.println(menu);
		}
	}
}
