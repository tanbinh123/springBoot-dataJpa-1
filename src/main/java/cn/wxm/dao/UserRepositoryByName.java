package cn.wxm.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import cn.wxm.pojo.User;
/**
 * 参数：
 * T: 当前需要映射的实体
 * ID:当前映射实体的OID类型
 * Repository:接口的方法 名称命名查询
 * @author WXM
 *
 */
public interface UserRepositoryByName extends Repository<User, Integer> {

	//方法名：findBy(相当于关键字)+属性(首字母大写)+查询条件(首字母大写)
	List<User> findByName(String name);
	
	//当俩条件 是且时 查询成立      且 And 或 Or （注意大小写）
	List<User> findByNameAndAge(String name,Integer age);
	
	//模糊查询  StarWith  EndWith
	List<User> findByNameLike(String name);
	
	User findById(Integer id);
	
	void save(Object obj);
}
