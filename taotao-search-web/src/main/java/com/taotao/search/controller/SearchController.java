package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.service.SearchService;


/**
 * 搜索服务Controller
 * <p>Title: SearchController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Value("${SEARCH_RESULT_ROWS}")
	private int SEARCH_RESULT_ROWS;
	
	@RequestMapping("/search")
	public String search(@RequestParam(name="q",defaultValue="123")String queryString,@RequestParam(defaultValue="1")Integer page,Model model) throws Exception {
		//转码
		queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
		//查询
		SearchResult searchResult = searchService.search(queryString, page, SEARCH_RESULT_ROWS);
		//把结果传递给页面
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages", searchResult.getTotalPages());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", page);
		//返回逻辑视图
		return "search";
		
	}
}
