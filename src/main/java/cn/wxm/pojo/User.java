package cn.wxm.pojo;
/**
 * 用户实体类
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity //声明实体类
@Table(name="t_user") //映射的表
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键策略
	@Column(name="t_id")//映射数据库的字段
	private Integer id;
	@Column(name="t_name")//映射数据库的字段
	private String name;
	@Column(name="t_age")//映射数据库的字段
	private Integer age;
	@Column(name="t_address")//映射数据库的字段
	private String address;
	
	@ManyToOne(cascade=CascadeType.PERSIST)//级联操作，保存客户的时候也能进行保存角色操作
	//@JoinColumn:维护的外键
	@JoinColumn(name="roles_id")
	private Roles roles;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", address=" + address + "]";
	}
	
}
