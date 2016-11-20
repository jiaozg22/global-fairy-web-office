package com.jzg.module.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="t_data_column")
@Component("DataColumnEntity")
public class DataColumnEntity extends CommonEntity implements Serializable{
	private int dataTableId;
	
	private String columnName;
	
	private String columnType;
	
	private String columnComment;
	
	public int getDataTableId() {
		return dataTableId;
	}

	public void setDataTableId(int dataTableId) {
		this.dataTableId = dataTableId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
}
