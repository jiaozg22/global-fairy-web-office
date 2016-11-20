package com.jzg.control;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.jzg.api.DatabaseDdlAPI;
import com.jzg.core.DbDdlCreator;
import com.jzg.module.entity.DataColumnEntity;
import com.jzg.module.entity.DataTableEntity;
import com.jzg.module.entity.DatabaseTableDdlEntity;
import com.jzg.module.entity.TemplateTable;
import com.jzg.module.param.DataColumnParam;
import com.jzg.util.ExcelUtils;

@Controller
@RequestMapping("/watchDataControler")
public class WatchDataControler {
	private static Logger logger = LogManager.getLogger("WatchDataControler");

	@Autowired
	@Qualifier("dbDdlCreator")
	private DbDdlCreator dbDdlCreator;

	@Autowired
	@Qualifier("databaseDdlAPI")
	private DatabaseDdlAPI databaseDdlAPI;
	
	@RequestMapping(value = "/watchdata/showByCondation.action", method = RequestMethod.POST)
	public String showWatchDataByCondation(HttpServletRequest request, HttpServletResponse response,@RequestParam("columnValue") String columnValue
) throws UnsupportedEncodingException{
		System.out.println("进入后台处理");
		String tableName=request.getParameter("tableName") ;
		
		request.setAttribute("columnValue",columnValue);
		String columnName2= request.getParameter("columnName"); 
		String startTime = request.getParameter("startTime");
		request.setAttribute("startTime",startTime);
		String endTime = request.getParameter("endTime");
		request.setAttribute("endTime",endTime);
		
		List<DatabaseTableDdlEntity> databaseTableDdlList=databaseDdlAPI.list();
		
		List<DataTableEntity> dataTableList=databaseDdlAPI.listDataTable();
		List<TemplateTable> templateTableList=new ArrayList<TemplateTable>();
		for(int tableCount=0;tableCount<dataTableList.size();tableCount++){
			TemplateTable templateTable=new TemplateTable();
			
			DataTableEntity dataTableEntity=dataTableList.get(tableCount);
			//表
			templateTable.setTableCommont(dataTableEntity.getTableColumn());
			templateTable.setTableName(dataTableEntity.getTableName());
		    
			//表结构
			List<DataColumnEntity> dataColumnEntityList=databaseDdlAPI.listDataColumnByTableName(dataTableList.get(tableCount).getTableName());
			System.out.println("*******dataColumnEntityList行数***h"+dataTableList.get(0).getTableName()+dataColumnEntityList.size());
			List<DataColumnParam> cell=new ArrayList<DataColumnParam>();
			for(DataColumnEntity dataColumnEntity:dataColumnEntityList){
				DataColumnParam dataColumnParam=new DataColumnParam();
				try {
					PropertyUtils.copyProperties(dataColumnParam, dataColumnEntity);
					cell.add(dataColumnParam);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			templateTable.setCell(cell);
			
			//表数据，根据责任人查询（默认）
			List<ArrayList<String>> rowsList = databaseDdlAPI.listDataByTableNameAndCondation(dataTableList.get(tableCount).getTableName(),columnName2,columnValue,startTime,endTime);
			templateTable.setRowsList(rowsList);
			
			templateTableList.add(templateTable);
		}
		System.out.println("*******databaseTableDdlList行数***h"+dataTableList.size());
		int sumDisposedeal = databaseDdlAPI.sumByColumnCommentAndCondation(templateTableList.get(0).getTableName(),templateTableList.get(0).getCell().get(5).getColumnName(),columnName2,columnValue,startTime,endTime);
		request.setAttribute("sumDisposedeal", sumDisposedeal);
		//表数据
		request.setAttribute("templateTableList", templateTableList);
		return "forward:/webpage/dataFromExcelToDatabase/watchDataUploadAndShow.jsp";
	}
	
	@RequestMapping(value = "/watchdata/show.action", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String  showWatchData(HttpServletRequest request, HttpServletResponse response,ModelAndView model) throws IOException {
		String tableComment="";
		System.out.println("进入后台处理");
		List<DatabaseTableDdlEntity> databaseTableDdlList=databaseDdlAPI.list();
		System.out.println("*******行数***h"+databaseTableDdlList.size());
		List<DataTableEntity> dataTableList=databaseDdlAPI.listDataTable();
		List<TemplateTable> templateTableList=new ArrayList<TemplateTable>();
		for(int tableCount=0;tableCount<dataTableList.size();tableCount++){
			TemplateTable templateTable=new TemplateTable();
			
			DataTableEntity dataTableEntity=dataTableList.get(tableCount);
			//表
			templateTable.setTableCommont(dataTableEntity.getTableColumn());
			templateTable.setTableName(dataTableEntity.getTableName());
		    
			//表结构
			List<DataColumnEntity> dataColumnEntityList=databaseDdlAPI.listDataColumnByTableName(dataTableList.get(tableCount).getTableName());
			System.out.println("*******dataColumnEntityList行数***h"+dataTableList.get(0).getTableName()+dataColumnEntityList.size());
			List<DataColumnParam> cell=new ArrayList<DataColumnParam>();
			for(DataColumnEntity dataColumnEntity:dataColumnEntityList){
				DataColumnParam dataColumnParam=new DataColumnParam();
				try {
					PropertyUtils.copyProperties(dataColumnParam, dataColumnEntity);
					cell.add(dataColumnParam);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			templateTable.setCell(cell);
			
			//表数据
			List<ArrayList<String>> rowsList = databaseDdlAPI.listDataByTableName(dataTableList.get(tableCount).getTableName());
			templateTable.setRowsList(rowsList);
			
			templateTableList.add(templateTable);
		}
		System.out.println("*******databaseTableDdlList行数***h"+dataTableList.size());
		int sumDisposedeal = databaseDdlAPI.sumByColumnComment(templateTableList.get(0).getTableName(),templateTableList.get(0).getCell().get(5).getColumnName());
		request.setAttribute("sumDisposedeal", sumDisposedeal);
		//表数据
		request.setAttribute("templateTableList", templateTableList);
		//model.addObject("templateTableLit", templateTableList);
		//请求转发，request 范围
		return  "forward:/webpage/dataFromExcelToDatabase/watchDataUploadAndShow.jsp";
	}

	/*
	 * 采用spring提供的上传文件的方法
	 */
	@RequestMapping("/springUpload.action")
	public String springUpload(HttpServletRequest request,HttpServletResponse response)
			throws IllegalStateException, IOException {
		logger.info("开始处理");
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next()
						.toString());
				if (file != null) {
					String path = this.getClass().getClassLoader().getResource("/").getPath();
					path = path.substring(0, path.length()-16)+ "\\" + file.getOriginalFilename();
					// 上传
					File outfile=new File(path);
					
					file.transferTo(outfile);
					System.out.println("保存路径："+outfile.getAbsolutePath());
					
					//一个sheet页创建一张表
					TemplateTable templateTable=ExcelUtils.readSigleSheetExcelForTableStructAndData(path,0);
					try{
						//删除旧表
						//dbDdlCreator.createTable(dbDdlCreator.initCleanSQL(templateTable));
						dbDdlCreator.createTable(dbDdlCreator.initDdlSQL(templateTable));
					}catch(Exception e){
						logger.info("创建数据库失败:"+e.getMessage());
					}
					//避免重复存储表 结构
					databaseDdlAPI.cleanAll();
					List<DataColumnParam> dataColumnParamList=templateTable.getCell();
					//存储表结构
					for(int i=0;i<dataColumnParamList.size();i++){
						DatabaseTableDdlEntity	databaseTableDdlEntity = new DatabaseTableDdlEntity();
						
						databaseTableDdlEntity.setTableName(templateTable.getTableName());
						databaseTableDdlEntity.setTableComment(templateTable.getTableCommont());
						
						DataColumnParam temp=dataColumnParamList.get(i); 
						databaseTableDdlEntity.setColumnName(temp.getColumnName());
						databaseTableDdlEntity.setColumnType(temp.getColumnType());
						databaseTableDdlEntity.setColumnComment(temp.getColumnComment());
						
						databaseDdlAPI.save(databaseTableDdlEntity);
					}
					
					//往表中插入数据
					dbDdlCreator.createTable(dbDdlCreator.initInsertSQL(templateTable));
				}
			}
		}
		long endTime = System.currentTimeMillis();
		logger.info("方法三的运行时间：" + String.valueOf(endTime - startTime) +"ms");
		
		//重定向，session范围有效，request范围失效。如果传递参数，用RedirectAttributes 
		return "redirect:/watchDataControler/watchdata/show.action";
	}
	
	@ExceptionHandler  
	    public String exp(HttpServletRequest request, Exception ex) {   
	           
	        request.setAttribute("ex", ex);   
	           
	        // 根据不同错误转向不同页面   
	        if(ex instanceof Exception) {   
	            return "forward:/webpage/index.jsp";   
	        }else{
	        	return "forward:/webpage/index.jsp";   
	        }
	    }   

	
}
