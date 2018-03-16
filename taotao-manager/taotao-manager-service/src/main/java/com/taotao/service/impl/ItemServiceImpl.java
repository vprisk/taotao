package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
/**
 * 商品管理类
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDesc;
	@Override
	public TbItem getItemById(long itemId) {
		TbItem item = tbItemMapper.selectByPrimaryKey(itemId);
		return item;
	}
	@Override
	public EasyUIDataGridResult getItemList(int page,int rows){
		//设置分页信息
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		//取查询结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		//返回结果
		return result;
	}
	@Override
	public TaotaoResult addItem(TbItem item, String desc) {
		//生成商品id
		long itemId = IDUtils.genItemId();
		//补全item属性
		item.setId(itemId);
		//商品状态：1-正常	2-下架	3-删除
		item.setStatus((byte) 1);
		item.setUpdated(new Date());
		item.setCreated(new Date());
		//向商品表插入数据
		tbItemMapper.insert(item);
		//创建商品描述对象
		TbItemDesc itemDesc=new TbItemDesc();
		//补全商品描述的属性
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setUpdated(new Date());
		itemDesc.setCreated(new Date());
		//插入商品描述信息
		tbItemDesc.insert(itemDesc);
		//返回结果
		return TaotaoResult.ok();
	}

}
