package cn.wxm.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_menu")
public class Menu {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menuid")
	private Integer menuid;
	@Column(name="menuname")
	private String menuname;
	@Column(name="menuurl")
	private String menuurl;
	@Column(name="parentid")
	private Integer parentid;//父节点
	
	@ManyToMany(mappedBy="menu")//映射role  Set集合的对象
	private Set<Roles> roles=new HashSet<Roles>(0);
	
	public Integer getMenuid() {
		return menuid;
	}
	
	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", menuname=" + menuname + ", menuurl=" + menuurl + ", parentid=" + parentid
				+ "]";
	}
	
}
