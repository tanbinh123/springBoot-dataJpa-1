package cn.wxm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.wxm.pojo.User;
/**
 * 参数：
 * T: 当前需要映射的实体
 * ID:当前映射实体的OID类型
 * Repository:接口的方法  @Query   HQl和SQl
 * @author WXM
 *
 */
public interface UserRepositoryQueryAnnotation extends Repository<User, Integer> {

	/**
	 * 当? 为?0 或者?1 时测试通过,或者"name=:name" 在方法处使用@Param("name") String name
	 * @param name
	 * @return
	 */
	@Query(value="from User where name = :name")  //此处的user并非数据库的表，而是实体类，查询语法是HQL
	List<User> queryByNameUseHQL( @Param("name") String name);
	
	/**
	 * 在@Query注解中nativeQuery默认为false，由hibernate解析为标准sql
	 * nativeQuery=true:不用hibernate在解析为sql了，告知就是一个标准sql来执行
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_user where t_name=?",nativeQuery=true)
	List<User> queryByNameUseSQL( String name);
	
	
	//该查询本身可以用来更新操作
	@Query("update User set name=:name where id=:id") //这里的User是是实体类  name为其属性
	@Modifying//执行更新操作
	void updateUserNameById(@Param("name") String name,@Param("id")Integer id);
	
}
