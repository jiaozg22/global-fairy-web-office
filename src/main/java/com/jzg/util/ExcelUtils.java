package com.jzg.util;
/**
 * @author jiao
 * 
 * @date 2016 09 15
 * */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xmlbeans.impl.util.Base64;

import com.alibaba.druid.util.StringUtils;
import com.jzg.module.entity.TemplateTable;
import com.jzg.module.param.DataColumnParam;


public class ExcelUtils {
	/** 读取表结构的标识 **/
	private static final String TABLE_STRUCT="表结构";
	
	/** 读取表数据的标识 **/
	private static final String TABLE_DATA="表数据";
	
	/** 读取表结构和表数据的合集 **/
	private static final String TABLE_STRUCT_AND_DATA="表结构和表数据";
	
	
	public static TemplateTable readSingleSheetExcelForTableStruct(String FILE_PATH,int i) throws IOException{
		return readXls(FILE_PATH,TABLE_STRUCT).get(i);
	}
	
	public static TemplateTable readSingleSheetExcelForTableData(String FILE_PATH,int i) throws IOException{
		return readXls(FILE_PATH,TABLE_DATA).get(i);
	}
	
	public static TemplateTable readSigleSheetExcelForTableStructAndData(String FILE_PATH,int i) throws IOException{
		return readXls(FILE_PATH,TABLE_STRUCT_AND_DATA).get(i);
	}

	public static List<TemplateTable> readExcelForTableStruct(String FILE_PATH) throws IOException{
		return readXls(FILE_PATH,TABLE_STRUCT);
	}
	
	public static List<TemplateTable> readExcelForTableData(String FILE_PATH) throws IOException{
		return readXls(FILE_PATH,TABLE_DATA);
	}
	
	public static List<TemplateTable> readExcelForTableStructAndData(String FILE_PATH) throws IOException{
		return readXls(FILE_PATH,TABLE_STRUCT_AND_DATA);
	}
	
	
    private static List<TemplateTable> readXls(String FILE_PATH,String type) throws IOException {
        InputStream inputStream = new FileInputStream(FILE_PATH);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        List<TemplateTable> sheetAllTableAndDataList = new ArrayList<TemplateTable>();
        
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            //处理表结构字段
            HSSFRow hssfRowName = hssfSheet.getRow(0); 
            TemplateTable templateTable=null;
            if(StringUtils.equals(type, TABLE_STRUCT)|StringUtils.equals(type, TABLE_STRUCT_AND_DATA)){
            	templateTable=new TemplateTable();
                //templateTable.setTableName(new String(hssfSheet.getSheetName().getBytes("gbk"),"utf-8"));
                String sheetTitleName=hssfSheet.getSheetName();
                templateTable.setTableCommont(sheetTitleName);
                String tableName=new String(TableNameGenarateUtil.generateTableName(sheetTitleName));
                templateTable.setTableName(tableName);
                if (hssfRowName != null) {
                	List<DataColumnParam> dataColumnParamList=new ArrayList<DataColumnParam>();
                	
                	int cellNum = hssfRowName.getPhysicalNumberOfCells();
                	int j=0;
                	while(j<cellNum){
                		// 循环列
                		String cellDetail=hssfRowName.getCell(j).getStringCellValue();
                		int cellType=hssfRowName.getCell(j).getCellType();
                		DataColumnParam dataColumnParam=new DataColumnParam();
                    	System.out.print(cellDetail+"|");
                    	
                    	dataColumnParam.setColumnName(ColumnNameGenerateUtil.columnNameGenerate(cellDetail));
                    	dataColumnParam.setColumnComment(cellDetail);
                    	dataColumnParam.setColumnType(String.valueOf(hssfRowName.getCell(j).getCellType()));
                    	dataColumnParamList.add(dataColumnParam);
                		j++;
                	}
                	templateTable.setCell(dataColumnParamList);
                	System.out.println();
                }
            }
            
            //List<TemplateTable> dataList = new ArrayList<TemplateTable>();
            List<ArrayList<String>> rowsList=new ArrayList<ArrayList<String>>();
            if(StringUtils.equals(type, TABLE_DATA)|StringUtils.equals(type, TABLE_STRUCT_AND_DATA)){
            	System.out.println("*****总共行数:"+hssfSheet.getLastRowNum());
                // 循环行Row,处理数据插入
                for (int rowNum = 1; rowNum < hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow != null) {
                    	int cellNum = hssfRow.getPhysicalNumberOfCells();
                    	
                    	int cellCount=0;
                    	
                    	ArrayList<String> row=new ArrayList<String>();
                    	while(cellCount<cellNum){
                    		// 循环列
                        	//System.out.print(hssfRow.getCell(j)+"|");
                    		HSSFCell hSSFCell=null;
                    		String rowData="";
                    		try{
                    			hSSFCell=hssfRow.getCell(cellCount);
                    			
                    			if(hSSFCell!=null){
                    				rowData=hSSFCell.toString();
                    			}else{
                    				rowData="";
                    			}
                    		}catch(NullPointerException ne){
                    			System.out.println(ne);
                    			System.out.print("数据为空|");
                    		}
                    		
                    		row.add(rowData);
                    		cellCount++;
                    	}
                    	rowsList.add(row);
                    }
                }
                templateTable.setRowsList(rowsList);
                sheetAllTableAndDataList.add(templateTable);
            }
           
        }
		return sheetAllTableAndDataList;
    }
    
    @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
            if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
                // 返回布尔类型的值
                return String.valueOf(hssfCell.getBooleanCellValue());
            } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                // 返回数值类型的值
                return String.valueOf(hssfCell.getNumericCellValue());
            } else {
                // 返回字符串类型的值
                return String.valueOf(hssfCell.getStringCellValue());
            }
        }
}