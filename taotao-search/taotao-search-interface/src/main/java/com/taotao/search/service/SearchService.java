package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

public interface SearchService {
	/**
	 * 搜索商品结果
	 * @param queryString
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
