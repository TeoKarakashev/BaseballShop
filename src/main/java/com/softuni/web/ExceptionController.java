package com.softuni.web;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable exception, ModelAndView modelAndView){
        modelAndView.addObject(exception.getMessage());
        modelAndView = new ModelAndView("error");

        return modelAndView;
    }
}
