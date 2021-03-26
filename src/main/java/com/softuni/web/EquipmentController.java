package com.softuni.web;

import com.softuni.service.BatService;
import com.softuni.service.GloveService;
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

    @GetMapping("")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView all(ModelAndView modelAndView){

        //TODO schedule, AOP, RestController?
        //TODO Think of something on the home page, add picture from profile


        modelAndView.addObject("gloves", this.gloveService.findAllGloves());
        modelAndView.addObject("bats", this.batService.findAllBats());
        modelAndView.setViewName("/equipment/equipment");

        return modelAndView;
    }


}
