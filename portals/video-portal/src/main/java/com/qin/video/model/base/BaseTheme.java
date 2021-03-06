package com.qin.video.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTheme<M extends BaseTheme<M>> extends Model<M> implements IBean {

	public void setId(Integer id) {
		set("id", id);
	}
	
	public Integer getId() {
		return getInt("id");
	}

	public void setMenuId(Integer menuId) {
		set("menu_id", menuId);
	}
	
	public Integer getMenuId() {
		return getInt("menu_id");
	}

	public void setTitle(String title) {
		set("title", title);
	}
	
	public String getTitle() {
		return getStr("title");
	}

	public void setDesc(String desc) {
		set("desc", desc);
	}
	
	public String getDesc() {
		return getStr("desc");
	}

	public void setFileId(Integer fileId) {
		set("file_id", fileId);
	}
	
	public Integer getFileId() {
		return getInt("file_id");
	}

	public void setType(Integer type) {
		set("type", type);
	}
	
	public Integer getType() {
		return getInt("type");
	}

	public void setSeq(Integer seq) {
		set("seq", seq);
	}
	
	public Integer getSeq() {
		return getInt("seq");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setOpId(Integer opId) {
		set("op_id", opId);
	}
	
	public Integer getOpId() {
		return getInt("op_id");
	}

	public void setStatus(Integer status) {
		set("status", status);
	}
	
	public Integer getStatus() {
		return getInt("status");
	}

	public void setOpName(String opName) {
		set("op_name", opName);
	}
	
	public String getOpName() {
		return getStr("op_name");
	}

	/**
	 * 点击数
	 */
	public void setClickNum(Integer clickNum) {
		set("click_num", clickNum);
	}
	
	/**
	 * 点击数
	 */
	public Integer getClickNum() {
		return getInt("click_num");
	}

}
