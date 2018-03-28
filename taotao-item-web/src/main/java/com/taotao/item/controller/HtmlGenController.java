package com.taotao.item.controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;

@Controller
public class HtmlGenController {
	@Autowired
	private FreeMarkerConfig freeMarkerConfig;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/genHtml")
	public String genHtml() throws Exception, MalformedTemplateNameException, ParseException, IOException{
		Configuration configuration = freeMarkerConfig.getConfiguration();
		Template template = configuration.getTemplate("hello.ftl");
		//创建输出流
		Writer out=new FileWriter("F:/freemarker/index_spring.html");
		//准备数据
		Map data=new HashMap<>();
		data.put("hello", "hello spring freemarker!");
		//执行
		template.process(data, out);
		//关闭流
		out.close();
		System.out.println("ok");
		return "ok";
	}
}
