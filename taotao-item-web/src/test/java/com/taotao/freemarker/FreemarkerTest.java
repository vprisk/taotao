package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerTest {
	@SuppressWarnings("unchecked")
	@Test
	public void test() throws IOException, TemplateException{
		//创建一个配置对象
		Configuration configuration=new Configuration(Configuration.getVersion());
		//设置模板所在的路径
		configuration.setDirectoryForTemplateLoading(new File("F:/freemarker"));
		//设置字符集
		configuration.setDefaultEncoding("UTF-8");
		//加载模板
		Template template = configuration.getTemplate("index.ftl");
		//准备数据
		@SuppressWarnings("rawtypes")
		Map data=new HashMap<>();
		//单个属性
		data.put("hello", "hello freemarker");
		//pojo
		Student student=new Student(1, "张三", new Date(), "上海");
		data.put("stu", student);
		//集合
		Student student2=new Student(2, "李四", new Date(), "北京");
		Student student3=new Student(3, "王五", new Date(), "武汉");
		List<Student> stuList=new ArrayList<>();
		stuList.add(student);
		stuList.add(student2);
		stuList.add(student3);
		data.put("stuList", stuList);
		//创建输出流
		Writer out=new FileWriter("F:/freemarker/index.html");
		//执行
		template.process(data, out);
		//关闭流
		out.close();
		System.out.println("ok");
	}
}
