package com.drt.exeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionResolever {
	
	@ExceptionHandler(value=Exception.class)
	public ModelAndView catchException(Exception exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.setViewName("system-error");
		
		return mav;
	}


}
