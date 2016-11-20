package com.jzg.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

/**
 * 
 * 
 * @version 1.0
 */

public interface IGenericBaseCommonDao<T> {
	/**
	 * 
	 * @return
	 */
//	public List<DBTable> getAllDbTableName();

	public Integer getAllDbTableSize();

	public <T> Serializable save(T entity);

	public <T> void batchSave(List<T> entitys);

	public <T> void saveOrUpdate(T entity);

	/**
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);

	/**
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);

	/**
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);

	/**
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class<?> entityName, Serializable id);

	public <T> void deleteEntityById(Class<?> entityName, Serializable id);

	/**
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);

	/**
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);

	public <T> void updateEntityById(Class<?> entityName, Serializable id);

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql);

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql);

	/**
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query);

	/**
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);

	public Session getSession();

	public List<?> findByExample(final String entityName,
			final Object exampleEntity);

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object, Object> getHashMapbyQuery(String query);


	/**
	 */
	public Integer executeSql(String sql, List<Object> param);

	/**
	 */
	public Integer executeSql(String sql, Object... param);

	/**
	 */
	public Integer executeSql(String sql, Map<String, Object> param);
	public Object executeSqlReturnKey(String sql, Map<String, Object> param);
	/**
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);

	/**
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz);

	/**
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs);

	/**
	 */
	public Long getCountForJdbc(String sql);

	/**
	 * 
	 */
	public Long getCountForJdbcParam(String sql, Object[] objs);

	/**
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param);

	/**
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult);

	public <T> List<T> findByDetached(DetachedCriteria dc);
	
	
	/**
	 * 添加对象
	 * 
	 * @param obj
	 *            要添加的对象
	 */
	public void add(T obj);

	/**
	 * 更新对象
	 * 
	 * @param obj
	 *            要更新的对象
	 */
	public void update(T obj);

	/**
	 * @param id
	 */
	public T get(Serializable id);


	/**
	 * @return 对象列表
	 */
	public List<T> list(String hql);

	/**
	 * 
	 * @return
	 */
	public List<T> list();
	

	/**
	 * 根据 hql 语句获取对象列表
	 * 
	 * @param hql
	 * @param parms
	 * @return 对象列表
	 */
	public List<T> listByHql(String hql, Object... parms);


	/**
	 * @param obj
	 */
	public void merge(T obj);
	
	public List<T> sqllist(String hql);
}
