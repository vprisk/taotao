package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

public interface ItemService {
	/**
	 * 根据id查询商品
	 * @param itemId
	 * @return
	 */
	TbItem getItemById(long itemId);
	
	/**
	 * 根据商品id查询商品描述
	 * @param itemId
	 * @return
	 */
	TbItemDesc getItemDescbyId(long itemId);
	/**
	 * 分页获取商品列表
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyUIDataGridResult getItemList(int page,int rows);
	
	/**
	 * 添加商品信息
	 * @param item
	 * @param desc
	 * @return
	 */
	public TaotaoResult addItem(TbItem item,String desc);
}
