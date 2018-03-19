package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	/**
	 * 获取网站类容分类列表tree
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoryList(long parentId);
	
	/**
	 * 添加网站内容分类节点
	 * @param parentId
	 * @param name
	 * @return
	 */
	public TaotaoResult addContentCategory(long parentId,String name);
	
	/**
	 * 修改节点
	 * @param id
	 * @param name
	 */
	public void updateContentCategory(long id,String name);
	
	/**
	 * 删除节点
	 * @param id
	 */
	public  void deleteContentCategory(long id);
}
