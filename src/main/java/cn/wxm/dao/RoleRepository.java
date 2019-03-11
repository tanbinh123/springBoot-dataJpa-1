package cn.wxm.dao;


import org.springframework.data.repository.Repository;

import cn.wxm.pojo.Roles;
/**
 * 参数：
 * T: 当前需要映射的实体
 * ID:当前映射实体的OID类型
 * Repository:接口的方法 名称命名查询
 * @author WXM
 *RoleRepository
 */
public interface RoleRepository extends Repository<Roles, Integer> {
	//方法名：findBy(相当于关键字)+属性(首字母大写)+查询条件(首字母大写)
	void save(Object obj);
	
	Roles findByRoleid(Integer id);
}
