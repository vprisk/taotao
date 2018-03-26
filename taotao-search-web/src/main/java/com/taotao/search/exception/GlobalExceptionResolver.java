package com.taotao.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理
 * @author Administrator
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception e) {
		e.printStackTrace();
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("message", "发生了错误");
		modelAndView.setViewName("error/exception");
		return modelAndView;
	}
	
}
