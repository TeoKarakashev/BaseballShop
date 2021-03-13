package com.softuni.web;

import com.softuni.service.BatService;
import com.softuni.service.GloveService;
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
    public ModelAndView all(ModelAndView modelAndView){

        //TODO error handling, cloudinary
        //TODO add bats from xml, Think of something on the home page, details page for bats and gloves, see all btn

        modelAndView.addObject("gloves", this.gloveService.findAllGloves());
        modelAndView.addObject("bats", this.batService.findAllBats());
        modelAndView.setViewName("equipment");

        return modelAndView;
    }


}