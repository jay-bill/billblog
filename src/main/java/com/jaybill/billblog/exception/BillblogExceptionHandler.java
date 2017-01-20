package com.jaybill.billblog.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class BillblogExceptionHandler  implements HandlerExceptionResolver{

	/**
	 * 异常统一处理
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("exception", exception);
		if(exception instanceof UserAccountException){
			return new ModelAndView("error-useraccount",map);
		}else if(exception instanceof UserAccountExistedException){
			return new ModelAndView("redirect:/error-user.jsp");
		}else if(exception instanceof CantFindThisIdException){
			return new ModelAndView("error-cantfindid",map);
		}
		return null;
	}
	
}
