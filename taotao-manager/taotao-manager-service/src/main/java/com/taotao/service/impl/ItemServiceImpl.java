package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
/**
 * 商品管理类
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Override
	public TbItem getItemById(long itemId) {
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		return item;
	}

}
