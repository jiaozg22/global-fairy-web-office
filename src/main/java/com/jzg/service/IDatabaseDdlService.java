package com.jzg.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jzg.module.entity.DataColumnEntity;
import com.jzg.module.entity.DataTableEntity;

public interface IDatabaseDdlService extends ICommonService{
	/**
	 * 查询所有数据表
	 * 
	 * @return List<DataTableEntity>
	 */
	List<DataTableEntity> listDataTable();

	/***
	 * 根据表名查找表结构
	 * @param tableName
	 * @return
	 */
	List<DataColumnEntity> listDataColumnByTableName(String tableName);

	List listDataByTableName(String tableName);

	void cleanAll();

	int sumByColumnComment(String tableComment,String columnComment);

	List listDataByTableNameAndCondation(String tableName,
			String columnName, String columnValue, String startTime,
			String endTime);

	int sumByColumnCommentAndCondation(String tableName,
			String columnName, String columnName2, String columnValue,
			String startTime, String endTime);

}
