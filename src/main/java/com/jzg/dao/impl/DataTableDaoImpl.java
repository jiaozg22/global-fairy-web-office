package com.jzg.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.jzg.dao.IDataTableDao;

@Repository("dataTableDao")
public class DataTableDaoImpl <T> extends GenericBaseCommonDaoImpl<T, Serializable> implements IDataTableDao<T>{

}
