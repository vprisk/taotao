package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;

@Controller
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0")long parentId){
		List<EasyUITreeNode> result = contentCategoryService.getContentCategoryList(parentId);
		return result;
	}
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult addContentCategory(long parentId,String name){
		TaotaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	@RequestMapping("/content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(long id,String name){
		contentCategoryService.updateContentCategory(id, name);
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(long id){
		contentCategoryService.deleteContentCategory(id);
		return TaotaoResult.ok();
	}
}
