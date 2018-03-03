package com.zhss.eshop.commodity.domain;

import java.util.Date;
import java.util.List;

import com.zhss.eshop.common.util.AbstractObject;

/**
 * 属性分组DO
 * @author zhonghuashishan
 *
 */
public class PropertyGroupDTO extends AbstractObject {

	/**
	 * id
	 */
	private Long id;
	/**
	 * 属性分组名称
	 */
	private String name;
	/**
	 * 类目id
	 */
	private Long categoryId;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 修改时间
	 */
	private Date gmtModified;
	/**
	 * 属性分组与属性的关联关系
	 */
	private List<PropertyGroupRelationshipDTO> relations;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public List<PropertyGroupRelationshipDTO> getRelations() {
		return relations;
	}
	public void setRelations(List<PropertyGroupRelationshipDTO> relations) {
		this.relations = relations;
	}
	
}
