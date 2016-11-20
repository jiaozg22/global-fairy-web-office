package com.jzg.module.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.jzg.module.param.DataColumnParam;


public class TemplateTable extends CommonEntity implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1L;
	
	/**
	 * 表名
	 * */
	private String tableName;
	
	/** 
	 * 表注释
	 *  */
	private String tableCommont;
	
	/**
	 * 字段名与字段类型
	 * */
	private List<DataColumnParam> cell = new ArrayList<DataColumnParam>();
	
	/**
	 * 表数据
	 * */
	private List<ArrayList<String>> rowsList=new ArrayList<ArrayList<String>>();

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableCommont() {
		return tableCommont;
	}

	public void setTableCommont(String tableCommont) {
		this.tableCommont = tableCommont;
	}

	public List<DataColumnParam> getCell() {
		return cell;
	}

	public void setCell(List<DataColumnParam> cell) {
		this.cell = cell;
	}

	public List<ArrayList<String>> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ArrayList<String>> rowsList) {
		this.rowsList = rowsList;
	}
	
}

