package com.taotao.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult queryContentList(long categoryId){
		EasyUIDataGridResult result = contentService.queryContentList(categoryId);
		return result;
	}
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content){
		TaotaoResult result = contentService.addContent(content);
		return result;
	}
	
	@RequestMapping("/content/edit")
	@ResponseBody
	public TaotaoResult editContent(TbContent content){
		TaotaoResult result = contentService.updateContent(content);
		return result;
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteContent(String ids){
		List<Long> dids=new ArrayList<>();
		String[] strings = ids.split(",");
		for (String id : strings) {
			dids.add(Long.parseLong(id));
		}
		TaotaoResult result = contentService.deleteContent(dids);
		return result;
	}
}
