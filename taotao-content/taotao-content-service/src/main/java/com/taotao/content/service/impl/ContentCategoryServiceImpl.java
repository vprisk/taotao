package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCategoryList(long parentId) {
		TbContentCategoryExample categoryExample=new TbContentCategoryExample();
		Criteria criteria = categoryExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> categories = tbContentCategoryMapper.selectByExample(categoryExample);
		List<EasyUITreeNode> result=new ArrayList<>();
		//循环组装待用tree结构结果
		for (TbContentCategory cat : categories) {
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(cat.getId());
			node.setText(cat.getName());
			node.setState(cat.getIsParent()?"closed":"open");
			result.add(node);
		}
		return result;
	}

	@Override
	public TaotaoResult addContentCategory(long parentId, String name) {
		TbContentCategory category=new TbContentCategory();
		category.setParentId(parentId);
		category.setName(name);
		//状态：1-正常	2-删除
		category.setStatus(1);
		//排序，默认为1
		category.setSortOrder(1);
		category.setIsParent(false);
		category.setCreated(new Date());
		category.setUpdated(new Date());
		//插入到数据库
		tbContentCategoryMapper.insert(category);
		//处理父节点
		TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parent.getIsParent()) {//叶子节点
			//更改为父节点
			parent.setIsParent(true);
			//更新父节点
			tbContentCategoryMapper.updateByPrimaryKey(parent);
		}
		return TaotaoResult.ok(category);
	}
	
	@Override
	public void updateContentCategory(long id, String name) {
		TbContentCategory category=tbContentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		category.setUpdated(new Date());
		tbContentCategoryMapper.updateByPrimaryKey(category);
	}
	
	@Override
	public void deleteContentCategory(long id) {
		tbContentCategoryMapper.deleteByPrimaryKey(id);
	}
}
