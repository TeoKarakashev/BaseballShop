package com.softuni.web;


import com.softuni.util.annotation.PageTitle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController {


    @PageTitle(name = "home")
    @GetMapping("/")
    public ModelAndView home(Principal principal, ModelAndView modelAndView){
        if(principal == null){
            modelAndView.setViewName("index");
        }else{
            modelAndView.setViewName("home");
        }

        return modelAndView;
    }

}
