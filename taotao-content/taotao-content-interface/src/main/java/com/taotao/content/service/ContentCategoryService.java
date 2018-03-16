package com.taotao.content.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;

public interface ContentCategoryService {
	/**
	 * 获取网站类容分类列表tree
	 * @param parentId
	 * @return
	 */
	public List<EasyUITreeNode> getContentCategoryList(long parentId);
}
