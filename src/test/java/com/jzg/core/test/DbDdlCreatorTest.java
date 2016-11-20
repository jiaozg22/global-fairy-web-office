package com.jzg.core.test;

import java.util.HashMap;
import java.util.Map;




import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import com.jzg.module.entity.TemplateTable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring-mvc*.xml"}) 
public class DbDdlCreatorTest {

	@Test
	public void testCreateTable(){
		TemplateTable templateTable=new TemplateTable();
		templateTable.setTableName("tableName");
		
		
		
		// DbDdlCreator dbDdlCreator=new DbDdlCreator(templateTable);
	}
	
	
	public static void main(String[] args){
		
	}

}
