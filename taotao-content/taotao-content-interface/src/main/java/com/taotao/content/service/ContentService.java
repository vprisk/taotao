package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	/**
	 * 根据内容分类id查询内容
	 * @param categoryId
	 * @return
	 */
	public EasyUIDataGridResult queryContentList(long categoryId);
	
	/**
	 * 新增网页内容
	 * @param content
	 * @return
	 */
	public TaotaoResult addContent(TbContent content);
	
	/**
	 * 修改网页内容
	 * @param content
	 * @return
	 */
	public TaotaoResult updateContent(TbContent content);
	
	/**
	 * 批量删除网页内容
	 * @param ids
	 * @return
	 */
	public TaotaoResult deleteContent(List<Long> ids);
	
	/**
	 * 根据内容分类id查询内容
	 * @param categoryId
	 * @return
	 */
	public List<TbContent> getContentList(long categoryId);
}
