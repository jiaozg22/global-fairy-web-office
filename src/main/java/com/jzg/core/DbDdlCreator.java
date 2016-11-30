package com.jzg.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jzg.module.entity.TemplateTable;
import com.jzg.module.param.DataColumnParam;
import com.jzg.util.ColumnNameGenerateUtil;
import com.jzg.util.StringHexConverterUtil;
import com.jzg.util.TableNameGenarateUtil;

@Component("dbDdlCreator")
public class DbDdlCreator {
	private static Logger logger = LogManager.getLogger(DbDdlCreator.class);
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	protected JdbcTemplate  jdbcTemplate;
	
	public String initDdlSQL(TemplateTable templateTable){
		StringBuffer sql=new StringBuffer("CREATE TABLE t_");
		sql.append(templateTable.getTableName());
		sql.append("(id INTEGER primary key  not NULL auto_increment,");
		//List存储的是一个固定长度为3的list，其中第一个值为字段编码，也就是数据库中实际的表结构名
		List<DataColumnParam> cessList=templateTable.getCell();
		for(int i=0;i<cessList.size();i++){ 
			String cell=cessList.get(i).getColumnName();
			String cellNameUnicode=cell;
			sql.append(""+cellNameUnicode+" VARCHAR(255)");
			if(i<cessList.size()-1){
				sql.append(",");
			}
		}
		sql.append(" ) type=myisam default charset utf8;");
		System.out.println(sql);
		
		logger.info(sql);
		return sql.toString();
	}

	public void createTable(String ddlSQL){
		System.out.println("创建数据表开始。。。");
		jdbcTemplate.execute(ddlSQL);
		System.out.println("创建完成");
	}
	
	public String initCleanSQL(TemplateTable templateTable){
		StringBuffer sql=new StringBuffer("delete from t_");
		sql.append(templateTable.getTableName());
		sql.append("");
		return sql.toString();
	}
	
	public String initInsertSQL(TemplateTable templateTable){
		StringBuffer sql=new StringBuffer("insert into t_");
		sql.append(templateTable.getTableName());
		sql.append("( ");
		
		List<DataColumnParam> cellList=templateTable.getCell();
		for(int i=0;i<cellList.size();i++){
			DataColumnParam dataColumn =cellList.get(i);
			sql.append(dataColumn.getColumnName());
			if(i<cellList.size()-1){
				sql.append(" ,");
			}
		}
		sql.append(" )");
		sql.append(" values ");
		
		//List存储的是一个固定长度为3的list，其中第一个值为字段编码，也就是数据库中实际的表结构名
		List<ArrayList<String>> rowsList=templateTable.getRowsList();
		int rowCount=rowsList.size();
		//int rowCount=rowsList.size();
		for(int i=0;i<rowCount;i++){
			ArrayList<String> row=rowsList.get(i);
			sql.append(" ( ");
			//for(int j=0;j<row.size();j++){
			int rowSize=row.size();
			for(int j=0;j<rowSize;j++){
				sql.append("'");
				sql.append(row.get(j));
				sql.append("'");
				if(j<rowSize-1){
					sql.append(",");
				}
			}
			sql.append(" )");
			if(i<rowCount-1){
				sql.append(",");
			}
		}
		sql.append(" )type=myisam default charset utf8;");
		sql.append(";");
		
		return sql.toString();
	}
	
	public static void main(String[] args){
		TemplateTable templateTable=new TemplateTable();
		templateTable.setTableName("tableName");
		List<DataColumnParam> dataColumnParamList=new ArrayList<DataColumnParam>();
		DataColumnParam dataColumnParam=new DataColumnParam();
		dataColumnParam.setColumnComment("测试测试");
		dataColumnParam.setColumnName("columnName");
		dataColumnParam.setColumnType("varchar");
		dataColumnParamList.add(dataColumnParam);
		
		templateTable.setCell(dataColumnParamList);
		DbDdlCreator dbDdlCreator=new DbDdlCreator();
		dbDdlCreator.createTable(dbDdlCreator.initDdlSQL(templateTable));
	    
	    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-*.xml");  
	  
	    context.start();  
	  
	    dbDdlCreator.sessionFactory = (SessionFactory) context.getBean("sessionFactory");  
	    dbDdlCreator.jdbcTemplate=(JdbcTemplate)context.getBean("jdbcTemplate") ;
	}

}
