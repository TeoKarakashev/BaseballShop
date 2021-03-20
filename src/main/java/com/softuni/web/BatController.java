package com.softuni.web;

import com.softuni.model.view.BatViewModel;
import com.softuni.service.BatService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/bats")
public class BatController {

    private final BatService batService;

    public BatController(BatService batService) {
        this.batService = batService;
    }


    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(@PathVariable String id, ModelAndView modelAndView){
        BatViewModel batViewModel = this.batService.findById(id);
        modelAndView.addObject("bat", batViewModel);
        modelAndView.setViewName("bats-details");
        return modelAndView;
    }
}
