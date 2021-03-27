package com.softuni.web;


import com.softuni.service.LogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {

    private final LogService logService;

    public StatsController(LogService logService) {
        this.logService = logService;
    }


    @GetMapping("")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public String stats(Model model){
            model.addAttribute("logs", this.logService.findAll());
        return "stats/stats";
    }
}
