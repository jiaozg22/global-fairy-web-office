package com.jzg.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.jzg.dao.ICommonDao;


@Repository("commonDao")
public class CommonDaoImpl<T>  extends GenericBaseCommonDaoImpl<T, Serializable> implements ICommonDao<T>{
	
}
