package com.jzg.module.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;



/**
 * @author jiao_zg
 * @date
 * @since 2015.05.07
 * **/
@MappedSuperclass
public class CommonEntity {
	
	/**
	 * @Field 标识
	 * * **/
	private int id;

	/**
	 * @Field 排序规则
	 * **/
	private Boolean sort;

	/**
	 * @Field 排序优先级
	 * **/
	private Integer sortNum;

	/**
	 * @Field 备注
	 * **/
	private String auduit;

	/**
	 * @Field 逻辑删除标识
	 * **/
	private Boolean del;

	/**
	 * @Field 添加人
	 * **/
	private String insertName;

	/**
	 * @Field 添加时间
	 * **/
	private String insertTime;

	/**
	 * @Field 更新者
	 * **/
	private String updateName;

	/**
	 * @Field 更新时间
	 * **/
	private String updateTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID",  columnDefinition=("int comment '标识'"))
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the sort
	 */
	@Column(name = "sort",  columnDefinition=("binary   comment '是否参与排序标识：0：不参与；1：参与'"))
	public Boolean getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(Boolean sort) {
		this.sort = sort;
	}

	/**
	 * @return the sortNum
	 */
	@Column(name = "sortNum" ,columnDefinition=("int comment '排序权值'"))
	public Integer getSortNum() {
		return sortNum;
	}

	/**
	 * @param sortNum
	 *            the sortNum to set
	 */
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the auduit
	 */
	@Column(name = "auduit" ,columnDefinition=("varchar(32) comment '添加者'"))
	public String getAuduit() {
		return auduit;
	}

	/**
	 * @param auduit
	 *            the auduit to set
	 */
	public void setAuduit(String auduit) {
		this.auduit = auduit;
	}

	/**
	 * @return the del
	 */
	@Column(name = "del",columnDefinition=("char(1) comment '删除标识，0：未删除；1：已删除'"))
	public Boolean getDel() {
		return del;
	}

	/**
	 * @param del
	 *            the del to set
	 */
	public void setDel(Boolean del) {
		this.del = del;
	}

	/**
	 * @return the insertName
	 */
	@Column(name = "insert_name",columnDefinition=("varchar(32) comment '添加者姓名'"))
	public String getInsertName() {
		return insertName;
	}

	/**
	 * @param insertName
	 *            the insertName to set
	 */
	public void setInsertName(String insertName) {
		this.insertName = insertName;
	}

	/**
	 * @return the insertTime
	 */
	@Column(name = "insert_time",columnDefinition=("varchar(19) comment '插入时间'"))
	public String getInsertTime() {
		return insertTime;
	}

	/**
	 * @param insertTime
	 *            the insertTime to set
	 */
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @return the updateName
	 */
	@Column(name = "update_name",columnDefinition=("varchar(40) comment '更新者'"))
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * @param updateName
	 *            the updateName to set
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * @return the updateTime
	 */
	@Column(name = "update_time",columnDefinition=("varchar(19) comment '更新时间'"))
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
}

