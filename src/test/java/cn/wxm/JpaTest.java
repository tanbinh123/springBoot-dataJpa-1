package cn.wxm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wxm.dao.UserCrudRepository;
import cn.wxm.dao.UserPagingAndSortingRepository;
import cn.wxm.dao.UserRepository;
import cn.wxm.dao.UserRepositoryByName;
import cn.wxm.dao.UserRepositoryQueryAnnotation;
import cn.wxm.dao.UserRepositorySpecification;
import cn.wxm.pojo.User;

/**
 * 测试UserRepository
 * @author WXM
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class)//测试的springboot启动类
public class JpaTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRepositoryByName userRepositoryByName;
	
	@Autowired
	private UserRepositoryQueryAnnotation userRepositoryQueryAnnotation;
	
	@Autowired
	private UserCrudRepository userCrudRepository;
	
	@Autowired
	private UserPagingAndSortingRepository userPagingAndSortingRepository;
	
	@Autowired
	private UserRepositorySpecification userRepositorySpecification;
	
	
	
	/**
	 * 测试UserRepository jpaRepository接口的save方法
	 */
	//@Test
	public void contextLoads() {
		User u=new User();
		u.setName("和谐");
		u.setAge(23);
		u.setAddress("河南");
		this.userRepository.save(u);
	}
	
	
	/**
	 * 测试UserRepositoryByName接口的findByName方法
	 */
	//@Test
	public void findByName(){
		List<User> users = this.userRepositoryByName.findByName("和谐");
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * 根据name和年龄查询
	 */
	//@Test
	public void findByNameAndAge(){
		List<User> users = this.userRepositoryByName.findByNameAndAge("爱国", 34);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * 模糊查询
	 */
	//@Test
	public void findByNameLike(){
		List<User> users = this.userRepositoryByName.findByNameLike("敬业");
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * repository @query 使用hql查询
	 */
	//@Test
	public void queryByNameUseHQL(){
		List<User> users = this.userRepositoryQueryAnnotation.queryByNameUseHQL("敬业");
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * repository @query 使用sql查询
	 */
	//@Test
	public void queryByNameUseSQL(){
		List<User> users = this.userRepositoryQueryAnnotation.queryByNameUseSQL("爱国");
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * repository @query 执行一个更新操作 @Test+@Transactional=@Rollback(true)
	 */
	//@Test
	@Transactional
	@Rollback(false)
	public void updateUserNameById(){
		this.userRepositoryQueryAnnotation.updateUserNameById("爱国",2);
		
	}
	
	/**
	 * 测试userCrudRepository的save方法：判断是否有该数据，没有则为插入语句 ，否则为更新语句
	 */
	//@Test
	public void testCrudSave(){
		User user=new User();
		user.setId(6);
		user.setName("很友善");
		user.setAge(45);
		user.setAddress("天津");
		this.userCrudRepository.save(user);
		
	}
	
	/**
	 * 测试userCrudRepository的findAll方法
	 */
	//@Test
	public void testCrudFindAll(){
		
		List<User> list=(List<User>)this.userCrudRepository.findAll();
		for (User user : list) {
			System.out.println(user);
		}
		
	}
	
	/**
	 * 测试userCrudRepository的findById方法
	 */
	//@Test
	public void testCrudFindById(){
		
		Optional<User> u=this.userCrudRepository.findById(4);
		System.out.println(u);
		
	}
	
	/**
	 * 测试userCrudRepository的deleteById方法
	 */
	//@Test
	public void testCruddeleteId(){
		
		this.userCrudRepository.deleteById(1);
		
	}
	
	/**
	 * 测试userPagingAndSortingRepository接口的findAll方法：用于排序
	 */
	//@Test
	public void testPagingAndSortingRepositoryfindAll(){
		
		/**
		 * new Sort(排序规则,"根据的字段")
		 */
		Sort sort=new Sort(Direction.DESC,"id");//这个是现在的方法，使用order的过时了
		List<User> users = (List<User>)this.userPagingAndSortingRepository.findAll(sort);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * 测试userPagingAndSortingRepository接口的findAll方法：用于分页
	 */
	//@Test
	public void testPagingAndSortingRepositoryfindAllPage(){
		/**
		 * page:当前页(从0开始)     size：每页显示几条记录   of(page,size)
		 */
		Pageable pageable=PageRequest.of(0, 2);//原来的方法过时了
		/*
		 * 这个page封装了分页的信息，如总记录数，有多少页......
		 */
	    Page<User> page=this.userPagingAndSortingRepository.findAll(pageable);
	    System.out.println("总记录数："+page.getTotalElements());
	    System.out.println("总页数："+page.getTotalPages());
	    List<User> list=page.getContent();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	/**
	 * 测试userPagingAndSortingRepository接口的findAll方法：用于分页和排序 userRepository
	 */
	//@Test
	public void testPagingAndSortingRepositoryfindAllPageAndSort(){
		/**
		 * page:当前页(从0开始)     size：每页显示几条记录   sort:排序规则          of(page,size,sort) 
		 */
		//定义排序规则 
		Sort sort=new Sort(Direction.DESC,"id");
		
		Pageable pageable=PageRequest.of(0,2,sort);//原来的方法过时了
		/*
		 * 这个page封装了分页的信息，如总记录数，有多少页......
		 */
	    Page<User> page=this.userPagingAndSortingRepository.findAll(pageable);
	    System.out.println("总记录数："+page.getTotalElements());
	    System.out.println("总页数："+page.getTotalPages());
	    List<User> list=page.getContent();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	
	/**
	 * 测试userRepository jpa接口的findAll方法：用于排序
	 */
	//@Test
	public void testUserRepositoryfindAll(){
		
		/**
		 * new Sort(排序规则,"根据的字段")
		 */
		Sort sort=new Sort(Direction.DESC,"id");//这个是现在的方法，使用order的过时了
		List<User> users =this.userRepository.findAll(sort);//这个就不用强制转换了
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	/**
	 * 测试userRepository jpa接口的findAll方法：用于排序
	 */
	@Test
	public void testuserRepositorySpecificationfindAll(){
		
		
		Sort sort=new Sort(Direction.DESC,"id");
		/**
		 * Specification<User:用于封装查询条件
		 */
		Specification<User> spec=new Specification<User>() {
		
			private static final long serialVersionUID = 1L;

			/**
			 * Predicate：封装了单个查询条件
			 * Root<User> root：查询对象的属性的封装
			 * CriteriaQuery<?> query：封装了我们要执行的查询中的各个部分的信息
			 * CriteriaBuilder：查询条件的构造器，定义不同的查询条件
			 */
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				/**
				 * 参数一：查询条件属性
				 * 参数二：条件的值
				 */
				//1、Predicate pre=criteriaBuilder.equal(root.get("name"),"爱国");
				
				//多条件查询
				List<Predicate> list=new ArrayList<>();
				
				/*list.add(criteriaBuilder.equal(root.get("name"),"敬业"));
				list.add(criteriaBuilder.equal(root.get("age"),23));
				Predicate[] arr=new Predicate[list.size()];*/
				
				//2、将2个查询条件设为and关系
				//Predicate pre=criteriaBuilder.and(list.toArray(arr));
				//Predicate pre = criteriaBuilder.or(list.toArray(arr));//建立条件的关系
				
				//在and关系下加上or关系   优先级：and（11） 高于   or(12)
				Predicate pre =criteriaBuilder.or((criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),"敬业"),criteriaBuilder.equal(root.get("age"),36))),criteriaBuilder.equal(root.get("id"),"6"));
				return pre;
			}
		};
		//添加排序
		Sort sort1=new Sort(Direction.DESC, "id");//根据id降序排序
		List<User> users =this.userRepositorySpecification.findAll(spec,sort1);
		for (User user : users) {
			System.out.println(user);
		}
	}
	
	
	
	
}
