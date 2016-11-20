package com.jzg.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzg.dao.IDatabaseDdlDao;
import com.jzg.module.entity.DataColumnEntity;
import com.jzg.module.entity.DataTableEntity;
import com.jzg.service.IDatabaseDdlService;

@Service("databaseDdlService")
public class DatabaseDdlServiceImpl extends CommonServiceImpl implements IDatabaseDdlService{
	@Autowired
	IDatabaseDdlDao<DataTableEntity> databaseDdlDao;
	
	@Override
	public List<DataTableEntity> listDataTable() {
		String query="select table_name,table_comment from t_database_table_ddl group by table_name";
		System.out.println("*****查询:"+query);
		List<DataTableEntity> dataTableList=new ArrayList<DataTableEntity>();
		
		List<Object[]> resultList =databaseDdlDao.findListbySql(query);
		for(Object[] obj:resultList){
			DataTableEntity dataTableEntity=new DataTableEntity();
			dataTableEntity.setTableName((String)obj[0]);
			dataTableEntity.setTableColumn((String)obj[1]);
			dataTableList.add(dataTableEntity);
		}
		DataTableEntity dataTableEntity=(DataTableEntity)dataTableList.get(0);
		System.out.println(query);
		return dataTableList;
	}

	@Override
	public List<DataColumnEntity> listDataColumnByTableName(String tableName) {
		String query="select column_comment,column_name,column_sortNum,column_type from t_database_table_ddl where table_name ='"+ tableName+"'";
		System.out.println("*****查询:"+query);
		List<DataColumnEntity> dataColumnEntityList=new ArrayList<DataColumnEntity>();
		
		List<Object[]> resultList =databaseDdlDao.findListbySql(query);
		for(Object[] obj:resultList){
			DataColumnEntity dataColumnEntity=new DataColumnEntity();
			dataColumnEntity.setColumnComment((String)obj[0]);
			dataColumnEntity.setColumnName((String)obj[1]);
			dataColumnEntity.setColumnType((String)obj[1]);
			dataColumnEntityList.add(dataColumnEntity);
		}
		DataColumnEntity dataColumnEntity=(DataColumnEntity)dataColumnEntityList.get(0);
		System.out.println(query);
		
		return dataColumnEntityList;
	}

	@Override
	public List<LinkedList<String>> listDataByTableName(String tableName) {
		List<LinkedList<String>> resultList=new ArrayList<LinkedList<String>>();
		
		List<DataColumnEntity> dataColumnList=listDataColumnByTableName(tableName);
		StringBuilder query=new StringBuilder("select ");
		int i=0;
		for(DataColumnEntity dataColumnEntity:dataColumnList){
			query.append(dataColumnEntity.getColumnName());
			if(i<dataColumnList.size()-1){
				query.append(" , ");
			}
			i++;
		}
		query.append(" from t_");
		query.append(tableName);
		System.out.println("**********"+query);
		List<Object[]> querytList =databaseDdlDao.findListbySql(query.toString());
		
		for(Object[] obj:querytList){
			LinkedList<String> result= new LinkedList<String>();
			for(int countColunn=0;countColunn<dataColumnList.size();countColunn++){
				result.add((String)obj[countColunn]);
			}
			resultList.add(result);
		}
		return resultList;
	}

	@Override
	public void cleanAll() {
		String sql="delete from t_database_table_ddl ";
		databaseDdlDao.executeSql(sql);
	}

	@Override
	public int sumByColumnComment(String tableName,String columnName) {
		Integer result=0;
		String sql="select sum("+columnName+") as sum from t_"+tableName;
		List<Double> sumList=databaseDdlDao.findListbySql(sql);
		
		if(sumList.get(0)!=null){
			result= sumList.get(0).intValue();
		}
		return result;
	}

	@Override
	public List<LinkedList<String>> listDataByTableNameAndCondation(
			String tableName, String columnName, String columnValue,
			String startTime, String endTime) {
		List<LinkedList<String>> resultList=new ArrayList<LinkedList<String>>();
		
		List<DataColumnEntity> dataColumnList=listDataColumnByTableName(tableName);
		StringBuilder query=new StringBuilder("select ");
		int i=0;
		for(DataColumnEntity dataColumnEntity:dataColumnList){
			query.append(dataColumnEntity.getColumnName());
			if(i<dataColumnList.size()-1){
				query.append(" , ");
			}
			i++;
		}
		query.append(" from t_");
		query.append(tableName);
		query.append(" where 1=1 ");
		if(columnValue!="责任人"){
			query.append(" and "+columnName+" like '%"+columnValue+"%' ");
		}
		if(startTime!="开始时间"&&startTime!=""){
			if(endTime!="结束时间"&&endTime!=""){
				query.append(" and column_CECACCE2B7B4C0A10AC8D5C6DA >= "+startTime);
				query.append(" and column_CECACCE2B7B4C0A10AC8D5C6DA <= "+endTime);
			}
		}
		
		System.out.println("**********"+query);
		List<Object[]> querytList =databaseDdlDao.findListbySql(query.toString());
		
		for(Object[] obj:querytList){
			LinkedList<String> result= new LinkedList<String>();
			for(int countColunn=0;countColunn<dataColumnList.size();countColunn++){
				result.add((String)obj[countColunn]);
			}
			resultList.add(result);
		}
		return resultList;
	}

	@Override
	public int sumByColumnCommentAndCondation(String tableName,
			String columnName,String columnName2, String columnValue,
			String startTime, String endTime) {
		Integer result=0;
		StringBuilder query=new StringBuilder("select sum("+columnName+") as sum from t_"+tableName);
		
		query.append(" where 1=1 ");
		if(columnValue!="责任人"){
			query.append(" and "+columnName2+" like '%"+columnValue+"%' ");
		}
		if(startTime!="开始时间"&&startTime!=""){
			if(endTime!="结束时间"&&endTime!=""){
				query.append(" and column_CECACCE2B7B4C0A10AC8D5C6DA >= "+startTime);
				query.append(" and column_CECACCE2B7B4C0A10AC8D5C6DA <= "+endTime);
			}
		}
		
		List<Double> sumList=databaseDdlDao.findListbySql(query.toString());
		System.out.println(sumList.size());
		if(sumList.get(0)!=null){
			result= sumList.get(0).intValue();
		}
		return result;
	}
}
