package com.jzg.api;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jzg.module.entity.DataColumnEntity;
import com.jzg.module.entity.DataTableEntity;
import com.jzg.module.entity.DatabaseTableDdlEntity;
import com.jzg.service.IDatabaseDdlService;

@Component("databaseDdlAPI")
public class DatabaseDdlAPI {
	@Autowired
	private IDatabaseDdlService databaseDdlService;
	
	public void cleanAll(){
		databaseDdlService.cleanAll();
	}
	
	public void save(DatabaseTableDdlEntity databaseDdl){
		databaseDdlService.save(databaseDdl);
	}
	
	public List<DatabaseTableDdlEntity> list(){
		return databaseDdlService.getList(DatabaseTableDdlEntity.class);
	}
	
	public List<DataTableEntity> listDataTable(){
		
		List<DataTableEntity> dataTableList=databaseDdlService.listDataTable();;
		return dataTableList;
	}
	public List<DataColumnEntity> listDataColumnByTableName(String tableName){
		List<DataColumnEntity> dataColumnEntityList=databaseDdlService.listDataColumnByTableName(tableName);
		return dataColumnEntityList;
	}
	
	public List<ArrayList<String>> listDataByTableName(String tableName){
		return databaseDdlService.listDataByTableName(tableName);
	}

	public int sumByColumnComment(String tableComment,String columnComment) {
		return databaseDdlService.sumByColumnComment(tableComment,columnComment);
	}

	public List<ArrayList<String>> listDataByTableNameAndCondation(
			String tableName,String columnName, String columnValue,  String startTime,
			String endTime) {
		return databaseDdlService.listDataByTableNameAndCondation(tableName,columnName, columnValue, startTime,endTime);
	}

	/**
	 * 
	 * @param tableName
	 * @param columnName
	 * @param columnName2:条件字段
	 * @param columnValue
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int sumByColumnCommentAndCondation(String tableName,String columnName, String columnName2, String columnValue,
			String startTime, String endTime) {
		return databaseDdlService.sumByColumnCommentAndCondation(tableName,columnName,columnName2, columnValue, startTime,endTime);
	}
}
