package com.jzg.module.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name="t_database_table_ddl")
@Component("DatabaseTableDdlEntity")
public class DatabaseTableDdlEntity extends CommonEntity implements Serializable{
	//表名，实际是程序生成的t开头的16进制串
	private String tableName;
    
	private String tableComment;
	
	private String columnName;
	
	private String columnComment;
		
	private String columnType;
	
	private int columnSortNum;

	@Column(name="table_name",columnDefinition=("varchar(100) comment '表名'"))
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name="column_name",columnDefinition=("varchar(100) comment '字段名'"))
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name="column_type",columnDefinition=("varchar(100) comment '字段类型'"))
	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	@Column(name="column_sortNum",columnDefinition=("varchar(19) comment '排序值'"))
	public int getColumnSortNum() {
		return columnSortNum;
	}

	public void setColumnSortNum(int columnSortNum) {
		this.columnSortNum = columnSortNum;
	}

	@Column(name="table_comment",columnDefinition=("varchar(100) comment '表显示名'"))
	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	@Column(name="column_comment",columnDefinition=("varchar(100) comment '字段显示名'"))
	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	
}
