package com.softuni.web;


import com.softuni.service.LogService;
import com.softuni.util.annotation.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final LogService logService;

    public StatsController(LogService logService) {
        this.logService = logService;
    }


    @PageTitle(name = "statistics")
    @GetMapping("")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ModelAndView stats(ModelAndView modelAndView){
            modelAndView.addObject("logs", this.logService.findAll());
            modelAndView.setViewName("stats/stats");
            modelAndView.addObject("title", "This veryu coll");
        return modelAndView;
    }
}
