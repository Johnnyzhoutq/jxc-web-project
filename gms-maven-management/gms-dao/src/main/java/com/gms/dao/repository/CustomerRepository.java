package com.gms.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.gms.entity.jxc.Customer;


/**
 * 客户Repository接口
 * @author jxc 
 *
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer>,JpaSpecificationExecutor<Customer>{

	/**
	 * 根据名称模糊查询客户信息
	 * @param name
	 * @return
	 */
	@Query(value="select * from t_customer where name like ?1",nativeQuery=true)
	public List<Customer> findByName(String name);
	@Query(value="select * from t_customer where shop_id=?1 and name like ?2",nativeQuery=true)
	public List<Customer> findByShopAndName(Integer shopId,String name);
}
