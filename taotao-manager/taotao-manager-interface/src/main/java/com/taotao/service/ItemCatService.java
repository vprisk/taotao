package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
/**
 * 商品分类接口
 * @author Administrator
 *
 */
public interface ItemCatService {
	/**
	 * 获取所有商品分类
	 * @param parentId
	 * @return
	 */
	List<EasyUITreeNode> getItemCatList(long parentId);
}
