package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.utils.FastDFSClient;

/**
 * 图片上传
 * @author Administrator
 *
 */
@Controller
public class PictureController {
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/pic/upload")
	@ResponseBody
	public Object picUpload(MultipartFile uploadFile){
		try {
			//获取扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//上传到图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:resource/client.conf");
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
			url = IMAGE_SERVER_URL + url;
			Map result=new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return result;
		} catch (Exception e) {
			Map result=new HashMap<>();
			result.put("error", 1);
			result.put("message", "图片上传失败");
			return result;
		}
	}
}
