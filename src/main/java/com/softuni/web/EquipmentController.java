package com.softuni.web;

import com.softuni.service.BatService;
import com.softuni.service.GloveService;
import com.softuni.util.annotation.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    private final GloveService gloveService;
    private final BatService batService;

    public EquipmentController(GloveService gloveService, BatService batService) {
        this.gloveService = gloveService;
        this.batService = batService;
    }

    @PageTitle(name = "equipment")
    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView all(ModelAndView modelAndView){

        modelAndView.addObject("gloves", this.gloveService.findAllGloves());
        modelAndView.addObject("bats", this.batService.findAllBats());
        modelAndView.setViewName("/equipment/equipment");

        return modelAndView;
    }


}
