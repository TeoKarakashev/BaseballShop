package com.softuni.web;

import com.softuni.model.view.GloveViewModel;
import com.softuni.service.GloveService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gloves")
public class GloveController {

    private final GloveService gloveService;

    public GloveController(GloveService gloveService) {
        this.gloveService = gloveService;
    }

    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView details(ModelAndView modelAndView,  @PathVariable String id) {
        GloveViewModel glove = this.gloveService.findById(id);
        modelAndView.addObject("glove", glove);
        modelAndView.setViewName("gloves-details");

        return modelAndView;
    }
}
