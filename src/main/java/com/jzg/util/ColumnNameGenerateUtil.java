package com.jzg.util;

public class ColumnNameGenerateUtil {
	public static String columnNameGenerate(String columnName){
		return "column_"+StringHexConverterUtil.encode(columnName);
	}
}
