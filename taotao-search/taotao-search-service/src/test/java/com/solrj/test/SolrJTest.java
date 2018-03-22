package com.solrj.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrJTest {
	@Test
	public void testAddDocument() throws SolrServerException, IOException{
		//创建一个solrServer,使用httpsolrServer创建对象
		SolrServer  solrServer=new HttpSolrServer("http://192.168.107.182:8080/solr/collection1");
		//创建一个文档对象solrInputDocument
		SolrInputDocument document=new SolrInputDocument();
		//想文档中添加域，必须有id域，要在schema.xml中定义
		document.addField("id", "0001");
		document.addField("item_title", "小米手机");
		document.addField("item_sell_point", "手机中的黑科技");
		//把文档添加到索引库
		solrServer.add(document);
		//提交
		solrServer.commit();
	}
	
	@Test
	public void testDelDocumentById() throws SolrServerException, IOException{
		//创建一个solrServer,使用httpsolrServer创建对象
		SolrServer  solrServer=new HttpSolrServer("http://192.168.107.182:8080/solr/collection1");
		//把文档从索引库删除，通过id
		solrServer.deleteById("zhang");
		//提交
		solrServer.commit();
	}
	
	@Test
	public void testDelDocumentByQuery() throws SolrServerException, IOException{
		//创建一个solrServer,使用httpsolrServer创建对象
		SolrServer  solrServer=new HttpSolrServer("http://192.168.107.182:8080/solr/collection1");
		//把文档从索引库删除，通过id
		solrServer.deleteByQuery("item_keywords:手机");
		//提交
		solrServer.commit();
	}
	
	@Test
	public void testqueryDocument() throws SolrServerException, IOException{
		//创建一个solrServer,使用httpsolrServer创建对象
		SolrServer  solrServer=new HttpSolrServer("http://192.168.107.182:8080/solr/collection1");
		SolrQuery query=new SolrQuery();
		query.setQuery("item_keywords:手机");
		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println("共找到结果："+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println("---------------------");
		}
	}
	
	@Test
	public void testqueryDocumentHightLighting() throws SolrServerException, IOException{
		//创建一个solrServer,使用httpsolrServer创建对象
		SolrServer  solrServer=new HttpSolrServer("http://192.168.107.182:8080/solr/collection1");
		SolrQuery query=new SolrQuery();
		query.setQuery("手机");
		//指定默认搜索域
		query.set("df","item_keywords");
		//开启高亮
		query.setHighlight(true);
		//添加高亮field
		query.addHighlightField("item_title");
		//设置高亮
		query.setHighlightSimplePre("<b>");
		query.setHighlightSimplePost("</b>");
		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println("共找到结果："+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			//取高亮显示
			String itemTitle=null;
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
			if (list!=null&&list.size()>0) {
				itemTitle=list.get(0);
			}else{
				itemTitle=(String) solrDocument.get("item_title");
			}
			System.out.println(itemTitle);
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println("---------------------");
		}
	}
}
