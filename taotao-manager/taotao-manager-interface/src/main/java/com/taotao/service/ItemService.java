package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	/**
	 * 根据id查询商品
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long itemId);
	/**
	 * 分页获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult getItemList(int page,int rows);
}
