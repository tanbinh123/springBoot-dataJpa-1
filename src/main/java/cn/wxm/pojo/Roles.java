package cn.wxm.pojo;

/**
 * 用户角色实体类
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_roles")
public class Roles {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="roleid")
	private Integer roleid;
	@Column(name="rolename")
	private String rolename;
	
	/*
	 * 一个角色可以有多个用户
	 */
	@OneToMany(mappedBy="roles")
	private Set<User> users=new HashSet<>(0);

	//fetch=FetchType.EAGER：使用立即加载来解决no session的问题
	@ManyToMany(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)//设置级联
	/**
	 * @JoinTable：映射中间表，在role或者menu中写都行，另一方只需要mappedBy对方的对象即可
	 * joinColumns：本表在中间表的映射外建字段
	 * inverseJoinColumns：对方表在中间表的映射外键字段
	 */
	@JoinTable(name="t_role_menu",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="menu_id"))
	private Set<Menu> menu=new HashSet<>();
	
	public Set<Menu> getMenu() {
		return menu;
	}

	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Roles [roleid=" + roleid + ", rolename=" + rolename + "]";
	}

}
