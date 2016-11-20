package com.jzg.dao;

import java.io.Serializable;
import java.util.List;


import com.jzg.util.PageList;
 
/** 
 * @author jiaozg
 * **/
public interface BaseDao<T>{
	/**
	 * 
	 * 
	 * @param obj
	 *            
	 */
	public void add(T obj);

	/**
	 * 
	 * 
	 * @param obj
	 *             
	 */
	public void update(T obj);

	/**
	 * 
	 * @param id
	 * @return  
	 */
	public T get(Serializable id);

	/**
	 * 
	 * @param obj
	 *             
	 */
	public void delete(T obj);

	/**
	 * 
	 * @return  
	 */
	public List<T> list(String hql);

	/**
	 * 
	 * @return
	 */
	public List<T> list();
	

	/**
	 * 
	 * @param hql
	 * @param parms
	 * @return  
	 */
	public List<T> listByHql(String hql, Object... parms);

	/**
	 * 
	 * @param hql
	 * @return
	 */
	public PageList<T> findPager(PageList<T> pageList, String hql);

	/**
	 * 
	 * @param obj
	 */
	public void merge(T obj);
	
	public List<T> sqllist(String hql);
}
