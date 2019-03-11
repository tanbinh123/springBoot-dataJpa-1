package cn.wxm.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import cn.wxm.pojo.User;
/**
 * 参数：
 * T: 当前需要映射的实体
 * ID:当前映射实体的OID类型
 * @author WXM
 *
 */
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Integer> {

}
