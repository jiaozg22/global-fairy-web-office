package com.jzg.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.jzg.dao.IDatabaseDdlDao;

@Repository("dataColumnDao")
public class DataColumnDaoImpl <T> extends GenericBaseCommonDaoImpl<T, Serializable> implements IDatabaseDdlDao<T>{

}
