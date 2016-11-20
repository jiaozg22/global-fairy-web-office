package com.jzg.util;


public class TableNameGenarateUtil {
	
	public static String generateTableName(String tableName){
		return StringHexConverterUtil.encode(tableName);
	}
	
	public static void main(String[] args){
		System.out.println(generateTableName("不达标"));
	}
}
